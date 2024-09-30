package com.traffic.tmanagement.service.permission;

import com.traffic.tmanagement.dto.user.PermissionDTO;
import com.traffic.tmanagement.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionDTO permissionToPermissionDTO(Permission permission);
    Permission permissionDTOToPermission(PermissionDTO permissionDTO);
}
