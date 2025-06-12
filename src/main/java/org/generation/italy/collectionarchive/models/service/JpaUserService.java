package org.generation.italy.collectionarchive.models.service;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.repositories.UserRepository;
import org.generation.italy.collectionarchive.restdto.PasswordUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class JpaUserService implements UserService{
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public JpaUserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserInfo() {
        return getAuthenticatedUser();
    }

    @Override
    public void updatePassword(PasswordUpdateRequestDto passwordUpdateRequestDto) {
        User user = getAuthenticatedUser();
        if(!isOldPasswordCorrect(user.getPassword(), passwordUpdateRequestDto.getOldPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Current password is incorrect");
        }

        if(!isNewPasswordConfirmed(passwordUpdateRequestDto.getNewPassword(), passwordUpdateRequestDto.getNewPassword2())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New Passwords do not match");
        }

        if(!isNewPasswordDifferent(passwordUpdateRequestDto.getOldPassword(), passwordUpdateRequestDto.getNewPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New password cannot be equal to old password");
        }

        user.setPassword(passwordEncoder.encode(passwordUpdateRequestDto.getNewPassword()));
        userRepo.save(user);
    }

    private boolean isOldPasswordCorrect(String currentPassword, String oldPassword) {
        return passwordEncoder.matches(oldPassword, currentPassword);
    }

    private boolean isNewPasswordConfirmed(String newPassword, String newPasswordConfirmation) {
        return newPassword.equals(newPasswordConfirmation);
    }

    private boolean isNewPasswordDifferent(String oldPassword, String newPassword) {
        return !oldPassword.equals(newPassword);
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() ||
                authentication.getPrincipal().equals("anonymousUser")) {
            throw new AccessDeniedException("Authentication required");
        }
        return (User) authentication.getPrincipal();
    }

    @Transactional
    @Override
    public boolean updateUser(User u) throws DataException {
        try {
            Optional<User> ou = userRepo.findById(u.getUserId());
            if(ou.isPresent()){
                userRepo.save(u);
                return true;
            }
            return false;
        } catch (PersistenceException e) {
            throw new DataException("errore nella modifica dell'user", e);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email){
        return userRepo.findByEmail(email);
    }

    @Override
    public Optional<User> findUserById(Integer id) throws DataException {
        return userRepo.findById(id);
    }

    @Override
    public boolean deactivateUser(int userId) throws DataException {
        try {
            Optional<User> ou = userRepo.findById(userId);
            if (ou.isEmpty()) {
                return false;
            }
            User u = ou.get();
            u.setActive(false);
            userRepo.save(u);
            return true;
        } catch (PersistenceException e) {
            throw new DataException("errore nella disattivazione dell'user", e);
        }

    }
}
