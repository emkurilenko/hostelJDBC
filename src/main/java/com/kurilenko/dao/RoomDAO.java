package com.kurilenko.dao;

import com.kurilenko.entity.Room;

import java.util.List;

public interface RoomDAO extends BaseDAO<Room, Long> {
    List<?> getAllInfoRooms(Long idHostel);
}
