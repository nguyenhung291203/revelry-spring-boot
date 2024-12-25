package com.eledevo.basejava.config.security;

import com.eledevo.basejava.entity.UserEntity;
import com.eledevo.basejava.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        
        // Assuming the method to get role is getRoleName() or similar
        String roleName = "ROLE_" +  userEntity.getRole().getName();// Adjust this method name as per your UserEntity class
        String usernames = userEntity.getUsername();
        String password = userEntity.getPassword();
        // Chuyển đổi vai trò thành GrantedAuthority
        GrantedAuthority authority = new SimpleGrantedAuthority(roleName);

        // Trả về UserDetails
        return new User(usernames, password, Collections.singletonList(authority));
    }
}
