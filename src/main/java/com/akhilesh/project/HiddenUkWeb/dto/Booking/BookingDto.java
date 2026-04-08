package com.akhilesh.project.HiddenUkWeb.dto.Booking;

import com.akhilesh.project.HiddenUkWeb.entity.Guest;
import com.akhilesh.project.HiddenUkWeb.entity.Hotel;
import com.akhilesh.project.HiddenUkWeb.entity.Room;
import com.akhilesh.project.HiddenUkWeb.entity.User;
import com.akhilesh.project.HiddenUkWeb.entity.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {
    private Long id;

    private Integer roomCount;

    private BigDecimal totalPrice;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private BookingStatus bookingStatus;

    private Set<GuestDto> guests;
}
