package com.airline.Airline.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.airline.Airline.dto.LoginRequest;
import com.airline.Airline.models.Role;
import com.airline.Airline.models.User;
import com.airline.Airline.repository.RoleRepository;
import com.airline.Airline.repository.UserRepo;
import com.airline.Airline.services.UserService;
import com.airline.Airline.utils.CustomUserDetailsService;
import com.airline.Airline.utils.JwtUtil;

import java.util.Collections;
import java.util.List;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public UserServiceImpl(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
            UserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       
        Role defaultRole = roleRepository.findByName("USER");
        if (defaultRole == null) {
            defaultRole = new Role();
            defaultRole.setName("USER");
            defaultRole = roleRepository.save(defaultRole); // Save the role before assigning it
        }

        // Assign the role to user
        user.setRoles(Set.of(defaultRole));

        return userRepository.save(user);
    }
    
    @Override
    public User createAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role defaultRole = roleRepository.findByName("ADMIN");
        if (defaultRole == null) {
            defaultRole = new Role();
            defaultRole.setName("ADMIN");
            defaultRole = roleRepository.save(defaultRole); // Save the role before assigning it
        }
        user.setRoles(Set.of(defaultRole));
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        User existingUser = getUserById(id);

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setMobileNo(updatedUser.getMobileNo());
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());


        Role defaultRole = roleRepository.findByName("USER");
        if (defaultRole == null) {
            defaultRole = new Role();
            defaultRole.setName("USER");
            defaultRole = roleRepository.save(defaultRole); // Save the role before assigning it
        }

        // Assign the role to user
        existingUser.setRoles(Set.of(defaultRole));
        
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        User existingUser = getUserById(id);
        userRepository.delete(existingUser);
    }

    @Override
    public String loginUser(@RequestBody LoginRequest user) {
        User u = userRepository.findByUsername(user.getUserName());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(u.getUsername(), user.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
        return jwtUtil.generateToken(userDetails.getUsername());
    }

}
