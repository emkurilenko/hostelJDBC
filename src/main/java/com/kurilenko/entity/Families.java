package com.kurilenko.entity;

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
}
