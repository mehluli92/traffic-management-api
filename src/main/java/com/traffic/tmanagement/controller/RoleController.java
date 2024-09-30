package com.traffic.tmanagement.controller;

import com.traffic.tmanagement.dto.user.RoleDTO;
import com.traffic.tmanagement.entity.Role;
import com.traffic.tmanagement.service.role.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping
    public Page<RoleDTO> getAllRoles(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "id") String sort,
            @RequestParam(required = false) String name
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,sort));
        return roleService.getAllRoles(pageable, name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id) {
        Optional<RoleDTO> role = roleService.getRoleById(id);
        return role.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@Valid @RequestBody RoleDTO role){
        RoleDTO createdRole = roleService.createRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> updateRole(@Valid @PathVariable Long id, @RequestBody RoleDTO roleDetails){
        RoleDTO updatedRole = roleService.updateRole(id, roleDetails);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
