package com.akhilesh.project.HiddenUkWeb.dto.placeDto;


import jakarta.persistence.NamedAttributeNode;
import lombok.*;

@Data
public class CreatePlaceRequestDTO {
    private Double latitude;
    private Double longitude;
    private Boolean hidden;
    private String name;
    private String district;
    private String description;
    private String category;
    private String bestSeason;
}
