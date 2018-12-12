package com.kurilenko.controller;

import com.kurilenko.entity.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    public void initialize() throws IOException {
        Platform.runLater(() -> {
            currentUserName.setText(currentUser.getUsername());
        });
    }
}
