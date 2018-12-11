package com.kurilenko;

import com.kurilenko.utils.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent =(Parent) new FXMLLoader().load(getClass().getClassLoader().getResource("fxml/index.fxml"));
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    }
}
