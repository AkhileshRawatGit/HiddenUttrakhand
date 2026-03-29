package com.akhilesh.project.HiddenUkWeb.repository;

import com.akhilesh.project.HiddenUkWeb.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepo extends JpaRepository<Hotel,Long> {
    List<Hotel> findByPlaceId(Long id);
}
