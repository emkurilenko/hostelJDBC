package com.kurilenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
public class RoomInfo {
    private Long id;
    private Long numberRoom;
    private Long floorNumber;
    private String roomSpecification;
    private Long occupiedBeds;
    private Long numberofbeds;
}
