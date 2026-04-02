package com.akhilesh.project.HiddenUkWeb.service.Room;

import com.akhilesh.project.HiddenUkWeb.dto.RoomDto.CreateRoomRequestDto;
import com.akhilesh.project.HiddenUkWeb.dto.RoomDto.RoomResponseDto;
import com.akhilesh.project.HiddenUkWeb.dto.RoomDto.UpdateRoomDto;
import com.akhilesh.project.HiddenUkWeb.entity.Hotel;
import com.akhilesh.project.HiddenUkWeb.entity.Room;
import com.akhilesh.project.HiddenUkWeb.exception.ResourceNotFoundException;
import com.akhilesh.project.HiddenUkWeb.repository.HotelRepo;
import com.akhilesh.project.HiddenUkWeb.repository.RoomRepo;
import com.akhilesh.project.HiddenUkWeb.service.Inventory.InventoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{

    private final RoomRepo roomRepo;
    private final HotelRepo hotelRepo;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;
    @Override
    public RoomResponseDto createRoom(Long hotelId, CreateRoomRequestDto dto) {

        Hotel hotel=hotelRepo.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("hotel not found with this id: "+hotelId));
        Room room= modelMapper.map(dto,Room.class);
        room.setHotel(hotel);
        roomRepo.save(room);

        if(hotel.getActive()){
            inventoryService.initializeRoomForAYear(room);
        }
        return modelMapper.map(room,RoomResponseDto.class);
    }

    @Override
    public List<RoomResponseDto> getAllRoomByHotelId(Long hotelId) {
        if(!hotelRepo.existsById(hotelId)){
            throw new ResourceNotFoundException("hotel not found with this id: "+hotelId);
        }
        List<Room> rooms=roomRepo.findByHotelId(hotelId);
        return rooms.stream().map(r->modelMapper.map(r,RoomResponseDto.class)).toList();
    }

    @Override
    public RoomResponseDto getRoomByRoomId(Long hotelId,Long roomId) {

        Room room=roomRepo.findById(roomId).orElseThrow(()->new ResourceNotFoundException("room not found with this id:"+roomId));
        if(!room.getHotel().getId().equals(hotelId)){
            throw new ResourceNotFoundException("room does not belong to this hotel");
        }
        return modelMapper.map(room,RoomResponseDto.class);
    }

    @Override
    public RoomResponseDto updateRoom(Long hotelId, Long roomId, UpdateRoomDto dto) {

        Room room = roomRepo.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("room not found with this id: " + roomId));

        if (!room.getHotel().getId().equals(hotelId)) {
            throw new ResourceNotFoundException("hotel id not match with this hotel id " + hotelId);
        }

        modelMapper.map(dto, room);

        Room updatedRoom = roomRepo.save(room);

        return modelMapper.map(updatedRoom, RoomResponseDto.class);
    }
    @Override
    @Transactional
    public void deleteRoom(Long hotelId, Long roomId) {
        Room room = roomRepo.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));

        if (!room.getHotel().getId().equals(hotelId)) {
            throw new RuntimeException("Room does not belong to this hotel!");
        }

        inventoryService.deleteFutureInventories(room);
        roomRepo.deleteById(roomId);
    }
}
