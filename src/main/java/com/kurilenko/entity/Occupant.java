package com.kurilenko.entity;

import com.kurilenko.entity.enums.Gender;
import com.kurilenko.entity.enums.OccupantType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter

@AllArgsConstructor
public class Occupant {

  private Long id;
  private String firstName;
  private String middleName;
  private String lastName;
  private Date dateOfBirth;
  private String telephone;
  private Gender gender;
  private OccupantType occupantType;
}
