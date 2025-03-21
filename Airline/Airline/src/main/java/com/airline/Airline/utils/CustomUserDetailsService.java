package com.airline.Airline.utils;

import org.springframework.security.core.userdetails.*;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.airline.Airline.repository.UserRepo;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepository;

    public CustomUserDetailsService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws RuntimeException {
        com.airline.Airline.models.User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .toList();

            System.out.println( "printMEE"+ new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities          
            ));

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            authorities          
        );  
          
    }
}

