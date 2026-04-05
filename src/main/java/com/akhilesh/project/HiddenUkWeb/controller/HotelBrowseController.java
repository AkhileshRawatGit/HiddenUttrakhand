package com.akhilesh.project.HiddenUkWeb.controller;

import com.akhilesh.project.HiddenUkWeb.dto.HotelDto.HotelInfoDto;
import com.akhilesh.project.HiddenUkWeb.dto.HotelDto.HotelResponseDTO;
import com.akhilesh.project.HiddenUkWeb.dto.HotelDto.HotelSearchRequest;
import com.akhilesh.project.HiddenUkWeb.service.Hotel.HotelService;
import com.akhilesh.project.HiddenUkWeb.service.Inventory.InventoryService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels")
@RequiredArgsConstructor
public class HotelBrowseController {

    private final InventoryService inventoryService;
    private final HotelService hotelService;
    @GetMapping("/search")
    public ResponseEntity<?>searchHotel(@RequestBody HotelSearchRequest request){
        Page<HotelResponseDTO> page=inventoryService.searchHotels(request);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{hotelId}/info")
    public ResponseEntity<?>getHotelsInfo(@PathVariable Long hotelId){
        HotelInfoDto dto=hotelService.getHotelInfo(hotelId);
        return ResponseEntity.ok(dto);

    }
}
