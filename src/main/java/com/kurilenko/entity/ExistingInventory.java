package com.kurilenko.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter

@AllArgsConstructor
public class ExistingInventory {

  private Long id;
  private Long fkRoom;
  private Long fkInventory;
  private Date dateOfEntry;
}
