package com.kurilenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
public class RoomSpecification {

  private Long numberOfBeds;
  private String typeOfRoom;
  private Long id;
}
