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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaUserProfileService implements UserProfileService{
    private UserContactRepository contactRepo;
    private ShippingAddressRepository shippingRepo;
    private UserRepository userRepository;

    @Autowired
    public JpaUserProfileService(UserContactRepository contactRepo,
                                 ShippingAddressRepository shippingRepo, UserRepository userRepository) {
        this.contactRepo = contactRepo;
        this.shippingRepo = shippingRepo;
        this.userRepository = userRepository;
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
            Optional<User> ou = userRepository.findById(userId);
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

            User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(User.class, userId));
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
            User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(User.class, userId));
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

            User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(User.class, userId));
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


}
