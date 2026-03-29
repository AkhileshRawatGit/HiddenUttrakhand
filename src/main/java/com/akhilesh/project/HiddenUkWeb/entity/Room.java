package com.akhilesh.project.HiddenUkWeb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id",nullable = false)
    private Hotel hotel;

    @Column(nullable = false)
    private String roomType;

    @Column(nullable = false,precision = 10, scale = 2)
    private BigDecimal pricePerNight;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(columnDefinition = "JSON")
    private String amenities;

    @Column(columnDefinition = "JSON")
    private String photos;

    @Column(nullable = false)
    private Integer totalCount;

    @Column(nullable = false)
    private Integer availableCount;

    @Column(nullable = false)
    private Integer maxOccupancy;
}
