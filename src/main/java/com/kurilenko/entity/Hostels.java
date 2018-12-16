package com.kurilenko.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Hostels {
    private Long id;
    private String nameHostel;
    private String address;
    private Long floors;
}
