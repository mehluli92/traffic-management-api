package com.traffic.tmanagement.service.permission;

import com.traffic.tmanagement.entity.Permission;
import com.traffic.tmanagement.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    public Optional<Permission> findPermissionById(Long id) {
        return Optional.ofNullable(permissionRepository.findById(id).orElse(null));
    }

    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    public Permission updatePermission(Long id, Permission permissionDetails) {
        Permission permission = permissionRepository.findById(id).orElse(null);
        if (permission != null){
            permission.setName(permissionDetails.getName());
            permission.setDescription(permissionDetails.getDescription());
            return permissionRepository.save(permission);
        } else {
            permissionDetails.setId(id);
            return permissionRepository.save(permissionDetails);
        }
    }

    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }
}
