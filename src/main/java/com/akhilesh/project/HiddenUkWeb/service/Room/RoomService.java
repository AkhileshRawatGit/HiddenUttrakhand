package com.akhilesh.project.HiddenUkWeb.service.Room;

import com.akhilesh.project.HiddenUkWeb.dto.RoomDto.CreateRoomRequestDto;
import com.akhilesh.project.HiddenUkWeb.dto.RoomDto.RoomResponseDto;
import com.akhilesh.project.HiddenUkWeb.dto.RoomDto.UpdateRoomDto;

import java.util.List;

public interface RoomService {
    RoomResponseDto createRoom(Long hotelId, CreateRoomRequestDto dto);

    List<RoomResponseDto> getAllRoomByHotelId(Long hotelId);

    RoomResponseDto getRoomByRoomId(Long hotelId,Long roomId);

    RoomResponseDto updateRoom(Long hotelId, Long roomId, UpdateRoomDto dto);

    void deleteRoom(Long hotelId,Long roomId);

}
