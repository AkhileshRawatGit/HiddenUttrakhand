package com.akhilesh.project.HiddenUkWeb.service.BookHotel;

import com.akhilesh.project.HiddenUkWeb.dto.Booking.BookingDto;
import com.akhilesh.project.HiddenUkWeb.dto.Booking.BookingRequestDto;
import com.akhilesh.project.HiddenUkWeb.dto.Booking.GuestDto;
import com.akhilesh.project.HiddenUkWeb.entity.*;
import com.akhilesh.project.HiddenUkWeb.entity.enums.BookingStatus;
import com.akhilesh.project.HiddenUkWeb.exception.ResourceNotFoundException;
import com.akhilesh.project.HiddenUkWeb.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Repository
@RequiredArgsConstructor
@Transactional
public class BookingServiceImpl implements BookingService{

    private final HotelRepo hotelRepo;
    private final RoomRepo roomRepo;
    private final BookingRepo bookingRepo;
    private final InventoryRepo inventoryRepo;
    private final ModelMapper modelMapper;
    private final GuestRepo guestRepo;
    @Override
    public BookingDto create(BookingRequestDto dto) {
        Hotel hotel=hotelRepo.findById(dto.getHotelId()).orElseThrow(()->new ResourceNotFoundException("hotel not found with this id: "+dto.getHotelId()));
        Room room=roomRepo.findById(dto.getRoomId()).orElseThrow(()->new ResourceNotFoundException("room not found with this id: "+dto.getRoomId()));
        List<Inventory> list=inventoryRepo.findAndLockAvailableInventory(room.getId(),dto.getCheckInDate(),dto.getCheckOutDate(),dto.getRoomCount());
        long daysCount= ChronoUnit.DAYS.between(dto.getCheckInDate(),dto.getCheckOutDate())+1;
        if(list.size()!=daysCount){
            throw new ResourceNotFoundException("the room inventory is not available for these days");
        }
        // reverse the room
        for(Inventory inventory:list){
            inventory.setReversedCount(inventory.getReversedCount()+dto.getRoomCount());
        }

        inventoryRepo.saveAll(list);


        //create a booking
        Booking booking=Booking.builder()
                .bookingStatus(BookingStatus.REVERSED)
                .hotel(hotel)
                .room(room)
                .checkInDate(dto.getCheckInDate())
                .checkOutDate(dto.getCheckOutDate())
                .user(getCurrentUser())
                .roomCount(dto.getRoomCount())
                .totalPrice(BigDecimal.TEN)
                .build();
        booking=bookingRepo.save(booking);
        return modelMapper.map(booking,BookingDto.class);
    }

    @Override
    @Transactional
    public BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList) {
        Booking booking=bookingRepo.findById(bookingId).orElseThrow(()->new ResourceNotFoundException("" +
                "booking not found with this id: "+bookingId));

        if(hasBookingExpired(booking)){
            throw new IllegalArgumentException("booking is expired");
        }
        if(booking.getBookingStatus()!=BookingStatus.REVERSED){
            throw new IllegalArgumentException("booking is not is Reversed state");
        }

        //add guest
        for(GuestDto guestDto: guestDtoList){
            Guest guest=modelMapper.map(guestDto, Guest.class);
            guest.setUser(getCurrentUser());
            guest=guestRepo.save(guest);
            booking.getGuests().add(guest);

        }
        booking.setBookingStatus(BookingStatus.GUEST_ADDED);
        booking=bookingRepo.save(booking);
        return modelMapper.map(booking,BookingDto.class);
    }

    public Boolean hasBookingExpired(Booking booking){
        return booking.getCreatedAt().plusMinutes(10).isBefore(LocalDateTime.now());
    }

    public User getCurrentUser(){
        User user=new User();
        user.setId(1L);
        return user;
    }
}
