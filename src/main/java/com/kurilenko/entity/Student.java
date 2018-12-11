package com.kurilenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
public class Student {

  private long id;
  private String recordNumber;
  private long fkGroup;
}
