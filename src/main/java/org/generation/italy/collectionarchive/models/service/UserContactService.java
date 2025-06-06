package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.UserContact;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserContactService {
    List<UserContact> findAllUserContacts() throws DataException;

    Optional<UserContact> findUserContactById(int id) throws DataException;

    UserContact createUserContact(UserContact contact, int userId) throws DataException, EntityNotFoundException;

    boolean updateUserContact(UserContact contact, int userId) throws DataException, EntityNotFoundException;

    boolean deleteUserContact(int id) throws DataException;
}
