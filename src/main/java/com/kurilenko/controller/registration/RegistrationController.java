package com.kurilenko.controller.registration;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.kurilenko.entity.*;
import com.kurilenko.service.*;
import com.kurilenko.utils.CurrentSession;
import com.kurilenko.utils.HelperWorkWithModalityWindow;
import com.kurilenko.utils.ValifationData;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import org.controlsfx.control.CheckComboBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RegistrationController {
    private ContractService contractService;
    private EmployeesService employeesService;
    private OccupantService occupantService;
    private UniversityService universityService;
    private RoomService roomService;
    private HostelService hosteService;
    private UnderwearService underviewerService;
    private StudentService studentService;
    private ParentService parentService;

    private List<String> listUnderwear;

    @FXML
    private CheckComboBox<String> checkBoxViewUnderviewer;

    @FXML
    private BorderPane registrationTab;

    @FXML
    private VBox vBoxFamiles;

    @FXML
    private JFXTextField fieldNumberContract;

    @FXML
    private JFXTextField fieldName;

    @FXML
    private JFXTextField fieldLastName;

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
    private JFXTextField numberZachetki;


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
    private JFXComboBox<String> comboBoxGender;

    @FXML
    private JFXDatePicker dateDBPicker;

    @FXML
    private JFXDatePicker dateOfSettlement;

    @FXML
    private JFXDatePicker dateOfEviction;

    @FXML
    private JFXDatePicker dateCreationContract;

    @FXML
    void comboBoxGenderAction(ActionEvent e) {

    }

    @FXML
    void comboBoxFloorsAction(ActionEvent event) {

        comboBoxRoom.getItems().clear();
        comboBoxRoom.setDisable(false);
        comboBoxRoom.getItems().addAll(roomService.getAllRoomNumberFree(Long.valueOf(comboBoxFloors.getValue())));

    }

    @FXML
    void comboBoxTypeOccupationAction(ActionEvent event) {
        if (comboBoxTypeOccupation.getValue().equals("Студент")) {
            vBoxFamiles.setVisible(true);
            hBoxStudent.setVisible(true);
        } else {
            vBoxFamiles.setVisible(false);
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

    //Код очень не красивый
    @FXML
    void registerButtonMouseClicked(MouseEvent e) {
        if (fieldName.getText() != null && fieldMiddleName.getText() != null && fieldLastName.getText() != null &&
                dateDBPicker.getValue() != null && fieldTelephone.getText() != null &&
                comboBoxGender.getValue() != null && comboBoxFloors != null && comboBoxRoom != null &&
                comboBoxTypeOccupation.getValue() != null && comboBoxFaculty.getValue() != null &&
                comboBoxDepartment.getValue() != null && fieldNumberInFamily.getText() != null && fieldNumberContract != null &&
                dateOfSettlement.getValue() != null && dateOfEviction.getValue() != null && dateCreationContract.getValue() != null) {
            String fio = fieldName.getText() + " " + fieldMiddleName.getText() + " " + fieldLastName.getText();
            if (ValifationData.validFio(fio) && ValifationData.validTelephon(fieldTelephone.getText())) {
                if (comboBoxTypeOccupation.getValue().equals("Студент")) {
                    if (comboBoxSpeciality.getValue() != null && comboBoxGroupStudent.getValue() != null && fileldMother.getText() != null
                            && fieldFather.getText() != null && fieldNumberInFamily.getText() != null && numberZachetki.getText() != null) {
                        if (ValifationData.validFio(fieldFather.getText()) && ValifationData.validFio(fileldMother.getText())) {
                            Occupant occupant = new Occupant(0L, fieldName.getText(),
                                    fieldMiddleName.getText(), fieldLastName.getText(), dateDBPicker.getValue(),
                                    fieldTelephone.getText(), comboBoxGender.getValue(), comboBoxTypeOccupation.getValue());
                            Long idOccupant = occupantService.save(occupant);
                            if (idOccupant > 0) {
                                GroupStudents groupStudentByName = universityService.getGroupStudentByName(comboBoxGroupStudent.getValue());
                                if (groupStudentByName != null) {
                                    Student student = new Student(idOccupant, numberZachetki.getText(), groupStudentByName.getId());
                                    Long idStudent = studentService.save(student);
                                    if (idStudent != 0) {
                                        String[] mother = fileldMother.getText().split(" ");
                                        String[] father = fieldFather.getText().split(" ");
                                        Long idMother = parentService.saveWithIdStudent(idStudent, getParrent(mother, "мать"));
                                        Long idFather = parentService.saveWithIdStudent(idStudent, getParrent(father, "отец"));
                                        if (idMother > 0 && idFather > 0) {
                                            Contract contract = new Contract(0L, fieldNumberContract.getText(), dateCreationContract.getValue(), idOccupant);
                                            Settlement settlement = new Settlement(0L, dateOfSettlement.getValue(), dateOfEviction.getValue(), 0L, roomService.getRoomByNumberRoom(comboBoxRoom.getValue()).getId());
                                            Long idContract = contractService.createContractWithSettlement(contract, settlement);
                                            if (idContract != 0) {
                                                if(!listUnderwear.isEmpty()){
                                                    listUnderwear.forEach(un -> underviewerService.saveChoiceUnderwearByContract(un, idContract));
                                                }
                                                roomService.incrementRoomInNumber(comboBoxRoom.getValue());
                                                HelperWorkWithModalityWindow.showDialog("Все ок!", "Успешное добавление", Alert.AlertType.INFORMATION);
                                                clearFields();
                                            } else {
                                                parentService.deleteParent(idFather);
                                                parentService.deleteParent(idMother);
                                                studentService.delete(idStudent);
                                                occupantService.delete(idOccupant);
                                                HelperWorkWithModalityWindow.showDialog("Ошибка", "Ошибка создания договора", Alert.AlertType.WARNING);
                                                clearFields();
                                            }
                                        } else {
                                            HelperWorkWithModalityWindow.showDialog("Ошибка", "Ошибка сохранения информации о родителях в бд", Alert.AlertType.WARNING);
                                            parentService.deleteParent(idFather);
                                            parentService.deleteParent(idMother);
                                            studentService.delete(idStudent);
                                            occupantService.delete(idOccupant);
                                            clearFields();
                                        }
                                    } else {
                                        HelperWorkWithModalityWindow.showDialog("Ошибка", "Ошибка сохранения инфы о студенте", Alert.AlertType.WARNING);
                                        studentService.delete(idStudent);
                                        clearFields();
                                    }
                                }
                            } else {
                                HelperWorkWithModalityWindow.showDialog("Ошибка", "Данный житель уже существует", Alert.AlertType.WARNING);
                                occupantService.delete(idOccupant);
                                clearFields();
                            }
                        }
                    }
                } else {
                    Occupant occupant = new Occupant(0L, fieldName.getText(),
                            fieldMiddleName.getText(), fieldLastName.getText(), dateDBPicker.getValue(),
                            fieldTelephone.getText(), comboBoxGender.getValue(), comboBoxTypeOccupation.getValue());
                    Long idOccupant = occupantService.save(occupant);
                    if (idOccupant > 0) {
                        Department department = universityService.getDepartmentByName(comboBoxDepartment.getValue());
                        if (department != null) {
                            Employees employees = new Employees(idOccupant, department.getId(), Long.valueOf(fieldNumberInFamily.getText()));
                            Long idEmployes = employeesService.save(employees);
                            if (idEmployes > 0) {
                                Contract contract = new Contract(0L, fieldNumberContract.getText(), dateCreationContract.getValue(), idOccupant);
                                Settlement settlement = new Settlement(0L, dateOfSettlement.getValue(), dateOfEviction.getValue(), 0L, roomService.getRoomByNumberRoom(comboBoxRoom.getValue()).getId());
                                Long idContract = contractService.createContractWithSettlement(contract, settlement);
                                if (idContract != 0) {
                                    if(!listUnderwear.isEmpty()){
                                        listUnderwear.forEach(un -> underviewerService.saveChoiceUnderwearByContract(un, idContract));
                                    }
                                    roomService.incrementRoomInNumber(comboBoxRoom.getValue());
                                    HelperWorkWithModalityWindow.showDialog("Все ок!", "Успешное добавление", Alert.AlertType.INFORMATION);
                                    clearFields();
                                } else {
                                    employeesService.delete(idEmployes);
                                    occupantService.delete(idOccupant);
                                    HelperWorkWithModalityWindow.showDialog("Ошибка", "Ошибка создания договора", Alert.AlertType.WARNING);
                                    clearFields();
                                }
                            } else {
                                HelperWorkWithModalityWindow.showDialog("Ошибка", "Ошибка сохранения", Alert.AlertType.WARNING);
                                employeesService.delete(idEmployes);
                                clearFields();
                            }
                        }
                    } else {
                        HelperWorkWithModalityWindow.showDialog("Ошибка", "Данный житель уже существует", Alert.AlertType.WARNING);
                        occupantService.delete(idOccupant);
                        clearFields();
                    }
                }
            }
            else {
                HelperWorkWithModalityWindow.showDialog("Ошибка", "Не все поля заполены, либо некорректны данные", Alert.AlertType.ERROR);
            }
        }else {
            HelperWorkWithModalityWindow.showDialog("Ошибка", "Не все поля заполены", Alert.AlertType.ERROR);
        }
    }

    private Parents getParrent(String[] fields, String status) {
        return new Parents(0L, fields[0], fields[1], fields[2], status);
    }

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            if (!CurrentSession.user.getUsername().equals("admin")) {
                contractService = new ContractService();
                parentService = new ParentService();
                employeesService = new EmployeesService();
                studentService = new StudentService();
                underviewerService = new UnderwearService();
                listUnderwear = new ArrayList<>();
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
                comboBoxGender.getItems().addAll("MALE", "FEMALE");
                vBoxFamiles.setVisible(false);
                checkBoxViewUnderviewer.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
                    public void onChanged(ListChangeListener.Change<? extends String> c) {
                        listUnderwear.clear();
                        listUnderwear.addAll(checkBoxViewUnderviewer.getCheckModel().getCheckedItems());
                        System.out.println(checkBoxViewUnderviewer.getCheckModel().getCheckedItems());
                    }
                });
                dateDBPicker.setConverter(converterForDate);
                dateOfEviction.setConverter(converterForDate);
                dateOfSettlement.setConverter(converterForDate);
                dateCreationContract.setConverter(converterForDate);
            }
        });
    }

    private StringConverter<LocalDate> converterForDate = new StringConverter<LocalDate>() {
        String pattern = "dd.MM.yyyy";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

         {
             /*dateDBPicker.setPromptText(pattern.toLowerCase());
             dateOfEviction.setPromptText(pattern.toLowerCase());
             dateOfSettlement.setPromptText(pattern.toLowerCase());
             dateCreationContract.setPromptText(pattern.toLowerCase());*/
         }

        @Override
        public String toString(LocalDate object) {
            if (object != null) {
                return dateFormatter.format(object);
            } else {
                return "";
            }
        }

        @Override
        public LocalDate fromString(String string) {
            if (string != null && !string.isEmpty()) {
                return LocalDate.parse(string, dateFormatter);
            } else {
                return null;
            }
        }
    };

    private void clearFields(){
        fieldName.clear();
        fieldNumberContract.clear();
        fieldFather.clear();
        fieldNumberInFamily.clear();
        fieldTelephone.clear();
        fieldLastName.clear();
        fieldMiddleName.clear();
        fileldMother.clear();

        comboBoxGroupStudent.getSelectionModel().clearSelection();
        comboBoxSpeciality.getSelectionModel().clearSelection();
        //comboBoxFaculty.getSelectionModel().clearSelection();
        //comboBoxTypeOccupation.getSelectionModel().clearSelection();
        //comboBoxDepartment.getSelectionModel().clearSelection();
        //comboBoxRoom.getSelectionModel().clearSelection();
        //comboBoxGender.getSelectionModel().clearSelection();
        //comboBoxFloors.getSelectionModel().clearSelection();
        dateCreationContract.setValue(null);
        dateOfSettlement.setValue(null);
        dateOfEviction.setValue(null);
        dateDBPicker.setValue(null);
    }
}

