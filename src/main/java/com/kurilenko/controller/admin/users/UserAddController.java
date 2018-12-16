package com.kurilenko.controller.admin.users;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.kurilenko.entity.Hostels;
import com.kurilenko.entity.User;
import com.kurilenko.entity.enums.UserRole;
import com.kurilenko.service.HostelService;
import com.kurilenko.service.UserService;
import com.kurilenko.utils.HelperWorkWithModalityWindow;
import com.kurilenko.utils.MD5;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class UserAddController {
    private UserService userService;
    private HostelService hostelService;

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXTextField fielduserName;

    @FXML
    private JFXPasswordField fieldPassword;

    @FXML
    private JFXComboBox<String> comboBoxHostel;

    @FXML
    private JFXComboBox<String> comboBoxRole;

    @FXML
    void btnAddMouseClicked(MouseEvent event) {
        if(checkData()){
            User user = new User();
            user.setUsername(fielduserName.getText());
            user.setPassword(MD5.getHash(fieldPassword.getText()));
            user.setUserRole(UserRole.valueOf(comboBoxRole.getValue()));
            Hostels hostels = new Hostels();
            hostels.setNameHostel(comboBoxHostel.getValue());
            Long userId = userService.createUser(user, hostels);
            if(userId != 0) {
                HelperWorkWithModalityWindow.showInfoDialog("Ok", "user created", stackPane);
                Stage stage = (Stage) stackPane.getScene().getWindow();
                stage.close();
            }else {
                clearField();
                HelperWorkWithModalityWindow.showInfoDialog("Woops", "Check the correctness of the data", stackPane);
            }
        }else{
            clearField();
            HelperWorkWithModalityWindow.showInfoDialog("Woops", "Check the correctness of the data", stackPane);
        }
    }

    @FXML
    void btnCancelMouseClicked(MouseEvent event) {
        Stage stage = (Stage) stackPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void initialize(){
        userService = new UserService();
        hostelService = new HostelService();
        comboBoxHostel.getItems().addAll(hostelService.getAllNameHostel());
        comboBoxRole.getItems().add(UserRole.ROLE_ADMIN.name());
        comboBoxRole.getItems().add(UserRole.ROLE_USER.name());
    }

    private void clearField(){
        fieldPassword.clear();
        fielduserName.clear();
    }

    private boolean checkData(){
        if(!checkField(fielduserName.getText())){
            return false;
        }
        if(!checkField(fieldPassword.getText())){
            return false;
        }
        if(comboBoxHostel.getValue().isEmpty()){
            return false;
        }
        if(comboBoxRole.getValue().isEmpty()){
            return false;
        }
        return true;
    }

    private boolean checkField(String str){
        if(str.isEmpty() || str.length() < 3 || str.equals("")){
            return false;
        }
        return true;
    }

}
