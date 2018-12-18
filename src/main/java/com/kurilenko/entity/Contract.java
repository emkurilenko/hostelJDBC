package com.kurilenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

@AllArgsConstructor
public class Contract {

  private Long id;
  private String number;
  private LocalDate dateOfConclusion;
  private Long fkOccupant;
}
