package com.kurilenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter

@AllArgsConstructor
public class Settlement {

  private Long id;
  private Date dateOfSettlement;
  private Date dateOfEviction_;
  private Long fkContract;
  private Long fkRoom;
}
