package com.faredo0o.securityjwt.service;

import com.faredo0o.securityjwt.domain.AppUser;
import com.faredo0o.securityjwt.domain.UserRole;
import com.faredo0o.securityjwt.repo.AppUserRepo;
import com.faredo0o.securityjwt.repo.RoleRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final AppUserRepo appUserRepo;
    private final RoleRepo roleRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       AppUser user=appUserRepo.findByUsername(username);
       if(user==null){
           log.error("User not found in the database");
           throw new UsernameNotFoundException("User not found in database");
       }else{
           log.info("User found in database {}",username);
       }

       // Mapping user authorities to simpleGrantedAuthorities and add it to Collection authorities
        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
       user.getRoles().forEach(role->authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new User(user.getUsername(),user.getPassword(),authorities);
    }
    @Override
    public AppUser saveUser(AppUser user) {
        log.info("Saving new user {} to the database",user.getName());
        return appUserRepo.save(user);
    }

    @Override
    public UserRole saveRole(UserRole role) {
        log.info("Saving new role {} to database",role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}",roleName,username);
        AppUser user=appUserRepo.findByUsername(username);
        UserRole role=roleRepo.findByName(roleName);
        user.getRoles().add(role);

    }

    @Override
    public AppUser getAppUser(String username) {
        log.info("Fetching user {}",username);
        return appUserRepo.findByUsername(username);
    }

    @Override
    public List<AppUser> getAllAppUsers() {
        log.info("Fetching all users");
        return appUserRepo.findAll();
    }


}
