package com.kurilenko.utils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
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

    public static void loadNewModalityWindowWithController(String url, String nameWindow, Window window, Object controller) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(HelperWorkWithModalityWindow.class.getClassLoader().getResource(url));
        loader.setController(controller);
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle(nameWindow);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(window);
        stage.showAndWait();
    }

    public static void showInfoDialog(String heading, String body, StackPane stackPane){
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(heading));
        content.setBody(new Text(body));
        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("Okay");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        content.setActions(button);
        dialog.show();
    }
}
