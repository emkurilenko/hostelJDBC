package com.kurilenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter

@AllArgsConstructor
public class Contract {

  private Long id;
  private String number;
  private Date dateOfConclusion;
  private Long fkOccupant;
}
