package com.akhilesh.project.HiddenUkWeb.controller;

import com.akhilesh.project.HiddenUkWeb.dto.HotelDto.CreateHotelRequestDTO;
import com.akhilesh.project.HiddenUkWeb.dto.HotelDto.HotelResponseDTO;
import com.akhilesh.project.HiddenUkWeb.dto.HotelDto.UpdateHotelRequestDto;
import com.akhilesh.project.HiddenUkWeb.service.Hotel.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hotel")
public class HotelController {


    private final HotelService service;
    @PostMapping
    public ResponseEntity<?>createHotel(@RequestBody CreateHotelRequestDTO dto){
        HotelResponseDTO dto1=service.createHotel(dto);
        return ResponseEntity.ok(dto1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable Long id){
        HotelResponseDTO dto=service.getHotelById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/place/{id}")
    public ResponseEntity<?>getAllHotelByPlace(@PathVariable Long id){
        List<HotelResponseDTO>hotelResponseDTOS=service.getAllHotelByPlace(id);
        return ResponseEntity.ok(hotelResponseDTOS);
    }


    @PutMapping("place/{placeId}/hotel/{hotelId}")
    public ResponseEntity<?>updateById(@PathVariable Long placeId ,@PathVariable Long hotelId, @RequestBody UpdateHotelRequestDto dto){
        HotelResponseDTO dto1=service.updateById(placeId,hotelId,dto);
        return ResponseEntity.ok(dto1);

    }
}
