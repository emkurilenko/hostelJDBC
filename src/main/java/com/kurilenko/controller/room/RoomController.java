package com.kurilenko.controller.room;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.kurilenko.controller.MainController;
import com.kurilenko.entity.RoomProperty;
import com.kurilenko.entity.User;
import com.kurilenko.service.RoomService;
import com.kurilenko.utils.CurrentSession;
import com.kurilenko.utils.HelperWorkWithModalityWindow;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.function.Function;

@Getter
@Setter

public class RoomController {
    @FXML
    private JFXTreeTableView<RoomProperty> tableViewRooms;

    private ObservableList<RoomProperty> roomsInTable = FXCollections.observableArrayList();

    private MainController mainController;

    private ObservableList<RoomProperty> data;
    private RoomService roomService;

    JFXTreeTableColumn<RoomProperty, String> roomFloor;
    JFXTreeTableColumn<RoomProperty, String> numberRoom;
    JFXTreeTableColumn<RoomProperty, String> busyNumRoom;
    JFXTreeTableColumn<RoomProperty, String> summaryNumRoom;
    JFXTreeTableColumn<RoomProperty, String> specificationRoom;

    private User currentUser;

    @FXML
    private JFXButton btnAddRoom;

    @FXML
    private JFXButton btnInfoRoom;

    @FXML
    private JFXButton btnDelRoom;

    @FXML
    public void initialize() {
        /*Platform.runLater(() -> {
            currentUser = CurrentSession.user;
        });*/
        currentUser = CurrentSession.user;
        roomService = new RoomService();
        initializeTable();
    }

    private <T> void setupCellValueFactory(JFXTreeTableColumn<RoomProperty, T> column, Function<RoomProperty, ObservableValue<T>> mapper) {
        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<RoomProperty, T> param) -> {
                    if (column.validateValue(param)) {
                        return mapper.apply(param.getValue().getValue());
                    } else {
                        return column.getComputedValue(param);
                    }
                }
        );
    }

    private void initializeTable() {
       /* if (currentUser.getUserRole() != UserRole.ROLE_ADMIN) {
            btnDelRoom.setDisable(false);
        }*/
        roomFloor = new JFXTreeTableColumn<>("Этаж");
        numberRoom = new JFXTreeTableColumn<>("Номер");
        busyNumRoom = new JFXTreeTableColumn<>("Занято");
        summaryNumRoom = new JFXTreeTableColumn<>("Вместительность");
        specificationRoom = new JFXTreeTableColumn<>("Назначение");

        roomFloor.setPrefWidth(100);
        numberRoom.setPrefWidth(150);
        busyNumRoom.setPrefWidth(150);
        summaryNumRoom.setPrefWidth(150);
        specificationRoom.setPrefWidth(150);

        setupCellValueFactory(roomFloor, RoomProperty::getRoomFloor);
        setupCellValueFactory(numberRoom, RoomProperty::getNumberRoom);
        setupCellValueFactory(busyNumRoom, RoomProperty::getBusyNumRoom);
        setupCellValueFactory(summaryNumRoom, RoomProperty::getSummaryNumRoom);
        setupCellValueFactory(specificationRoom, RoomProperty::getSpecificationRoom);

        refreshData();
        tableViewRooms.setShowRoot(false);


        btnDelRoom.disableProperty().bind(Bindings.equal(-1, tableViewRooms.getSelectionModel().selectedIndexProperty()));

        btnDelRoom.setOnMouseClicked(event -> {
            RoomProperty roomProperty = data.get(tableViewRooms.getSelectionModel().selectedIndexProperty().get());
            data.remove(tableViewRooms.getSelectionModel().selectedIndexProperty().get());
            roomService.delete(roomProperty);
        });

        tableViewRooms.getColumns().setAll(roomFloor, numberRoom, busyNumRoom, summaryNumRoom, specificationRoom);
    }


    private void refreshData() {
        data = roomService.getAllInfoRoom(1L);
        tableViewRooms.setRoot(new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren));
    }

    @FXML
    void btnAddRoomMouseClicked(MouseEvent event) {
        try {
            HelperWorkWithModalityWindow.loadNewModalityWindow("fxml/add/add_room.fxml", "Добавление комнаты", ((Node) event.getSource()).getScene().getWindow());
            refreshData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btnDelRoomMouseClicked(MouseEvent event) {

    }

    @FXML
    void btnInfoRoomMouseClicked(MouseEvent event) {
        try {
            HelperWorkWithModalityWindow.loadNewModalityWindow("fxml/info/info_room.fxml", "Информация", ((Node) event.getSource()).getScene().getWindow());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
