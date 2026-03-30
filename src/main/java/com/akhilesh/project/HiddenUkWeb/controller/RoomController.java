package com.akhilesh.project.HiddenUkWeb.controller;

import com.akhilesh.project.HiddenUkWeb.advice.ApiResponse;
import com.akhilesh.project.HiddenUkWeb.dto.RoomDto.CreateRoomRequestDto;
import com.akhilesh.project.HiddenUkWeb.dto.RoomDto.UpdateRoomDto;
import com.akhilesh.project.HiddenUkWeb.service.Room.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hotels/{hotelId}/rooms")
public class RoomController {

    private final RoomService service;

    @PostMapping
    public ResponseEntity<?>createRoom(@RequestBody CreateRoomRequestDto dto, @PathVariable Long hotelId){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createRoom(hotelId,dto));
    }

    @GetMapping
    public ResponseEntity<?>getAllRoomByHotelId(@PathVariable Long hotelId){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getAllRoomByHotelId(hotelId));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<?>getRoomById(@PathVariable Long hotelId, @PathVariable Long roomId){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getRoomByRoomId(hotelId,roomId));
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<?>updateRoom(@PathVariable Long hotelId, @PathVariable Long roomId,
                                       @RequestBody UpdateRoomDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateRoom(hotelId,roomId,dto));
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<?>deleteRoom(@PathVariable Long hotelId,@PathVariable Long roomId){
        service.deleteRoom(hotelId, roomId);
        return ResponseEntity.ok(new ApiResponse<>("Room deleted successfully!"));
    }
}
