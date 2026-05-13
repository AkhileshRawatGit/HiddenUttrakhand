package com.akhilesh.project.HiddenUkWeb.repository;

import com.akhilesh.project.HiddenUkWeb.dto.Strategy.HotelPriceDto;
import com.akhilesh.project.HiddenUkWeb.entity.Hotel;
import com.akhilesh.project.HiddenUkWeb.entity.HotelMinPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.ScopedValue;
import java.time.LocalDate;

public interface HotelMinPriceRepo extends JpaRepository<HotelMinPrice,Long> {
    @Query("""
            select new com.akhilesh.project.HiddenUkWeb.dto.Strategy.HotelPriceDto(i.hotel,AVG(i.price))
            from HotelMinPrice i
            where i.hotel.city= :city
                and i.date between :startDate and :endDate
                and i.hotel.active=true
            group by i.hotel
            """)
    Page<HotelPriceDto> findHotelWithAvailableInventory(
            @Param("city") String city,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("roomCount") Integer roomCount,
            @Param("dateCount") Long dateCount,
            Pageable pageable
    );

    ScopedValue<HotelMinPrice> findByHotelAndDate(Hotel hotel, LocalDate date);
}
