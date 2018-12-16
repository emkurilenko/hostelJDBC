package com.kurilenko.controller.occupant;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.kurilenko.entity.OccupantProperty;
import com.kurilenko.utils.CurrentSession;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.BorderPane;

import java.util.function.Function;

public class OccupantController {

    private JFXTreeTableColumn<OccupantProperty, String> occupantName;
    private JFXTreeTableColumn<OccupantProperty, String> roomNumber;
    private JFXTreeTableColumn<OccupantProperty, String> typeOccupant;

    @FXML
    private BorderPane occupantTab;

    private ObservableList<OccupantProperty> data;

    @FXML
    private JFXTreeTableView<OccupantProperty> tableViewOccupant;

    @FXML
    private void initialize() {

        setupTableView();
        Platform.runLater(() -> {
            System.out.println(CurrentSession.user);
        });
    }

    private void setupTableView() {
        occupantName = new JFXTreeTableColumn<>("ФИО");
        roomNumber = new JFXTreeTableColumn<>("Комната");
        typeOccupant = new JFXTreeTableColumn<>("Тип жильца");

        occupantName.setPrefWidth(200);

        setupCellValueFactory(occupantName, OccupantProperty::getOccupantName);
        setupCellValueFactory(roomNumber, OccupantProperty::getRoomNumber);
        setupCellValueFactory(typeOccupant, OccupantProperty::getTypeOccupant);

        tableViewOccupant.setShowRoot(false);

        // refreshDataOnTable();

        tableViewOccupant.getColumns().setAll(occupantName, roomNumber, typeOccupant);
    }

    private <T> void setupCellValueFactory(JFXTreeTableColumn<OccupantProperty, T> column, Function<OccupantProperty, ObservableValue<T>> mapper) {
        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<OccupantProperty, T> param) -> {
                    if (column.validateValue(param)) {
                        return mapper.apply(param.getValue().getValue());
                    } else {
                        return column.getComputedValue(param);
                    }
                }
        );
    }

    private void refreshDataOnTable() {
        tableViewOccupant.setRoot(new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren));
    }
}
