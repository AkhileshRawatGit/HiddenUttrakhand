package com.akhilesh.project.HiddenUkWeb.service.Inventory;

import com.akhilesh.project.HiddenUkWeb.entity.Room;

public interface InventoryService {
    void initializeRoomForAYear(Room room);

    void deleteFutureInventories(Room room);
}
