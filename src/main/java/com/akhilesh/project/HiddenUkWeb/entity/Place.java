package com.akhilesh.project.HiddenUkWeb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false,name = "hidden")
    private Boolean hidden;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(name="best_season",nullable = false)
    private String bestSeason;

    @OneToMany(mappedBy = "place",fetch = FetchType.LAZY)
    private List<Hotel> hotelList;

}
