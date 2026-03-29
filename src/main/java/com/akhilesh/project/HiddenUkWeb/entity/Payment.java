package com.akhilesh.project.HiddenUkWeb.entity;

import com.akhilesh.project.HiddenUkWeb.entity.enums.PaymentStatus;
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
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String transactionId;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal amount;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private String paymentMethod;

    @Enumerated(value = EnumType.STRING)
    private PaymentStatus paymentStatus;
}
