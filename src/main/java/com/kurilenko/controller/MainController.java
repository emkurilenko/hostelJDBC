package com.kurilenko.controller;

import com.kurilenko.entity.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MainController {
    private Stage primaryStage;
    private User currentUser;

    @FXML
    private Label currentUserName;

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            currentUserName.setText(currentUser.getUsername());
        });
    }
}
