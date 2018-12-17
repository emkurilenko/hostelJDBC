package com.kurilenko.entity;

import lombok.*;

@Getter
@Setter

@ToString

@NoArgsConstructor
@AllArgsConstructor
public class Room {

  private Long id;
  private Long numberRoom;
  private Double square;
  private Long roomSpecification;
  private Long number;
  private Long fkHostel;
  private Long floors;
}
