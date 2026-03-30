package com.akhilesh.project.HiddenUkWeb.dto.RoomDto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class UpdateRoomDto {
    private String roomType;

    private BigDecimal pricePerNight;

    private List<String> amenities;

    private List<String> photos;

    private Integer totalCount;

    private Integer availableCount;

    private Integer maxOccupancy;
}
