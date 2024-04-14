package com.geolidth.BackEnd.models.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class NewUser {
    private  String username;
    private  String password;
    private String email;
    private UserRolesDTO roles;
}
