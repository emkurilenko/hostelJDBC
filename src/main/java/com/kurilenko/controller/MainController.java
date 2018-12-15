package com.kurilenko.controller;

import com.jfoenix.controls.JFXButton;
import com.kurilenko.controller.room.RoomController;
import com.kurilenko.entity.User;
import com.kurilenko.entity.enums.UserRole;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter

public class MainController {
    private Stage primaryStage;
    private User currentUser;

    @FXML
    private RoomController roomController;

    @FXML
    private Label currentUserName;

    @FXML
    private JFXButton btnOptionForAdmin;

    @FXML
    public void initialize() throws IOException {
        Platform.runLater(() -> {
            currentUserName.setText(currentUser.getUsername());
            if(currentUser.getUserRole() == UserRole.ROLE_ADMIN){
                btnOptionForAdmin.setVisible(true);
            }
        });
    }

    @FXML
    private void btnSettingForAdminMouseClicked(MouseEvent event){

    }
}
