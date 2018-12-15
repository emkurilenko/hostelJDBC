package com.kurilenko.entity;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Data;

@Data

public class RoomProperty extends RecursiveTreeObject<RoomProperty> {
    StringProperty id;
    StringProperty roomFloor;
    StringProperty numberRoom;
    StringProperty busyNumRoom;
    StringProperty summaryNumRoom;
    StringProperty specificationRoom;

    public RoomProperty(Long id, Long roomFloor, Long numberRoom, Long busyNumRoom, Long summaryNumRoom, String specificationRoom) {
        this.id = new SimpleStringProperty(id.toString());
        this.roomFloor = new SimpleStringProperty(roomFloor.toString());
        this.numberRoom = new SimpleStringProperty(numberRoom.toString());
        this.busyNumRoom = new SimpleStringProperty(busyNumRoom.toString());
        this.summaryNumRoom = new SimpleStringProperty(summaryNumRoom.toString());
        this.specificationRoom = new SimpleStringProperty(specificationRoom);
    }
}
