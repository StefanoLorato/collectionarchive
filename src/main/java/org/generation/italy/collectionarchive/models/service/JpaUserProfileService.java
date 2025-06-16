package org.generation.italy.collectionarchive.models.service;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.generation.italy.collectionarchive.models.entities.*;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaUserProfileService implements UserProfileService{
    private UserContactRepository contactRepo;
    private ShippingAddressRepository shippingRepo;
    private UserRepository userRepo;
    private UserLikeRepository userLikeRepo;
    private ItemRepository itemRepo;
    private UserFeedbackRepository userFeedbackRepo;
    private OrderRepository orderRepo;
    private UserCommentRepository userCommentRepo;
    private CollectionRepository collectionRepo;

    @Autowired
    public JpaUserProfileService(UserContactRepository contactRepo,
                                 ShippingAddressRepository shippingRepo, UserRepository userRepo,
                                 UserLikeRepository userLikeRepo, ItemRepository itemRepo, OrderRepository orderRepo,
                                 UserFeedbackRepository userFeedbackRepo, UserCommentRepository userCommentRepo,
                                 CollectionRepository collectionRepo) {
        this.contactRepo = contactRepo;
        this.shippingRepo = shippingRepo;
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
        this.userFeedbackRepo = userFeedbackRepo;
        this.orderRepo = orderRepo;
        this.userCommentRepo = userCommentRepo;
        this.collectionRepo = collectionRepo;
    }

    // USER CONTACT

    @Override
    public List<UserContact> findAllUserContacts() throws DataException {
        return contactRepo.findAll();
    }

    @Override
    public Optional<UserContact> findUserContactById(int id) throws DataException {
        return contactRepo.findById(id);
    }

    @Override
    @Transactional
    public UserContact createUserContact(UserContact contact, int userId) throws DataException, EntityNotFoundException {
        try {
            Optional<User> ou = userRepo.findById(userId);
            User user = ou.orElseThrow(() -> new EntityNotFoundException(User.class, userId));
            contact.setUser(user);
            contactRepo.save(contact);
            return contact;
        } catch (PersistenceException e) {
            throw new DataException("Errore durante la creazione del contatto", e);
        }
    }

    @Override
    @Transactional
    public boolean updateUserContact(UserContact contact, int userId) throws DataException, EntityNotFoundException {
        try {
            Optional<UserContact> existing = contactRepo.findById(contact.getContactId());
            if (existing.isEmpty()) {
                return false;
            }

            User user = userRepo.findById(userId).orElseThrow(() -> new EntityNotFoundException(User.class, userId));
            contact.setUser(user);
            contactRepo.save(contact);
            return true;
        } catch (PersistenceException e) {
            throw new DataException("Errore durante l'aggiornamento del contatto", e);
        }
    }

    @Override
    @Transactional
    public boolean deleteUserContact(int id) throws DataException {
        Optional<UserContact> existing = contactRepo.findById(id);
        if (existing.isPresent()) {
            contactRepo.delete(existing.get());
            return true;
        }
        return false;
    }



    // SHIPPING ADDRESS

    @Override
    public List<ShippingAddress> findAllShippingAddresses() throws DataException {
        return shippingRepo.findAll();
    }

    @Override
    public Optional<ShippingAddress> findShippingAddressById(int id) throws DataException {
        return shippingRepo.findById(id);
    }

    @Override
    public ShippingAddress createShippingAddress(ShippingAddress address, int userId) throws DataException, EntityNotFoundException {
        try {
            User user = userRepo.findById(userId).orElseThrow(() -> new EntityNotFoundException(User.class, userId));
            address.setUser(user);
            shippingRepo.save(address);
            return address;
        } catch (PersistenceException e) {
            throw new DataException("Errore durante la creazione dell'indirizzo", e);
        }
    }

    @Override
    public boolean updateShippingAddress(ShippingAddress address, int userId) throws DataException, EntityNotFoundException {
        try {
            Optional<ShippingAddress> existing = shippingRepo.findById(address.getShippingId());
            if (existing.isEmpty()) {
                return false;
            }

            User user = userRepo.findById(userId).orElseThrow(() -> new EntityNotFoundException(User.class, userId));
            address.setUser(user);
            shippingRepo.save(address);
            return true;
        } catch (PersistenceException e) {
            throw new DataException("Errore durante l'aggiornamento del contatto", e);
        }
    }

    @Override
    public boolean deleteShippingAddress(int id) throws DataException {
        Optional<ShippingAddress> existing = shippingRepo.findById(id);
        if (existing.isPresent()) {
            shippingRepo.delete(existing.get());
            return true;
        }
        return false;
    }

    @Override
    public List<ShippingAddress> findShippingAddressesByUserId(Integer id) throws DataException {
        return shippingRepo.findByUserUserId(id);
    }

    // USER LIKE

    @Override
    public List<UserLike> findAllUserLikes() throws DataException {
        return userLikeRepo.findAll();
    }

    @Override
    public Optional<UserLike> findUserLikeById(int id) throws DataException {
        return userLikeRepo.findById(id);
    }

    @Override
    @Transactional
    public UserLike createUserLike(UserLike like, Integer userId, Integer itemId, Integer collectionId) throws DataException, EntityNotFoundException {
        try {
            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException(User.class, userId));

            Item i = null;
            Collection c = null;
            if(itemId != null) {
                Optional<Item> oi = itemRepo.findById(itemId);
                i = oi.orElseThrow(() -> new EntityNotFoundException(Item.class, itemId));
            }
            if(collectionId != null) {
                Optional<Collection> oc = collectionRepo.findById(collectionId);
                c = oc.orElseThrow(() -> new EntityNotFoundException(Collection.class, collectionId));
            }

            like.setUser(user);
            like.setItem(i);
            like.setCollection(c);
            return userLikeRepo.save(like);
        } catch (PersistenceException e) {
            throw new DataException("Errore durante la creazione del like", e);
        }
    }

    @Override
    @Transactional
    public boolean deleteUserLike(int id) throws DataException {
        Optional<UserLike> ul = userLikeRepo.findById(id);
        if (ul.isPresent()) {
            userLikeRepo.delete(ul.get());
            return true;
        }
        return false;
    }

    @Override
    public List<UserLike> findUserLikesByUserId(int userId) throws DataException {
        return userLikeRepo.findByUserUserId(userId);
    }

    @Override
    public boolean userAlreadyLikedItem(Integer userId, Integer itemId, Integer collectionId) throws DataException {
        return userLikeRepo.existsByUserUserIdAndItemItemIdAndCollectionCollectionId(userId, itemId, collectionId);
    }
    @Override
    public List<UserFeedback> findAllUserFeedbacks() throws DataException {
        try {
            return userFeedbackRepo.findAll();
        } catch (PersistenceException e) {
            throw new DataException("Errore nel recupero dei feedback", e);
        }
    }

    @Override
    public Optional<UserFeedback> findUserFeedbackById(int id) throws DataException {
        try {
            return userFeedbackRepo.findById(id);
        } catch (PersistenceException e) {
            throw new DataException("Errore nel recupero del feedback", e);
        }
    }

    @Override
    public Optional<UserFeedback> findFeedbackByOrderId(int orderId) throws DataException {
        try {
            return userFeedbackRepo.findByOrderOrderId(orderId);
        } catch (PersistenceException e) {
            throw new DataException("Errore nel recupero del feedback per ordine", e);
        }
    }

    @Override
    @Transactional
    public UserFeedback createUserFeedback(int orderId, int fromUserId, int toUserId, int rating, String comment)
            throws DataException, EntityNotFoundException {
        try {
            Order order = orderRepo.findById(orderId)
                    .orElseThrow(() -> new EntityNotFoundException(Order.class, orderId));
            User fromUser = userRepo.findById(fromUserId)
                    .orElseThrow(() -> new EntityNotFoundException(User.class, fromUserId));
            User toUser = userRepo.findById(toUserId)
                    .orElseThrow(() -> new EntityNotFoundException(User.class, toUserId));

            UserFeedback feedback = new UserFeedback();
            feedback.setOrder(order);
            feedback.setFromUser(fromUser);
            feedback.setToUser(toUser);
            feedback.setRating(rating);
            feedback.setComment(comment);
            feedback.setCreatedAt(java.time.LocalDateTime.now());

            return userFeedbackRepo.save(feedback);
        } catch (PersistenceException e) {
            throw new DataException("Errore durante la creazione del feedback", e);
        }
    }

    @Override
    @Transactional
    public boolean deleteUserFeedback(int id) throws DataException {
        try {
            Optional<UserFeedback> f = userFeedbackRepo.findById(id);
            if (f.isPresent()) {
                userFeedbackRepo.delete(f.get());
                return true;
            }
            return false;
        } catch (PersistenceException e) {
            throw new DataException("Errore durante l'eliminazione del feedback", e);
        }
    }
    @Override
    public List<UserComment> findAllUserComments() throws DataException {
        try {
            return userCommentRepo.findAll();
        } catch (PersistenceException e) {
            throw new DataException("Errore nel recupero dei commenti", e);
        }
    }

    @Override
    public Optional<UserComment> findUserCommentById(int id) throws DataException {
        try {
            return userCommentRepo.findById(id);
        } catch (PersistenceException e) {
            throw new DataException("Errore nel recupero del commento", e);
        }
    }

    @Override
    @Transactional
    public UserComment createUserComment(int userId, int objectId, String comment)
            throws DataException, EntityNotFoundException {
        try {
            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException(User.class, userId));
            Item item = itemRepo.findById(objectId)
                    .orElseThrow(() -> new EntityNotFoundException(Item.class, objectId));

            UserComment uc = new UserComment();
            uc.setUser(user);
            uc.setItem(item);
            uc.setComment(comment);

            return userCommentRepo.save(uc);
        } catch (PersistenceException e) {
            throw new DataException("Errore nella creazione del commento", e);
        }
    }

    @Override
    @Transactional
    public boolean updateUserComment(UserComment comment, int userId, int objectId)
            throws DataException, EntityNotFoundException {
        try {
            if (!userCommentRepo.existsById(comment.getCommentId())) return false;

            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException(User.class, userId));
            Item item = itemRepo.findById(objectId)
                    .orElseThrow(() -> new EntityNotFoundException(Item.class, objectId));

            comment.setUser(user);
            comment.setItem(item);

            userCommentRepo.save(comment);
            return true;
        } catch (PersistenceException e) {
            throw new DataException("Errore durante l'aggiornamento del commento", e);
        }
    }

    @Override
    @Transactional
    public boolean deleteUserComment(int id) throws DataException {
        try {
            Optional<UserComment> comment = userCommentRepo.findById(id);
            if (comment.isPresent()) {
                userCommentRepo.delete(comment.get());
                return true;
            }
            return false;
        } catch (PersistenceException e) {
            throw new DataException("Errore durante l'eliminazione del commento", e);
        }
    }
}
