package com.kurilenko.controller;

import com.jfoenix.controls.JFXButton;
import com.kurilenko.controller.room.RoomController;
import com.kurilenko.entity.User;
import com.kurilenko.entity.enums.UserRole;
import com.kurilenko.utils.CurrentSession;
import com.kurilenko.utils.HelperWorkWithModalityWindow;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
            currentUser = CurrentSession.user;
            currentUserName.setText(currentUser.getUsername());
            if(currentUser.getUserRole() == UserRole.ROLE_ADMIN){
                btnOptionForAdmin.setVisible(true);
            }
        });
    }

    @FXML
    private void btnSettingForAdminMouseClicked(MouseEvent event) throws IOException{
        HelperWorkWithModalityWindow.loadNewModalityWindow("fxml/admin/admin_setting.fxml", "Настройки админа", ((Node)event.getSource()).getScene().getWindow());
    }
}
