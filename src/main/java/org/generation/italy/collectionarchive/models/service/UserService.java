package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.exceptions.DataException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAllUsers()throws DataException;

    Optional<User> findUserById(Integer id)throws DataException;

    User createUser(User user)throws DataException;//Qui dovrò inserire la logica di criptazione dei dati

    boolean deleteUser(Integer id)throws DataException;

    boolean updateUser(User u)throws DataException;

    Optional<User> findUserByEmail(String email)throws DataException;
}

