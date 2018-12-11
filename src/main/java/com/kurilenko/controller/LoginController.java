package com.kurilenko.controller;

import com.kurilenko.entity.User;
import com.kurilenko.service.UserService;
import com.kurilenko.utils.MD5;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter

public class LoginController {

    private Stage primaryStage;

    @FXML
    private TextField fieldLogin;

    @FXML
    private PasswordField filedPassword;

    private UserService userService;

    @FXML
    private void initialize(){
        userService = new UserService();
    }

    @FXML
    void btnExit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void btnLogin(ActionEvent event) {
        User user = new User();
        user.setUsername(fieldLogin.getText());
        user.setPassword(MD5.getHash(filedPassword.getText()));
        user = userService.existUser(user);
        if(user != null){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/main.fxml"));
                Parent parent = (Parent)loader.load();
                MainController mainController = loader.getController();

                primaryStage.setScene(new Scene(parent));
                mainController.setPrimaryStage(primaryStage);
                mainController.setCurrentUser(user);
            }catch (IOException e){
                System.out.println(e);
            }
        }else
            System.out.println("very bad");
        cleanField();
    }

    private void cleanField(){
        fieldLogin.clear();
        filedPassword.clear();
    }

}
