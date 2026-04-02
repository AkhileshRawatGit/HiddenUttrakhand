package com.akhilesh.project.HiddenUkWeb.repository;


import com.akhilesh.project.HiddenUkWeb.entity.Inventory;
import com.akhilesh.project.HiddenUkWeb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long> {

    void deleteByDateAfterAndRoom(LocalDate date, Room room);
}
