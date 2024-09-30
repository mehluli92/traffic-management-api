package com.traffic.tmanagement.controller;

import com.traffic.tmanagement.dto.user.PermissionDTO;
import com.traffic.tmanagement.entity.Permission;
import com.traffic.tmanagement.service.permission.PermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("permissions")
public class PermissionController {
    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }
    @GetMapping
    public Page<PermissionDTO> getAllPermissions(Pageable pageable, @RequestParam(required = false) String name) {
        return permissionService.getAllPermissions(pageable, name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionDTO> getPermissionById(@PathVariable Long id) {
        Optional<PermissionDTO> permission = permissionService.findPermissionById(id);
        return permission.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PermissionDTO> createPermission(@Valid @RequestBody PermissionDTO permissionDTO) {
        PermissionDTO createdPermission = permissionService.createPermission(permissionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPermission);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionDTO> updatePermission(@Valid @PathVariable Long id, @RequestBody PermissionDTO permissionDTO){
        PermissionDTO updatedPermission = permissionService.updatePermission(id, permissionDTO);
        return ResponseEntity.ok(updatedPermission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return ResponseEntity.noContent().build();
    }
}
