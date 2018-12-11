package com.kurilenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
public class GroupStudents {

  private Long id;
  private String nameGroup;
  private Long fkSpecialty;
}
