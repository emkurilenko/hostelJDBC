package com.kurilenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

@AllArgsConstructor
public class Settlement {

  private Long id;
  private LocalDate dateOfSettlement;
  private LocalDate dateOfEviction_;
  private Long fkContract;
  private Long fkRoom;
}
