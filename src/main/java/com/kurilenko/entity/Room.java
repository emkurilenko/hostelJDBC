package com.kurilenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class Room {

  private Long id;
  private Long numberRoom;
  private Double square;
  private Long Floors;
  private Long roomSpecification;
  private Long number;
  private Long fkHostel;
}
