package com.akhilesh.project.HiddenUkWeb.entity;

import com.akhilesh.project.HiddenUkWeb.entity.enums.Plan;
import com.akhilesh.project.HiddenUkWeb.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id",nullable = false)
    private Guest guestUser;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Enumerated(value = EnumType.STRING)
    private Set<Plan> plan;

    @Enumerated(value = EnumType.STRING)
    private Set<Status>status;
}
