package com.kurilenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
public class Specialty {

  private Long id;
  private String nameSpecialty;
  private Long idDepartment;
}
