package com.geolidth.BackEnd.models.dao;

import com.geolidth.BackEnd.models.UserRole;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "user_roles")
@Data
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private BookUser user;

    @Enumerated(EnumType.STRING)
    private UserRole.Role role;
}

