package com.akhilesh.project.HiddenUkWeb.dto.HotelDto;

import com.akhilesh.project.HiddenUkWeb.entity.HotelContactInfo;

import lombok.Data;

import java.util.List;


@Data
public class CreateHotelRequestDTO {

    private String name;
    private String city;
    private List<String> amenities;
    private List<String> photos;
    private HotelContactDTO contactInfo;
    private Boolean active;
    private Long placeId;
}
