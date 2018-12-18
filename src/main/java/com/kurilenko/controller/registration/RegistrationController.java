package com.kurilenko.controller.registration;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.kurilenko.entity.Department;
import com.kurilenko.entity.Specialty;
import com.kurilenko.entity.enums.UserRole;
import com.kurilenko.service.*;
import com.kurilenko.utils.CurrentSession;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.controlsfx.control.CheckComboBox;

public class RegistrationController {

    private OccupantService occupantService;
    private UniversityService universityService;
    private RoomService roomService;
    private HostelService hosteService;
    private UnderwearService underviewerService;


    @FXML
    private CheckComboBox<String> checkBoxViewUnderviewer;

    @FXML
    private BorderPane registrationTab;

    @FXML
    private JFXTextField fieldName;

    @FXML
    private JFXTextField fieldFamily;

    @FXML
    private JFXTextField fieldMiddleName;

    @FXML
    private JFXTextField fieldTelephone;

    @FXML
    private JFXTextField fieldNumberInFamily;
    @FXML
    private JFXTextField fileldMother;
    @FXML
    private JFXTextField fieldFather;


    @FXML
    private JFXComboBox<Long> comboBoxRoom;

    @FXML
    private JFXComboBox<String> comboBoxTypeOccupation;

    @FXML
    private HBox hBoxStudent;

    @FXML
    private JFXComboBox<String> comboBoxFloors;

    @FXML
    private JFXComboBox<String> comboBoxFaculty;

    @FXML
    private JFXComboBox<String> comboBoxDepartment;

    @FXML
    private JFXComboBox<String> comboBoxSpeciality;

    @FXML
    private JFXComboBox<String> comboBoxGroupStudent;

    @FXML
    private JFXDatePicker dateDBPicker;

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
    void comboBoxDepartmentActon(ActionEvent event) {
        if (comboBoxTypeOccupation.getValue() != null) {
            if (comboBoxTypeOccupation.getValue().equals("Студент")) {
                if (comboBoxDepartment.getValue() != null) {
                    comboBoxSpeciality.setDisable(false);
                    Department department = universityService.getDepartmentByName(comboBoxDepartment.getValue());
                    if (department != null) {
                        comboBoxSpeciality.getItems().clear();
                        comboBoxSpeciality.getItems().addAll(
                                universityService.getAllSpecialityName(department.getId()));
                        if (comboBoxSpeciality.getItems().size() == 0) {
                            comboBoxSpeciality.setDisable(true);
                            comboBoxGroupStudent.setDisable(true);
                        }
                    }
                }
            } else {
                comboBoxSpeciality.setVisible(true);
            }
        }
    }

    @FXML
    void comboBoxFacultyActon(ActionEvent event) {
        if (comboBoxFaculty.getValue() != null) {
            comboBoxGroupStudent.setDisable(true);
            comboBoxSpeciality.setDisable(true);
            comboBoxDepartment.getItems().clear();
            comboBoxDepartment.getItems().addAll(universityService.getAllDepartmentName(
                    universityService.getIdFacultyByName(comboBoxFaculty.getValue())
            ));
            if (comboBoxDepartment.getItems().size() != 0) {
                comboBoxDepartment.setDisable(false);
            }
        } else {
            comboBoxGroupStudent.setDisable(false);
            comboBoxSpeciality.setDisable(false);
        }
    }

    @FXML
    void comboBoxSpecialityAction(ActionEvent event) {
        if (comboBoxSpeciality.getValue() != null) {
            comboBoxGroupStudent.setDisable(false);
            Specialty specialty = universityService.getSpecialityByName(comboBoxSpeciality.getValue());
            if (specialty != null) {
                comboBoxGroupStudent.getItems().clear();
                comboBoxGroupStudent.getItems().addAll(
                        universityService.getAllGroupStudent(
                                specialty.getId())
                );
                if (comboBoxGroupStudent.getItems().size() == 0) {
                    comboBoxGroupStudent.setDisable(true);
                    comboBoxGroupStudent.setDisable(true);
                }
            }
        } else
            comboBoxGroupStudent.setDisable(true);
    }

    @FXML
    void registerButtonMouseClicked(MouseEvent e) {

    }

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            if (CurrentSession.user.getUserRole() != UserRole.ROLE_ADMIN || !CurrentSession.user.getUsername().equals("admin")) {
                underviewerService = new UnderwearService();
                roomService = new RoomService();
                occupantService = new OccupantService();
                hosteService = new HostelService();
                universityService = new UniversityService();
                checkBoxViewUnderviewer.getItems().addAll(underviewerService.getAllNameUnderwearObservableList());
                // Platform.runLater(() -> {
                hBoxStudent.setVisible(false);
                comboBoxDepartment.setDisable(true);
                comboBoxSpeciality.setDisable(true);
                comboBoxGroupStudent.setDisable(true);
                comboBoxTypeOccupation.getItems().addAll("Студент", "Сотрудник");
                comboBoxRoom.setDisable(true);
                comboBoxFloors.getItems().addAll(hosteService.getFloorsList(hosteService.getHostelByUserId(CurrentSession.user.getId()).getNameHostel()));
                comboBoxFaculty.getItems().addAll(universityService.getAllFacultyName());
                //   });
            }
        });
    }
}
