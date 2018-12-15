package com.kurilenko.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class HelperWorkWithModalityWindow {
    public static void loadNewModalityWindow(String url, String nameWindow, Window window) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(HelperWorkWithModalityWindow.class.getClassLoader().getResource(url));
        stage.setScene(new Scene(root));
        stage.setTitle(nameWindow);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(window);
        stage.showAndWait();
    }
}
