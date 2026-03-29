package com.akhilesh.project.HiddenUkWeb.dto.HotelDto;

import com.akhilesh.project.HiddenUkWeb.entity.HotelContactInfo;
import com.akhilesh.project.HiddenUkWeb.entity.Place;
import com.akhilesh.project.HiddenUkWeb.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class HotelResponseDTO {
    private Long id;
    private String name;
    private String city;
    private List<String> amenities;
    private List<String> photos;
    private HotelContactDTO contactInfo;
    private Boolean active;
    private Long placeId;
}
