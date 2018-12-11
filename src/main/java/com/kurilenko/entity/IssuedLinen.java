package com.kurilenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
public class IssuedLinen {

  private Long fkContract;
  private Long fkUnderwear;
}
