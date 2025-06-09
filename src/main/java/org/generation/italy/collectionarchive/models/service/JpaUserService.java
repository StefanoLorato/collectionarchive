package org.generation.italy.collectionarchive.models.service;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.generation.italy.collectionarchive.models.entities.ShippingAddress;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.entities.UserContact;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.repositories.ShippingAddressRepository;
import org.generation.italy.collectionarchive.models.repositories.UserContactRepository;
import org.generation.italy.collectionarchive.models.repositories.UserRepository;
import org.generation.italy.collectionarchive.restdto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaUserService implements UserService{
    private UserContactRepository contactRepo;
    private UserRepository userRepo;
    private ShippingAddressRepository shippingRepo;

    public User toEntity(UserDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        //user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return user;
    }

    @Autowired
    public JpaUserService(UserContactRepository contactRepo, UserRepository userRepo,
                         ShippingAddressRepository shippingRepo, UserRepository userRepository) {
        this.contactRepo = contactRepo;
        this.userRepo = userRepo;
        this.shippingRepo = shippingRepo;

    }

    // USER
    @Override
    public List<User> findAllUsers() throws DataException{
        return userRepo.findAll();
    }

    @Override
    public Optional<User> findUserById(Integer id) throws DataException{
        return userRepo.findById(id);
    }

    @Override
    public User createUser(User user) throws DataException {
        try {
            return userRepo.save(user);
        } catch (PersistenceException e) {
            throw new DataException("errore nella creazione dell'user", e);
        }
    }

    @Override
    public boolean deleteUser(Integer id) throws DataException{
        Optional<User> ou = userRepo.findById(id);
        if(ou.isEmpty()){
            return false;
        }
        userRepo.delete(ou.get());
        return true;
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
}
