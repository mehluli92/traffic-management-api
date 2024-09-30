package com.traffic.tmanagement.service.user;

import com.traffic.tmanagement.dto.user.RoleDTO;
import com.traffic.tmanagement.dto.user.UserDTO;
import com.traffic.tmanagement.entity.Role;
import com.traffic.tmanagement.entity.User;
import com.traffic.tmanagement.repository.RoleRepository;
import com.traffic.tmanagement.repository.UserRepository;
import com.traffic.tmanagement.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    public UserService(
            UserRepository userRepository,
            VehicleRepository vehicleRepository,
            UserMapper userMapper,
            RoleRepository roleRepository
    ){
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
        this.userMapper = userMapper;

        this.roleRepository = roleRepository;
    }

    public Page<UserDTO> getAllUsers(Pageable pageable){

        return userRepository.findAll(pageable).map(userMapper::userToUserDTO);
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::userToUserDTO);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.userDTOtoUser(userDTO);
        //saving
        Set<Role> roles = new HashSet<>();
        for (RoleDTO role : userDTO.getRoles()){
            Role existingRole = roleRepository.findById(role.getId())
                    .orElseThrow(() -> new RuntimeException("Role not found: "+ role.getId()));
            roles.add(existingRole);
        }

        user.setRoles(roles);
        User createdUser = userRepository.save(user);

        return userMapper.userToUserDTO(createdUser);
    }

    public UserDTO updateUser(Long id, UserDTO userDetails) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setName(userDetails.getName());
            existingUser.setEc_number(userDetails.getEcNumber());
            existingUser.setPassword(userDetails.getPassword());
            existingUser.setEnabled(userDetails.getEnabled());

            Set<Role> roles = new HashSet<>();
            for (RoleDTO role: userDetails.getRoles()) {
                Role existingRole = roleRepository.findById(role.getId())
                        .orElseThrow(() -> new RuntimeException("Role not found: " + role.getId()));
                roles.add(existingRole);
            }
            existingUser.setRoles(roles);
            User updatedUser = userRepository.save(existingUser);

            return userMapper.userToUserDTO(updatedUser);
        } else {
            userDetails.setId(id);
            User newUser = userRepository.save(userMapper.userDTOtoUser(userDetails));
            return userMapper.userToUserDTO(newUser);
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
