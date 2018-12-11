package com.kurilenko.entity.enums;

import lombok.Getter;

@Getter

public enum Gender {
    MALE("мужчина"), FEMAIE("женщина");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

}
