package com.akhilesh.project.HiddenUkWeb.repository;

import com.akhilesh.project.HiddenUkWeb.entity.Booking;
import com.akhilesh.project.HiddenUkWeb.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking,Long> {
}
