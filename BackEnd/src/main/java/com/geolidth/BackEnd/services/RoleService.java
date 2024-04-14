package com.geolidth.BackEnd.services;

import com.geolidth.BackEnd.models.UserRole;

import java.util.List;

public interface RoleService {
    List<UserRole> getAllRoles();
    void addRole(UserRole role);
    void updateRole(UserRole role);
    void deleteRoleById(Integer id);
    UserRole getRoleByName(String roleName);
}