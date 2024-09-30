package com.traffic.tmanagement.service.permission;

import com.traffic.tmanagement.dto.user.PermissionDTO;
import com.traffic.tmanagement.entity.Permission;
import com.traffic.tmanagement.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository, PermissionMapper permissionMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }

    public Page<PermissionDTO> getAllPermissions(Pageable pageable, String name) {
        if (StringUtils.hasText(name)){
            return permissionRepository.findByNameContainingIgnoreCase(name, pageable).map(permissionMapper::permissionToPermissionDTO);
        }
        return permissionRepository.findAll(pageable).map(permissionMapper::permissionToPermissionDTO);
    }

    public Optional<PermissionDTO> findPermissionById(Long id) {
        return permissionRepository.findById(id).map(permissionMapper::permissionToPermissionDTO);
    }

    public PermissionDTO createPermission(PermissionDTO permissionDTO) {
        Permission newPermission =  permissionMapper.permissionDTOToPermission(permissionDTO);
        Permission savedPermission = permissionRepository.save(newPermission);
        return permissionMapper.permissionToPermissionDTO(savedPermission);
    }

    public PermissionDTO updatePermission(Long id, PermissionDTO permissionDTO) {
        Permission permission = permissionRepository.findById(id).orElse(null);
        if (permission != null){
            permission.setName(permissionDTO.getName());
            permission.setDescription(permissionDTO.getDescription());
            Permission savedPermission = permissionRepository.save(permission);
            return permissionMapper.permissionToPermissionDTO(savedPermission);
        } else {
            permissionDTO.setId(id);
            Permission savedPermission = permissionRepository.save(permissionMapper.permissionDTOToPermission(permissionDTO));
            return permissionMapper.permissionToPermissionDTO(savedPermission);
        }
    }

    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }
}
