package com.akhilesh.project.HiddenUkWeb.dto.placeDto;

import lombok.Data;

@Data
public class UpdatePlaceRequestDTO {
    private Boolean hidden;
    private String name;
    private String district;
    private String description;
    private String category;
    private String bestSeason;
    private Double latitude;
    private Double longitude;
}
