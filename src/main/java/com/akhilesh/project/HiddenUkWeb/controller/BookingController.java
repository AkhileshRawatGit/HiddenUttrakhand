package com.akhilesh.project.HiddenUkWeb.controller;

import com.akhilesh.project.HiddenUkWeb.dto.Booking.BookingDto;
import com.akhilesh.project.HiddenUkWeb.dto.Booking.BookingRequestDto;
import com.akhilesh.project.HiddenUkWeb.dto.Booking.GuestDto;
import com.akhilesh.project.HiddenUkWeb.service.BookHotel.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotel/bookings")
@RequiredArgsConstructor
public class BookingController {


    private final BookingService bookingService;
    @PostMapping
    public ResponseEntity<?>bookHotel(@RequestBody BookingRequestDto dto){
        return ResponseEntity.ok(bookingService.create(dto));
    }

    @PostMapping("/{bookingId}/addGuests")
    public ResponseEntity<?>addGuests(@PathVariable Long bookingId, @RequestBody List<GuestDto> guestDtoList){
        return ResponseEntity.ok(bookingService.addGuests(bookingId,guestDtoList));
    }
}
