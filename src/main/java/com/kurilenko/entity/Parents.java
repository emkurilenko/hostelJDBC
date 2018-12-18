package com.kurilenko.entity;

import com.kurilenko.entity.enums.StatusFamily;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
public class Parents {

  private Long id;
  private String firstName;
  private String middleName;
  private String lastName;
  private StatusFamily status;
}
