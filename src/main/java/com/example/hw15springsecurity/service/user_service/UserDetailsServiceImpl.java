package com.example.hw15springsecurity.service.user_service;

import com.example.hw15springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final com.example.hw15springsecurity.entity.User userByEmail = userRepository
                .findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Such user is not found"));
        return new User(userByEmail.getEmail(), userByEmail.getPassword(), Collections.emptyList());
    }
}

