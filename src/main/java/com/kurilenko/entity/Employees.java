package com.kurilenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
public class Employees {

  private Long id;
  private Long fkDepartment;
  private Long numberOfFamilyMembers;
}
