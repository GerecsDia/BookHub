package com.geolidth.BackEnd.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "user_role")
public class UserRole {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private Role name;

    @Column(name = "role")
    private String role;

    public enum Role {
        ADMIN_ROLE,
        USER_ROLE,
        GUEST_ROLE
    }
}