package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.*;
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

    // SHIPPING ADDRESS
    List<ShippingAddress> findAllShippingAddresses() throws DataException;
    Optional<ShippingAddress> findShippingAddressById(int id) throws DataException;
    ShippingAddress createShippingAddress(ShippingAddress address, int userId) throws DataException, EntityNotFoundException;
    boolean updateShippingAddress(ShippingAddress address, int userId) throws DataException, EntityNotFoundException;
    boolean deleteShippingAddress(int id) throws DataException;
    List<ShippingAddress> findShippingAddressesByUserId(Integer id) throws DataException;

    // USER LIKE
    List<UserLike> findAllUserLikes() throws DataException;
    Optional<UserLike> findUserLikeById(int id) throws DataException;
    UserLike createUserLike(UserLike like, Integer userId, Integer itemId, Integer collectionId) throws DataException, EntityNotFoundException;
    boolean deleteUserLike(int id) throws DataException;
    List<UserLike> findUserLikesByUserId(int userId) throws DataException;
    boolean userAlreadyLikedItem(Integer userId, Integer itemId, Integer collectionId) throws DataException;

    // USER FEEDBACK

    List<UserFeedback> findAllUserFeedbacks() throws DataException;
    Optional<UserFeedback> findUserFeedbackById(int id) throws DataException;
    Optional<UserFeedback> findFeedbackByOrderId(int orderId) throws DataException;
    UserFeedback createUserFeedback(int orderId, int fromUserId, int toUserId, int rating, String comment)
            throws DataException, EntityNotFoundException;
    boolean deleteUserFeedback(int id) throws DataException;

    // USER COMMENT
    List<UserComment> findAllUserComments() throws DataException;
    Optional<UserComment> findUserCommentById(int id) throws DataException;
    UserComment createUserComment(UserComment c, int userId, Integer itemId, Integer collectionId, String comment) throws DataException, EntityNotFoundException;
    boolean updateUserComment(UserComment comment, int userId, int objectId) throws DataException, EntityNotFoundException;
    boolean deleteUserComment(int id) throws DataException;
}
