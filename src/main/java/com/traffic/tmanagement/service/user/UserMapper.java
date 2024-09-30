package com.traffic.tmanagement.service.user;

import com.traffic.tmanagement.dto.user.PermissionDTO;
import com.traffic.tmanagement.dto.user.RoleDTO;
import com.traffic.tmanagement.dto.user.UserDTO;
import com.traffic.tmanagement.entity.Permission;
import com.traffic.tmanagement.entity.Role;
import com.traffic.tmanagement.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO  userToUserDTO(User user);
    User userDTOtoUser(UserDTO userDTO);
    PermissionDTO permissionToPermissionDTO(Permission permission);
    Permission permissionDTOToPermission(PermissionDTO permissionDTO);
    RoleDTO roleToRoleDTO(Role role);
    Role roleDTOToRole(RoleDTO roleDTO);
}
