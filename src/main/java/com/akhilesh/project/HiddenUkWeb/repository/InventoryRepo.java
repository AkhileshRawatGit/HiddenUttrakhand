package com.akhilesh.project.HiddenUkWeb.repository;


import com.akhilesh.project.HiddenUkWeb.entity.Hotel;
import com.akhilesh.project.HiddenUkWeb.entity.Inventory;
import com.akhilesh.project.HiddenUkWeb.entity.Room;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long> {

    void deleteByRoom( Room room);

    @Query("""
            select distinct i.hotel
            from Inventory i
            where i.city= :city
                and i.date between :startDate and :endDate
                and i.closed=false
                and (i.totalCount-i.bookedCount -i.reversedCount) >= :roomCount
            group by i.hotel, i.room
            having count(i.date) = :dateCount
            """)
    Page<Hotel>findHotelWithAvailableInventory(
            @Param("city") String city,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("roomCount") Integer roomCount,
            @Param("dateCount") Long dateCount,
            Pageable pageable
    );

    @Query("""
            select i from Inventory i
            where i.room.id= :roomId
                and i.date between :startDate and :endDate
                and i.closed=false
                and (i.totalCount-i.bookedCount -i.reversedCount) >= :roomCount
            
            """)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Inventory> findAndLockAvailableInventory(
            @Param("roomId") Long roomId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("roomCount") Integer roomCount
    );
}
