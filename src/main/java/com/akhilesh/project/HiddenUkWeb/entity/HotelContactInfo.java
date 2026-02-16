package com.akhilesh.project.HiddenUkWeb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class HotelContactInfo {
    private String address;
    private String email;
    private String phoneNumber;
    private String location;


}