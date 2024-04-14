package com.geolidth.BackEnd.models.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Login {
    private  String username;
    private  String password;
    private String email;
}
