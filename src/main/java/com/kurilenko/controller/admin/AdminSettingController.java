package com.kurilenko.controller.admin;

import com.jfoenix.controls.JFXButton;
import com.kurilenko.service.RoomService;
import com.kurilenko.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminSettingController {

    @FXML
    private JFXButton btnUsers;

    @FXML
    private AnchorPane anchorPaneWork;

    private UserService userService;
    private RoomService roomService;
    @FXML
    private void initialize(){
        roomService = new RoomService();
        userService = new UserService();
    }

    @FXML
    void btnUserMouseClicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/admin/work_with_user.fxml"));
        anchorPaneWork.getScene().setRoot(root);
    }
}
