package com.akhilesh.project.HiddenUkWeb.dto.HotelDto;

import com.akhilesh.project.HiddenUkWeb.dto.RoomDto.RoomResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelInfoDto {
    private HotelResponseDTO hotelResponseDTO;
    private List<RoomResponseDto> roomResponseDtoList;


}
