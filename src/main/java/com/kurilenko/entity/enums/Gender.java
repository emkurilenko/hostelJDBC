package com.kurilenko.entity.enums;

import lombok.Getter;

@Getter

public enum Gender {
    MALE("МУЖЧИНА"), FEMALE("ЖЕНЩИНА");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }
}
