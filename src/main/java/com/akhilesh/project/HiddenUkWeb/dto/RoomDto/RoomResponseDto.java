package com.akhilesh.project.HiddenUkWeb.dto.RoomDto;

import com.akhilesh.project.HiddenUkWeb.entity.Hotel;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class RoomResponseDto {

    private Long id;

    private String roomType;

    private BigDecimal pricePerNight;

    private List<String> amenities;

    private List<String> photos;

    private Integer totalCount;

    private Integer availableCount;

    private Integer maxOccupancy;

    private Long hotelId;

    private String hotelName;
}
