package com.kurilenko.controller.room;

import com.kurilenko.service.RoomService;
import javafx.fxml.FXML;

public class AddRoomController {
    private RoomService roomService;


    @FXML
    public void initialize() {
        roomService = new RoomService();
    }
}
