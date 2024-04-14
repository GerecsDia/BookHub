package com.geolidth.BackEnd.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserRolesDTO {
    private List<String> roles;

}