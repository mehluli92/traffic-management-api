package com.traffic.tmanagement.repository;

import com.traffic.tmanagement.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
   @Query("SELECT r FROM Role r JOIN FETCH r.permissions WHERE r.id = :id")
   Role findByIdWithPermissions(@Param("id") Long id);

    @Query("SELECT r FROM Role r LEFT JOIN r.permissions WHERE r.name LIKE %:name%")
    Page<Role> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);
}
