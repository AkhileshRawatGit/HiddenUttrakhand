package com.akhilesh.project.HiddenUkWeb.dto.Booking;

import com.akhilesh.project.HiddenUkWeb.entity.User;
import com.akhilesh.project.HiddenUkWeb.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class GuestDto {

    private Long id;

    private User user;

    private String name;

    private Integer age;

    private Gender gender;
}
