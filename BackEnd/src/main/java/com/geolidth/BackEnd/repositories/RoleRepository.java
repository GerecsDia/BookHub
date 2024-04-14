package com.geolidth.BackEnd.repositories;

import com.geolidth.BackEnd.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Integer> {

    UserRole findByName(String name);

    void deleteById(Integer id);
}