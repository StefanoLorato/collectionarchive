package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
    @Primary
    @Service
    public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

        @Autowired
        private UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .disabled(!user.isActive())
                    .authorities("ROLE_" + user.getRole().toUpperCase())
                    .build();
        }
    }


