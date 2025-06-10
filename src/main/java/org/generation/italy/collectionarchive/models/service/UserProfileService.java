package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.ShippingAddress;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.entities.UserContact;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserProfileService {
    // USER CONTACT
    List<UserContact> findAllUserContacts() throws DataException;
    Optional<UserContact> findUserContactById(int id) throws DataException;
    UserContact createUserContact(UserContact contact, int userId) throws DataException, EntityNotFoundException;
    boolean updateUserContact(UserContact contact, int userId) throws DataException, EntityNotFoundException;
    boolean deleteUserContact(int id) throws DataException;

    //USER LIKE

    //USER COMMENT

    // SHIPPING ADDRESS
    List<ShippingAddress> findAllShippingAddresses() throws DataException;
    Optional<ShippingAddress> findShippingAddressById(int id) throws DataException;
    ShippingAddress createShippingAddress(ShippingAddress address, int userId) throws DataException, EntityNotFoundException;
    boolean updateShippingAddress(ShippingAddress address, int userId) throws DataException, EntityNotFoundException;
    boolean deleteShippingAddress(int id) throws DataException;
}
