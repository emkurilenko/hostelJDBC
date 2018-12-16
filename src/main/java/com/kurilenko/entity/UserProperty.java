package com.kurilenko.entity;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Data;


@Data
public class UserProperty extends RecursiveTreeObject<UserProperty> {
    StringProperty id;
    StringProperty username;
    StringProperty hostelName;
    StringProperty role;

    public UserProperty(Long id, String username, String hostelName, String role){
        this.id = new SimpleStringProperty(id.toString());
        this.username = new SimpleStringProperty(username);
        this.hostelName = new SimpleStringProperty(hostelName);
        this.role = new SimpleStringProperty(role);
    }
}
