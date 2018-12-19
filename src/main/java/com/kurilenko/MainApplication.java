package com.kurilenko;

import com.kurilenko.controller.LoginController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader( getClass().getClassLoader().getResource("fxml/login.fxml"));
        Parent parent = (Parent)loader.load();
        LoginController loginController = loader.getController();
        stage.setScene(new Scene(parent));
        primaryStage.setResizable(false);
        loginController.setPrimaryStage(stage);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        Platform.exit();
        System.exit(0);
    }
}
