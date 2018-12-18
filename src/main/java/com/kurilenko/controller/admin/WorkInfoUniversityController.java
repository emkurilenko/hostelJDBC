package com.kurilenko.controller.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.kurilenko.entity.Department;
import com.kurilenko.entity.GroupStudents;
import com.kurilenko.entity.Specialty;
import com.kurilenko.service.UniversityService;
import com.kurilenko.utils.HelperWorkWithModalityWindow;
import com.kurilenko.utils.ValifationData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class WorkInfoUniversityController {
    private UniversityService universityService;

    @FXML
    private AnchorPane anhorPaneUn;


    @FXML
    private JFXButton btnAddButton;

    @FXML
    private JFXButton btnDeleteGroup;

    @FXML
    private JFXComboBox<String> comBoxFaculty;

    @FXML
    private JFXComboBox<String> comboBoxDepartment;

    @FXML
    private JFXComboBox<String> comboBoxSpeciality;

    @FXML
    private JFXToggleButton toggleButtonNewSpeciality;

    @FXML
    private JFXComboBox<String> comboBoxGroupStudent;

    @FXML
    private JFXToggleButton toggleButtonNewGroup;

    @FXML
    private JFXTextField fieldGroupStudent;
    @FXML
    private JFXTextField fieldNewSpeciality;

    @FXML
    void btnAddButtonMouseClicked(MouseEvent event) {
        if (toggleButtonNewGroup.selectedProperty().get() &&
                !fieldGroupStudent.getText().equals("") &&
                !fieldGroupStudent.getText().isEmpty() &&
                ValifationData.validGroup(fieldGroupStudent.getText())) {
            Specialty specialty = universityService.getSpecialityByName(comboBoxSpeciality.getValue());
            if (specialty != null) {
                GroupStudents groupStudents = new GroupStudents();
                groupStudents.setFkSpecialty(specialty.getId());
                groupStudents.setNameGroup(fieldGroupStudent.getText());

                if (universityService.saveGroup(groupStudents) != 0) {
                    Stage stage = (Stage) anhorPaneUn.getScene().getWindow();
                    stage.close();
                } else {
                    HelperWorkWithModalityWindow.showDialog("Ошибка!", "Ошибка сохранения", Alert.AlertType.WARNING);
                }
            }
        }
        if (toggleButtonNewSpeciality.selectedProperty().get() && !fieldNewSpeciality.getText().equals("") && !fieldNewSpeciality.getText().isEmpty()) {
            Department department = universityService.getDepartmentByName(comboBoxDepartment.getValue());
            if(department != null){
                Specialty specialty = new Specialty();
                specialty.setIdDepartment(department.getId());
                specialty.setNameSpecialty(fieldNewSpeciality.getText());
                if(universityService.saveSpecialty(specialty) > 0){
                    Stage stage = (Stage)anhorPaneUn.getScene().getWindow();
                    stage.close();
                } else {
                    HelperWorkWithModalityWindow.showDialog("Ошибка!", "Ошибка сохранения", Alert.AlertType.WARNING);
                }
            }
        }
    }

    @FXML
    void btnCancelMouseClicked(MouseEvent event) {
        Stage stage = (Stage) anhorPaneUn.getScene().getWindow();
        stage.close();
    }


    @FXML
    void btnDeleteGroupMouseClicked(MouseEvent event) {

    }

    @FXML
    void comBoxFacultyAction(ActionEvent event) {
        comboBoxDepartment.setDisable(false);
        comboBoxDepartment.getItems().clear();
        comboBoxDepartment.getItems().addAll(universityService.getAllDepartmentName(
                universityService.getIdFacultyByName(comBoxFaculty.getValue())
        ));

    }

    @FXML
    void comboBoxGroupStudentAction(ActionEvent event) {
        /*if (!toggleButtonNewGroup.selectedProperty().get()) {
            if (comboBoxSpeciality.getValue() != null) {
                Specialty specialty = universityService.getSpecialityByName(comboBoxSpeciality.getValue());
                if (specialty != null) {
                    comboBoxGroupStudent.getItems().clear();
                    comboBoxGroupStudent.getItems().addAll(universityService.getAllGroupStudent(specialty.getId()));
                    comboBoxGroupStudent.setDisable(false);
                }
            }
        }*/
    }

    @FXML
    void comboBoxDepartmentAction(ActionEvent event) {
        if (comboBoxDepartment.getValue() != null) {
            comboBoxSpeciality.setDisable(true);
            toggleButtonNewSpeciality.setDisable(false);
            Department department = universityService.getDepartmentByName(comboBoxDepartment.getValue());
            if (department != null) {
                comboBoxSpeciality.getItems().clear();
                comboBoxSpeciality.getItems().addAll(universityService.getAllSpecialityName(department.getId()));
                if (comboBoxSpeciality.getItems().size() != 0) {
                    comboBoxSpeciality.setDisable(false);
                    toggleButtonNewSpeciality.selectedProperty().setValue(false);
                } else {
                    toggleButtonNewSpeciality.selectedProperty().setValue(true);
                    toggleButtonNewSpeciality.setDisable(true);
                    fieldNewSpeciality.setDisable(false);
                }

            }
        }
    }

    @FXML
    void comboBoxSpecialityAction(ActionEvent event) {
        if (!toggleButtonNewSpeciality.selectedProperty().get()) {
            comboBoxGroupStudent.setDisable(true);
            if (comboBoxSpeciality.getValue() != null) {
                Specialty specialty = universityService.getSpecialityByName(comboBoxSpeciality.getValue());
                if (specialty != null) {
                    comboBoxGroupStudent.getItems().clear();
                    comboBoxGroupStudent.getItems().addAll(universityService.getAllGroupStudent(
                            specialty.getId()
                    ));
                    if (comboBoxGroupStudent.getItems().size() != 0) {
                        comboBoxGroupStudent.setDisable(false);
                        toggleButtonNewGroup.selectedProperty().setValue(false);
                        toggleButtonNewGroup.setDisable(false);
                    } else {
                        toggleButtonNewGroup.setDisable(true);
                        toggleButtonNewGroup.selectedProperty().setValue(true);
                        fieldGroupStudent.setDisable(false);
                    }
                }
            }
        }
    }

    @FXML
    void toggleButtonNewGroupMouseClicked(MouseEvent event) {

    }

    @FXML
    void toggleButtonNewSpecialityMouseClicked(MouseEvent event) {

    }

    @FXML
    public void initialize() {
        universityService = new UniversityService();
        comboBoxDepartment.setDisable(true);
        comBoxFaculty.getItems().addAll(universityService.getAllFacultyName());
        comboBoxSpeciality.setDisable(true);
        toggleButtonNewSpeciality.setDisable(true);
        fieldNewSpeciality.setDisable(true);
        comboBoxGroupStudent.setDisable(true);
        toggleButtonNewGroup.setDisable(true);
        fieldGroupStudent.setDisable(true);
        btnDeleteGroup.setDisable(true);

        toggleButtonNewSpeciality.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (toggleButtonNewSpeciality.selectedProperty().get()) {
                    fieldNewSpeciality.setDisable(false);
                    comboBoxSpeciality.setDisable(true);
                    toggleButtonNewGroup.selectedProperty().setValue(false);
                    toggleButtonNewGroup.setDisable(true);
                    toggleButtonNewGroup.selectedProperty().setValue(false);
                    fieldGroupStudent.setDisable(true);
                } else {
                    toggleButtonNewGroup.setDisable(false);
                    fieldNewSpeciality.setDisable(true);
                    comboBoxSpeciality.setDisable(false);
                }
            }
        });

        toggleButtonNewGroup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (toggleButtonNewGroup.selectedProperty().get() && !toggleButtonNewSpeciality.selectedProperty().get()) {
                    fieldGroupStudent.setDisable(false);
                    comboBoxGroupStudent.setDisable(true);
                } else {
                    fieldGroupStudent.setDisable(true);
                    comboBoxGroupStudent.setDisable(false);
                }
            }
        });
    }

}
