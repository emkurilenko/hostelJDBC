package com.kurilenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter

@AllArgsConstructor
public class Payment {

  private Long id;
  private Long fkContract;
  private Date datePayment;
  private Double sum;
  private Boolean paid;
}
