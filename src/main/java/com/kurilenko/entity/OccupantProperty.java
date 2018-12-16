package com.kurilenko.entity;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class OccupantProperty extends RecursiveTreeObject<OccupantProperty> {
    private LongProperty id;
    private StringProperty occupantName;
    private StringProperty roomNumber;
    private StringProperty typeOccupant;

    public OccupantProperty(Long id, String occupantName, String roomNumber, String typeOccupant){
        this.id = new SimpleLongProperty(id);
        this.occupantName = new SimpleStringProperty(occupantName);
        this.roomNumber = new SimpleStringProperty(roomNumber);
        this.typeOccupant = new SimpleStringProperty(typeOccupant);
    }
}
