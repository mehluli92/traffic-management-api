package com.traffic.tmanagement.service.role;

import com.traffic.tmanagement.entity.Role;
import com.traffic.tmanagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles(Pageable pageable){
        return roleRepository.findAll(pageable);
    }

    public Optional<Role> getRoleById(Long id){
        return roleRepository.findById(id);
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Long id, Role roleDetails){
        Role role = roleRepository.findById(id).orElse(null);
        if (role != null){
            role.setName(roleDetails.getName());
            role.setDescription(roleDetails.getDescription());
            return roleRepository.save(role);
        } else {
            roleDetails.setId(id);
            return roleRepository.save(roleDetails);
        }
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
