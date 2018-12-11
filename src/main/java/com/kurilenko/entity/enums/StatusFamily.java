package com.kurilenko.entity.enums;

import lombok.Getter;

@Getter

public enum StatusFamily {
    MOTHER("мать"), FATHER("отец"), SON("сын"), DAUGHTER("дочь");

    private final String type;

    StatusFamily(String type) {
        this.type = type;
    }
}
