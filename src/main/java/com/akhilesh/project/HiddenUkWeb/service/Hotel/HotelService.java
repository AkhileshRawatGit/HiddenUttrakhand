package com.akhilesh.project.HiddenUkWeb.service.Hotel;

import com.akhilesh.project.HiddenUkWeb.dto.HotelDto.CreateHotelRequestDTO;
import com.akhilesh.project.HiddenUkWeb.dto.HotelDto.HotelResponseDTO;
import com.akhilesh.project.HiddenUkWeb.dto.HotelDto.UpdateHotelRequestDto;

import java.util.List;

public interface HotelService {
    HotelResponseDTO createHotel(CreateHotelRequestDTO dto);
    HotelResponseDTO getHotelById(Long id);
    List<HotelResponseDTO> getAllHotelByPlace(Long id);
    HotelResponseDTO updateById(Long placeId,Long hotelId, UpdateHotelRequestDto dto);
    void deleteById(Long placeId,Long hotelId);
}
