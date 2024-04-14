package com.geolidth.BackEnd.models.dao;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Data
public class UserRolesDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public UserRolesDAO() {
    }

    public UserRolesDAO(String name) {
        this.name = name;
    }
}