package com.traffic.tmanagement.service.role;

import com.traffic.tmanagement.dto.user.PermissionDTO;
import com.traffic.tmanagement.dto.user.RoleDTO;
import com.traffic.tmanagement.entity.Permission;
import com.traffic.tmanagement.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Optional;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO roleToRoleDTO(Role role);
    Role roleDTOToRole(RoleDTO roleDTO);
    //RoleDTO roleToRoleDTO(Optional<Role> role);

    PermissionDTO permissionToPermissionDTO(Permission permission);
    Permission permissionDTOToPermission(PermissionDTO permissionDTO);
    Set<PermissionDTO> permissionsToPermissionDTOs(Set<Permission> permissions);
}
