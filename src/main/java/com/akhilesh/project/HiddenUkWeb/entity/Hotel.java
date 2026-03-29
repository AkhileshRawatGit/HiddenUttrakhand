package com.akhilesh.project.HiddenUkWeb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @ElementCollection
    private List<String> amenities;

    @ElementCollection
    private List<String> photos;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


    @Embedded
    private HotelContactInfo contactInfo;

    @Column(nullable = false)
    private Boolean active=true;


    @OneToMany(mappedBy = "hotel",fetch = FetchType.LAZY)
    private List<Room> rooms;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
