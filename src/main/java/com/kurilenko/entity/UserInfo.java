package com.kurilenko.entity;

import com.kurilenko.entity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
public class UserInfo {
    private Long id;
    private String username;
    private UserRole role;
    private String hostel;
}
