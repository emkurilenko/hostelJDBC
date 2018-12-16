package com.kurilenko.service;

import com.kurilenko.dao.impl.RoomDAOImpl;
import com.kurilenko.entity.Room;
import com.kurilenko.entity.RoomInfo;
import com.kurilenko.entity.RoomProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.stream.Collectors;

public class RoomService {
    private RoomDAOImpl roomDAO;

    public RoomService(){
        roomDAO = new RoomDAOImpl();
    }

    public Long saveRoom(Room room){
       Long id = roomDAO.save(room);
       return id;
    }

    public ObservableList<RoomProperty> getAllInfoRoom(Long id){
        ObservableList<RoomProperty> dummyData = FXCollections.observableArrayList();
        dummyData.addAll(roomDAO.getAllInfoRooms(id).stream().map(room -> createRoomProperty(room)).collect(Collectors.toList()));
        return dummyData;
    }

    public RoomProperty createRoomProperty(RoomInfo roomInfo) {
        return new RoomProperty(roomInfo.getId(),
                roomInfo.getFloorNumber(),
                roomInfo.getNumberRoom(),
                roomInfo.getOccupiedBeds(),
                roomInfo.getNumberofbeds(),
                roomInfo.getRoomSpecification());
    }

    public void delete(RoomProperty roomProperty) {
        roomDAO.delete(Long.valueOf(roomProperty.getId().getValue()));
    }
}
