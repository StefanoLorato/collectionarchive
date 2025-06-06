package org.generation.italy.collectionarchive.models.service;

import jakarta.persistence.PersistenceException;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.entities.UserContact;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.repositories.specifications.UserContactRepository;
import org.generation.italy.collectionarchive.models.repositories.specifications.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JpaUserContactService implements UserContactService {

    private final UserContactRepository contactRepo;
    private final UserRepository userRepo;

    @Autowired
    public JpaUserContactService(UserContactRepository contactRepo, UserRepository userRepo) {
        this.contactRepo = contactRepo;
        this.userRepo = userRepo;
    }

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
            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException(User.class, userId));
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

            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException(User.class, userId));
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
}