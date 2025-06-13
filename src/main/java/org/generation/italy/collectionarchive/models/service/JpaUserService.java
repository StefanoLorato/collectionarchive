package org.generation.italy.collectionarchive.models.service;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.generation.italy.collectionarchive.models.entities.ShippingAddress;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.entities.UserContact;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.generation.italy.collectionarchive.models.entities.UserLike;
import org.generation.italy.collectionarchive.models.entities.Item;
import org.generation.italy.collectionarchive.models.repositories.UserLikeRepository;
import org.generation.italy.collectionarchive.models.repositories.ItemRepository;
import org.generation.italy.collectionarchive.models.entities.Order;
import org.generation.italy.collectionarchive.models.entities.UserFeedback;
import org.generation.italy.collectionarchive.models.repositories.OrderRepository;
import org.generation.italy.collectionarchive.models.repositories.UserFeedbackRepository;
import org.generation.italy.collectionarchive.models.entities.UserComment;




import java.util.List;
import java.util.Optional;

@Service
public class JpaUserService implements UserService{
    private UserContactRepository contactRepo;
    private UserRepository userRepo;
    private ShippingAddressRepository shippingRepo;
    private UserRepository userRepository;
    private UserLikeRepository userLikeRepo;
    private ItemRepository itemRepo;
    private UserFeedbackRepository userFeedbackRepo;
    private OrderRepository orderRepo;
    private UserCommentRepository userCommentRepo;


    @Autowired
    public JpaUserService(UserContactRepository contactRepo,
                          UserRepository userRepo,
                          ShippingAddressRepository shippingRepo,
                          UserRepository userRepository,
                          UserLikeRepository userLikeRepo,
                          ItemRepository itemRepo,
                          UserFeedbackRepository userFeedbackRepo,
                          OrderRepository orderRepo, UserCommentRepository userCommentRepo) {
        this.contactRepo = contactRepo;
        this.userRepo = userRepo;
        this.shippingRepo = shippingRepo;
        this.userRepository = userRepository;
        this.userLikeRepo = userLikeRepo;
        this.itemRepo = itemRepo;
        this.userFeedbackRepo = userFeedbackRepo;
        this.orderRepo = orderRepo;
        this.userCommentRepo = userCommentRepo;

    }

    // USER

    @Override
    public List<User> findAllUsers() throws DataException{
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Integer id) throws DataException{
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) throws DataException {
        try {
            return userRepository.save(user);
        } catch (PersistenceException e) {
            throw new DataException("errore nella creazione dell'user", e);
        }
    } //Qui dovrò inserire la logica di criptazione dei dati

    @Override
    public boolean deleteUser(Integer id) throws DataException{
        Optional<User> ou = userRepository.findById(id);
        if(ou.isEmpty()){
            return false;
        }
        userRepository.delete(ou.get());
        return true;
    }

    @Transactional
    @Override
    public boolean updateUser(User u) throws DataException {
        try {
            Optional<User> ou = userRepository.findById(u.getUserId());
            if(ou.isPresent()){
                userRepository.save(u);
                return true;
            }
            return false;
        } catch (PersistenceException e) {
            throw new DataException("errore nella modifica dell'user", e);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email){
        return userRepository.findByEmail(email);
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
    public UserLike createUserLike(int userId, int itemId) throws DataException, EntityNotFoundException {
        try {
            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException(User.class, userId));
            Item item = itemRepo.findById(itemId)
                    .orElseThrow(() -> new EntityNotFoundException(Item.class, itemId));

            UserLike like = new UserLike();
            like.setUser(user);
            like.setItem(item);
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
    public boolean userAlreadyLikedItem(int userId, int itemId) throws DataException {
        return userLikeRepo.existsByUserUserIdAndItemItemId(userId, itemId);
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
