package com.kurilenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
public class Department {

  private Long id;
  private String nameDepartment;
  private Long idFaculty;
}
