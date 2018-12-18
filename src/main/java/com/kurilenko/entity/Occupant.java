package com.kurilenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

@AllArgsConstructor
public class Occupant {

  private Long id;
  private String firstName;
  private String middleName;
  private String lastName;
  private LocalDate dateOfBirth;
  private String telephone;
  private String gender;
  private String occupantType;
}
