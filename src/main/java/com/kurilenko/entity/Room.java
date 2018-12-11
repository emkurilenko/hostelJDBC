package com.kurilenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
public class Room {

  private Long id;
  private Long numberRoom;
  private Double square;
  private Long fkFloors;
  private Long fkRoomSpecification;
  private Long number;
  private Long fkHostel;
}
