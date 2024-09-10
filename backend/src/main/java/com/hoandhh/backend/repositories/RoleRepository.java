package com.hoandhh.backend.repositories;

import com.hoandhh.backend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
