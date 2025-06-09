package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.ShippingAddress;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.entities.UserContact;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    // USER
    List<User> findAllUsers()throws DataException;
    Optional<User> findUserById(Integer id)throws DataException;
    User createUser(User user)throws DataException;
    boolean deleteUser(Integer id)throws DataException;
    boolean updateUser(User u)throws DataException;
    Optional<User> findUserByEmail(String email)throws DataException;

    // USER CONTACT
    List<UserContact> findAllUserContacts() throws DataException;
    Optional<UserContact> findUserContactById(int id) throws DataException;
    UserContact createUserContact(UserContact contact, int userId) throws DataException, EntityNotFoundException;
    boolean updateUserContact(UserContact contact, int userId) throws DataException, EntityNotFoundException;
    boolean deleteUserContact(int id) throws DataException;

    // SHIPPING ADDRESS
    List<ShippingAddress> findAllShippingAddresses() throws DataException;
    Optional<ShippingAddress> findShippingAddressById(int id) throws DataException;
    ShippingAddress createShippingAddress(ShippingAddress address, int userId) throws DataException, EntityNotFoundException;
    boolean updateShippingAddress(ShippingAddress address, int userId) throws DataException, EntityNotFoundException;
    boolean deleteShippingAddress(int id) throws DataException;
}
