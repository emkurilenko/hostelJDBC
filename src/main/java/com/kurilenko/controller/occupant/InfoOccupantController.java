package com.kurilenko.controller.occupant;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.kurilenko.dao.impl.GroupStudentsDAOImpl;
import com.kurilenko.entity.GroupStudents;
import com.kurilenko.entity.Occupant;
import com.kurilenko.entity.Student;
import com.kurilenko.service.EmployeesService;
import com.kurilenko.service.OccupantService;
import com.kurilenko.service.RoomService;
import com.kurilenko.service.StudentService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import lombok.Setter;

@Setter

public class InfoOccupantController {
    Long idOccupant;

    private OccupantService occupantService;
    private StudentService studentService;
    private EmployeesService employeesService;
    private GroupStudentsDAOImpl groupStudentsDAO;
    private Occupant occupant;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnCreateReport;

    @FXML
    private JFXTextField fieldFIO;

    @FXML
    private JFXTextField fieldTelephone;

    @FXML
    private JFXTextField fieldDateBirthday;

    @FXML
    private JFXTextField fieldType;

    @FXML
    private JFXTextField fieldGroup;

    @FXML
    private JFXTextField fieldFac;

    @FXML
    private JFXTextField fieldRoom;

    @FXML
    void btnCancelMouseClicked(MouseEvent event) {

    }

    @FXML
    void btnCreateReportMouseClicked(MouseEvent event) {

    }


    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            occupantService = new OccupantService();
            groupStudentsDAO = new GroupStudentsDAOImpl();
            RoomService roomService = new RoomService();
            occupant = occupantService.getById(idOccupant);
            fieldFIO.setText(occupant.getFirstName() + " " + occupant.getMiddleName() + " " + occupant.getLastName());
            fieldTelephone.setText(occupant.getTelephone());
            fieldDateBirthday.setText(occupant.getDateOfBirth().toString());
            fieldType.setText(occupant.getOccupantType());
            fieldRoom.setText(String.valueOf(roomService.getRoomNumberByIdOccupant(idOccupant)));

            if (occupant.getOccupantType().equals("Студент")) {
                studentService = new StudentService();
                Student student = studentService.getByID(idOccupant);
                GroupStudents groupStudents = groupStudentsDAO.getOneById(student.getFkGroup());
                fieldGroup.setText(groupStudents.getNameGroup());
                fieldFac.setText(groupStudentsDAO.getSELECT_FAC_BY_ID_GROUP(groupStudents.getId()));
            } else {
                employeesService = new EmployeesService();
            }
        });
    }
}
