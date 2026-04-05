package com.akhilesh.project.HiddenUkWeb.service.Inventory;

import com.akhilesh.project.HiddenUkWeb.dto.HotelDto.HotelResponseDTO;
import com.akhilesh.project.HiddenUkWeb.dto.HotelDto.HotelSearchRequest;
import com.akhilesh.project.HiddenUkWeb.entity.Hotel;
import com.akhilesh.project.HiddenUkWeb.entity.Inventory;
import com.akhilesh.project.HiddenUkWeb.entity.Room;
import com.akhilesh.project.HiddenUkWeb.repository.InventoryRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepo inventoryRepo;
    private final ModelMapper modelMapper;
    @Override
    public void initializeRoomForAYear(Room room) {
        LocalDate today=LocalDate.now();
        LocalDate endDay=today.plusYears(1);
        for(;!today.isAfter(endDay);today=today.plusDays(1)){
            Inventory inventory=Inventory.builder()
                    .hotel(room.getHotel())
                    .room(room)
                    .bookedCount(0)
                    .city(room.getHotel().getCity())
                    .date(today)
                    .price(room.getPricePerNight())
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .build();
            inventoryRepo.save(inventory);
        }

    }

    @Override
    public void deleteAllInventories(Room room) {
        inventoryRepo.deleteByRoom(room);
    }

    @Override
    public Page<HotelResponseDTO> searchHotels(HotelSearchRequest request) {
        Pageable pageable= PageRequest.of(request.getPage(),request.getSize());
        Long dateCount= ChronoUnit.DAYS.between(request.getStartDate(),request.getEndDate())+1;
        Page<Hotel>hotels=inventoryRepo.findHotelWithAvailableInventory(request.getCity(),request.getStartDate(),request.getEndDate(),request.getRoomCount(),
                dateCount,pageable);
        return hotels.map(h->modelMapper.map(h,HotelResponseDTO.class));
    }
}
