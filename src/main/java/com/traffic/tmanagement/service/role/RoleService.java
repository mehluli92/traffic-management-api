package com.traffic.tmanagement.service.role;

import com.traffic.tmanagement.dto.user.PermissionDTO;
import com.traffic.tmanagement.dto.user.RoleDTO;
import com.traffic.tmanagement.entity.Permission;
import com.traffic.tmanagement.entity.Role;
import com.traffic.tmanagement.repository.PermissionRepository;
import com.traffic.tmanagement.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RoleMapper roleMapper;
    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    public RoleService(RoleRepository roleRepository, PermissionRepository permissionRepository, RoleMapper roleMapper){
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.roleMapper = roleMapper;
    }

    public Page<RoleDTO> getAllRoles(Pageable pageable, String name){
        if (StringUtils.hasText(name)){
            return roleRepository.findByNameContainingIgnoreCase(name, pageable).map(roleMapper::roleToRoleDTO);
        }
        return roleRepository.findAll(pageable).map(roleMapper::roleToRoleDTO);
    }

    public Optional<RoleDTO> getRoleById(Long id){
//        Optional<Role> role = roleRepository.findById(id);
//        logger.info("role data gotten by id: ",role);
        return roleRepository.findById(id).map(roleMapper::roleToRoleDTO);
    }

    public RoleDTO createRole(RoleDTO roleDTO) {
        Set<Permission> permissions = new HashSet<>();
        for (PermissionDTO permissionDTO : roleDTO.getPermissions()){
            Permission existingPermission = permissionRepository.findById(permissionDTO.getId()).orElse(null);
            if (existingPermission != null) {
                permissions.add(existingPermission);
            }
        }
        Role role = roleMapper.roleDTOToRole(roleDTO);
        role.setPermissions(permissions);

        Role savedRole = roleRepository.save(role);
        return roleMapper.roleToRoleDTO(savedRole);
    }

    public RoleDTO updateRole(Long id, RoleDTO roleDetails){
        Role role = roleRepository.findById(id).orElse(null);
        Set<Permission> permissions = new HashSet<>();
        for (PermissionDTO permission: roleDetails.getPermissions()) {
            Permission existingPermission = permissionRepository.findById(permission.getId()).orElse(null);
            if (existingPermission != null) {
                permissions.add(existingPermission);
            }
        }
        if (role != null){
            role.setPermissions(permissions);
            role.setName(roleDetails.getName());
            role.setDescription(roleDetails.getDescription());
            Role updatedRole =  roleRepository.save(role);
            return roleMapper.roleToRoleDTO(updatedRole);
        } else {
            roleDetails.setId(id);
            Role newRole = roleRepository.save(roleMapper.roleDTOToRole(roleDetails));
            return roleMapper.roleToRoleDTO(newRole);
        }
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
