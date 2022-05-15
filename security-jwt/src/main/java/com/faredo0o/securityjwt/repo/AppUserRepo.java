package com.faredo0o.securityjwt.repo;

import com.faredo0o.securityjwt.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);

}
