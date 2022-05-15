package com.faredo0o.securityjwt.repo;
import com.faredo0o.securityjwt.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<UserRole,Long> {
    UserRole findByName(String name);
}
