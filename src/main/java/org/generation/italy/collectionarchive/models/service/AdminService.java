package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.restdto.UserDto;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    User promoteToAdmin(int userId);
    boolean deleteNonAdminUser(int userId);
    List<User> findAllUsers()throws DataException;
}
