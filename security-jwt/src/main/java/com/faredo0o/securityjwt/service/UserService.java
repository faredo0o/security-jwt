package com.faredo0o.securityjwt.service;

import com.faredo0o.securityjwt.domain.AppUser;
import com.faredo0o.securityjwt.domain.UserRole;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);
    UserRole saveRole(UserRole role);
    void addRoleToUser(String username,String roleName);
    AppUser getAppUser(String username);
    List<AppUser> getAllAppUsers();
}
