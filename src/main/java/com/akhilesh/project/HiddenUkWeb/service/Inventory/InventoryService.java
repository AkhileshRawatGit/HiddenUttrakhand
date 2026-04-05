package com.akhilesh.project.HiddenUkWeb.service.Inventory;

import com.akhilesh.project.HiddenUkWeb.dto.HotelDto.HotelResponseDTO;
import com.akhilesh.project.HiddenUkWeb.dto.HotelDto.HotelSearchRequest;
import com.akhilesh.project.HiddenUkWeb.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {
    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelResponseDTO> searchHotels(HotelSearchRequest request);
}
