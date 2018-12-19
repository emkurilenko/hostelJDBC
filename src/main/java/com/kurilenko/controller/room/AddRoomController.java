package com.kurilenko.controller.room;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.kurilenko.entity.Room;
import com.kurilenko.service.HostelService;
import com.kurilenko.service.RoomService;
import com.kurilenko.service.RoomSpecificationService;
import com.kurilenko.utils.HelperWorkWithModalityWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AddRoomController {
    private RoomService roomService;
    private HostelService hostelService;
    private RoomSpecificationService roomSpecificationService;

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXTextField filedNumber;

    @FXML
    private JFXTextField filedSquer;

    @FXML
    private JFXComboBox<String> comboBoxHostel;

    @FXML
    private JFXComboBox<String> comboBoxSpecification;

    @FXML
    private JFXComboBox<String> comboBoxFloors;

    @FXML
    private JFXButton btnCancel;

    @FXML
    void btnAddMouseClicked(MouseEvent event) {
        if (checkData()) {
            Room room = new Room();
            room.setNumber(0L);
            room.setFkHostel(hostelService.getHostelByName(comboBoxHostel.getValue()).getId());
            room.setNumberRoom(Long.valueOf(filedNumber.getText()));
            room.setSquare(Double.valueOf(filedSquer.getText()));
            room.setFloors(Long.valueOf(comboBoxFloors.getValue()));
            room.setRoomSpecification(roomSpecificationService.getBySpecification(comboBoxSpecification.getValue()).getId());
            Long aLong = roomService.saveRoom(room);
            if(aLong != 0){
                HelperWorkWithModalityWindow.showInfoDialog("Ok", "Room created", stackPane);
                Stage stage = (Stage) stackPane.getScene().getWindow();
                stage.close();
            }
        } else {
            HelperWorkWithModalityWindow.showInfoDialog("Woops", "Empty field", stackPane);
        }
    }

    @FXML
    void comboBoxHostelListener(ActionEvent event) {
        comboBoxFloors.setDisable(false);
        comboBoxFloors.getItems().addAll(hostelService.getFloorsList(comboBoxHostel.getValue()));
    }

    private boolean checkData() {
        if (!checkNumberRoom() || !checkFiledSquer()
                && checkComboBox(comboBoxHostel) && checkComboBox(comboBoxSpecification) && checkComboBox(comboBoxFloors)) {
            return false;
        }
        return true;
    }

    private boolean checkComboBox(JFXComboBox<String> comboBox){
        if(comboBox.getValue().isEmpty() || comboBox.getValue().equals(""))
            return false;
        return true;
    }

    private boolean checkFiledSquer() {
        try {
            if (!filedSquer.getText().isEmpty() && !filedSquer.equals("")) {
                Double squer = Double.valueOf(filedSquer.getText());
                if (squer > 1.0) {
                    return true;
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    public boolean checkNumberRoom() {
        try {
            if (!filedNumber.getText().isEmpty() && !filedNumber.equals("")) {
                Long numberRoom = Long.valueOf(filedNumber.getText());
                if (numberRoom > 1.0) {
                    return true;
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    @FXML
    public void btnCancelMouseClicked(MouseEvent e){
        Stage stage = (Stage) stackPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() {
        roomSpecificationService = new RoomSpecificationService();
        hostelService = new HostelService();
        roomService = new RoomService();
        comboBoxFloors.setDisable(true);
        comboBoxHostel.getItems().addAll(hostelService.getAllNameHostel());
        comboBoxSpecification.getItems().addAll(roomSpecificationService.getAllNameSpecification());
    }
}
