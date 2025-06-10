package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.restdto.PasswordUpdateRequestDto;
import org.generation.italy.collectionarchive.restdto.UserDto;

import java.util.Optional;

public interface UserService {
    User getUserInfo();
    void updatePassword(PasswordUpdateRequestDto passwordUpdateRequestDto);
    boolean updateUser(User u)throws DataException;
    Optional<User> findUserByEmail(String email)throws DataException;
    Optional<User> findUserById(Integer id)throws DataException;
    boolean deactivateUser(int userId) throws DataException;
}
