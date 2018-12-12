package com.kurilenko.controller;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RoomController {
    @FXML
    private JFXTreeTableView<RoomTable> tableViewRooms;


    JFXTreeTableColumn<RoomTable, String> roomFloor = new JFXTreeTableColumn<>("Этаж");
    JFXTreeTableColumn<RoomTable, String> numberRoom = new JFXTreeTableColumn<>("Номер");
    JFXTreeTableColumn<RoomTable, String> busyNumRoom = new JFXTreeTableColumn<>("Занято");
    JFXTreeTableColumn<RoomTable, String> summaryNumRoom = new JFXTreeTableColumn<>("Вместительность");
    JFXTreeTableColumn<RoomTable, String> specificationRoom = new JFXTreeTableColumn<>("Назначение");

    private MainController mainController;

    @FXML
    public void initialize() {
        roomFloor.setCellValueFactory((TreeTableColumn.CellDataFeatures<RoomTable, String> param) -> {
            if (roomFloor.validateValue(param)) {
                return param.getValue().getValue().numberRoom;
            } else {
                return roomFloor.getComputedValue(param);
            }
        });

        numberRoom.setCellValueFactory((TreeTableColumn.CellDataFeatures<RoomTable, String> param) -> {
            if (numberRoom.validateValue(param)) {
                return param.getValue().getValue().numberRoom;
            } else {
                return numberRoom.getComputedValue(param);
            }
        });

        busyNumRoom.setCellValueFactory((TreeTableColumn.CellDataFeatures<RoomTable, String> param) -> {
            if (busyNumRoom.validateValue(param)) {
                return param.getValue().getValue().busyNumRoom;
            } else {
                return busyNumRoom.getComputedValue(param);
            }
        });

        summaryNumRoom.setCellValueFactory((TreeTableColumn.CellDataFeatures<RoomTable, String> param) -> {
            if (summaryNumRoom.validateValue(param)) {
                return param.getValue().getValue().summaryNumRoom;
            } else {
                return summaryNumRoom.getComputedValue(param);
            }
        });

        specificationRoom.setCellValueFactory((TreeTableColumn.CellDataFeatures<RoomTable, String> param) -> {
            if (specificationRoom.validateValue(param)) {
                return param.getValue().getValue().specificationRoom;
            } else {
                return specificationRoom.getComputedValue(param);
            }
        });

        tableViewRooms.getColumns().addAll(roomFloor, numberRoom, busyNumRoom, summaryNumRoom, specificationRoom);
    }


    private class RoomTable extends RecursiveTreeObject<RoomTable> {
        LongProperty id;
        StringProperty roomFloor;
        StringProperty numberRoom;
        StringProperty busyNumRoom;
        StringProperty summaryNumRoom;
        StringProperty specificationRoom;

        RoomTable(Long id, String roomFloor, String numberRoom, String busyNumRoom, String summaryNumRoom, String specificationRoom) {
            this.id = new SimpleLongProperty(id);
            this.roomFloor = new SimpleStringProperty(roomFloor);
            this.numberRoom = new SimpleStringProperty(numberRoom);
            this.busyNumRoom = new SimpleStringProperty(busyNumRoom);
            this.summaryNumRoom = new SimpleStringProperty(summaryNumRoom);
            this.specificationRoom = new SimpleStringProperty(specificationRoom);
        }
    }
}
