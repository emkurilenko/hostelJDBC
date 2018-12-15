package com.kurilenko.controller.admin;

import com.kurilenko.service.RoomService;
import com.kurilenko.service.UserService;
import javafx.fxml.FXML;

public class AdminSettingController {
    private UserService userService;
    private RoomService roomService;
    @FXML
    private void initialize(){
        roomService = new RoomService();
        userService = new UserService();
    }
}
