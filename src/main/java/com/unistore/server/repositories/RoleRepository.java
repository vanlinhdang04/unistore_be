package com.unistore.server.repositories;

import com.unistore.server.common.ERole;
import com.unistore.server.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    //Find role by name
    Optional<Role> findByName(ERole name);
}
