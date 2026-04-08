package com.akhilesh.project.HiddenUkWeb.service.BookHotel;

import com.akhilesh.project.HiddenUkWeb.dto.Booking.BookingDto;
import com.akhilesh.project.HiddenUkWeb.dto.Booking.BookingRequestDto;
import com.akhilesh.project.HiddenUkWeb.dto.Booking.GuestDto;
import com.akhilesh.project.HiddenUkWeb.entity.Booking;
import com.akhilesh.project.HiddenUkWeb.entity.enums.BookingStatus;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface BookingService {

    BookingDto create(BookingRequestDto booking);


    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);
}
