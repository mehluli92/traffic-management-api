package com.traffic.tmanagement.repository;

import com.traffic.tmanagement.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
