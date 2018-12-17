package com.kurilenko.controller.registration;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.kurilenko.service.HostelService;
import com.kurilenko.service.OccupantService;
import com.kurilenko.service.RoomService;
import com.kurilenko.utils.CurrentSession;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class RegistrationController {

    private OccupantService occupantService;
    private RoomService roomService;
    private HostelService hosteService;

    @FXML
    private BorderPane registrationTab;

    @FXML
    private JFXTextField fieldName;

    @FXML
    private JFXTextField fieldFamily;

    @FXML
    private JFXTextField fieldMiddleName;

    @FXML
    private JFXComboBox<Long> comboBoxRoom;

    @FXML
    private JFXComboBox<String> comboBoxTypeOccupation;

    @FXML
    private HBox hBoxStudent;

    @FXML
    private JFXComboBox<String> comboBoxFloors;

    @FXML
    private JFXComboBox<?> comboBoxFaculty;

    @FXML
    private JFXComboBox<?> comboBoxDepartment;

    @FXML
    private JFXComboBox<?> comboBoxSpeciality;

    @FXML
    private JFXComboBox<?> comboBoxGroupStudent;

    @FXML
    void comboBoxFloorsAction(ActionEvent event) {
        comboBoxRoom.getItems().clear();
        comboBoxRoom.setDisable(false);
        comboBoxRoom.getItems().addAll(roomService.getAllRoomNumber(Long.valueOf(comboBoxFloors.getValue())));
    }

    @FXML
    void comboBoxTypeOccupationAction(ActionEvent event) {
        if (comboBoxTypeOccupation.getValue().equals("Студент")) {
            hBoxStudent.setVisible(true);
        } else {
            hBoxStudent.setVisible(false);
        }
    }

    @FXML
    public void initialize() {
        roomService = new RoomService();
        occupantService = new OccupantService();
        hosteService = new HostelService();
        Platform.runLater(() -> {
            hBoxStudent.setVisible(false);
            comboBoxTypeOccupation.getItems().addAll("Студент", "Сотрудник");
            comboBoxRoom.setDisable(true);
            comboBoxFloors.getItems().addAll(hosteService.getFloorsList(hosteService.getHostelByUserId(CurrentSession.user.getId()).getNameHostel()));
        });
    }
}
