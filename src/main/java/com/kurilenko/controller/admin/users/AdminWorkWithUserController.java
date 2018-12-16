package com.kurilenko.controller.admin.users;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.kurilenko.entity.UserProperty;
import com.kurilenko.service.UserService;
import com.kurilenko.utils.HelperWorkWithModalityWindow;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.function.Function;

public class AdminWorkWithUserController {
    private final String uriUserAddFXML = "fxml/admin/add_user.fxml";
    @FXML
    private JFXButton btnBack;

    ObservableList<UserProperty> data;

    @FXML
    private JFXButton btnAddUser;
    @FXML
    private JFXButton btnEditUser;
    @FXML
    private JFXButton btnDelUser;

    @FXML
    private JFXTreeTableView<UserProperty> tableUsers;

    JFXTreeTableColumn<UserProperty, String> idUser;
    JFXTreeTableColumn<UserProperty, String> userName;
    JFXTreeTableColumn<UserProperty, String> hostelName;
    JFXTreeTableColumn<UserProperty, String> userRole;

    private UserService userService;

    @FXML
    private void initialize() {
        userService = new UserService();
        setupTableView();
    }

    private void setupTableView(){
        idUser = new JFXTreeTableColumn<>("ID");
        userName = new JFXTreeTableColumn<>("user_name");
        hostelName = new JFXTreeTableColumn<>("hostel_name");
        userRole = new JFXTreeTableColumn<>("user_role");

        setupCellValueFactory(idUser,UserProperty::getId);
        setupCellValueFactory(userName,UserProperty::getUsername);
        setupCellValueFactory(hostelName,UserProperty::getHostelName);
        setupCellValueFactory(userRole,UserProperty::getRole);

        refreshDataOnTable();

        tableUsers.setShowRoot(false);

        btnDelUser.disableProperty().bind(Bindings.equal(-1, tableUsers.getSelectionModel().selectedIndexProperty()));
        btnEditUser.disableProperty().bind(Bindings.equal(-1, tableUsers.getSelectionModel().selectedIndexProperty()));

        btnEditUser.setOnMouseClicked((e) -> {
            UserProperty choise = data.get(tableUsers.getSelectionModel().selectedIndexProperty().get());
            UserEditController userEditController = new UserEditController();
            userEditController.setUserInfo(choise);
            try {
                HelperWorkWithModalityWindow.loadNewModalityWindowWithController(uriUserAddFXML, "Редактирование",
                        ((Node) e.getSource()).getScene().getWindow(), userEditController);
            }catch (IOException ex){
                System.out.println(ex.getMessage());
            }
            refreshDataOnTable();
        });

        btnDelUser.setOnMouseClicked((e) ->{
            UserProperty delUser = data.get(tableUsers.getSelectionModel().selectedIndexProperty().get());
            data.remove(tableUsers.getSelectionModel().selectedIndexProperty().get());
            userService.delete(Long.valueOf(delUser.getId().getValue()));
        });

        tableUsers.getColumns().setAll(idUser, userName, hostelName, userRole);
    }

    private void refreshDataOnTable(){
        data = userService.getAllUserProperty();
        tableUsers.setRoot(new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren));
    }

    private <T> void setupCellValueFactory(JFXTreeTableColumn<UserProperty, T> column, Function<UserProperty, ObservableValue<T>> mapper) {
        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<UserProperty, T> param) -> {
                    if (column.validateValue(param)) {
                        return mapper.apply(param.getValue().getValue());
                    } else {
                        return column.getComputedValue(param);
                    }
                }
        );
    }

    @FXML
    void btnAddUserMouseClicked(MouseEvent event) throws IOException {
        UserAddController userAddController = new UserAddController();
        HelperWorkWithModalityWindow.loadNewModalityWindowWithController(uriUserAddFXML, "Добавление нового пользователя",
                ((Node) event.getSource()).getScene().getWindow(), userAddController);
        refreshDataOnTable();
    }

    @FXML
    void btnBackMMouseClicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/admin/admin_setting.fxml"));
        btnBack.getScene().setRoot(root);
    }
}
