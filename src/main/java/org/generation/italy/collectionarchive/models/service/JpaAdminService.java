package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.Authority;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class JpaAdminService implements AdminService{
    private UserRepository userRepo;

    public JpaAdminService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> findAllUsers() {
        return StreamSupport.stream(userRepo.findAll().spliterator(), false).toList();
    }

    @Override
    public User promoteToAdmin(int userId) {
        Optional<User> user = userRepo.findById(userId);

        if (user.isEmpty() || user.get().getAuthorities().stream().anyMatch(authority -> "ROLE_ADMIN"
                .equals(authority.getAuthority()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "User does not exist or already an admin");
        }

        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority("ROLE_CLIENT"));
        authorities.add(new Authority("ROLE_ADMIN"));
        user.get().setAuthorities(authorities);

        return userRepo.save(user.get());
    }

    @Override
    public boolean deleteNonAdminUser(int userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isEmpty() || user.get().getAuthorities().stream().anyMatch(authority -> "ROLE_ADMIN"
                .equals(authority.getAuthority()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "User does not exist or is an admin");
        }
        userRepo.delete(user.get());
        return true;
    }
}
