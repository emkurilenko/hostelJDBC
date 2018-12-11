package com.kurilenko.entity.enums;

public enum OccupantType {
    STUDENT("студент"), EMPLOYEES("сотрудник");
    private final String type;

    OccupantType(String type) {
        this.type = type;
    }
}
