package com.geolidth.BackEnd.Controllers;

import com.geolidth.BackEnd.models.UserRole;
import com.geolidth.BackEnd.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserRole>> getAllRoles() {
        List<UserRole> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Void> addRole(@RequestBody UserRole role) {
        roleService.addRole(role);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRole(@PathVariable Integer id, @RequestBody UserRole role) {
        role.setId(id);
        roleService.updateRole(role);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
        roleService.deleteRoleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{roleName}")
    public ResponseEntity<UserRole> getRoleByName(@PathVariable String roleName) {
        UserRole role = roleService.getRoleByName(roleName);
        return role != null ?
                new ResponseEntity<>(role, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}