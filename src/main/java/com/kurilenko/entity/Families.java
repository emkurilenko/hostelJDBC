package com.kurilenko.entity;

import com.kurilenko.entity.enums.StatusFamily;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
public class Families {

  private Long id;
  private Long fkParent;
  private Long fkStudent;
  private StatusFamily status;
}
