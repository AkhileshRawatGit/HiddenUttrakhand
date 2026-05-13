package com.akhilesh.project.HiddenUkWeb.dto.Strategy;

import com.akhilesh.project.HiddenUkWeb.entity.Hotel;
import lombok.Data;

@Data
public class HotelPriceDto {
    private Hotel hotel;
    private Double price;
}
