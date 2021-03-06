package com.kurilenko.entity;

import com.kurilenko.entity.enums.UserRole;
import lombok.*;

@Getter
@Setter

@ToString

@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private UserRole userRole;
}
