package com.kurilenko.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class OccupantInfo {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Long roomNumber;
    private String typeOccupant;
}
