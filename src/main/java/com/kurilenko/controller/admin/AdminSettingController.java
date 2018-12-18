package com.kurilenko.controller.admin;

import com.jfoenix.controls.JFXButton;
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

    @FXML
    private void initialize(){
    }

    @FXML
    void btnUserMouseClicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/admin/work_with_user.fxml"));
        anchorPaneWork.getScene().setRoot(root);
    }

    @FXML
    void btnWorkWithInfoUniversity(MouseEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/admin/work_with_info_university.fxml"));
        anchorPaneWork.getScene().setRoot(root);
    }
}
