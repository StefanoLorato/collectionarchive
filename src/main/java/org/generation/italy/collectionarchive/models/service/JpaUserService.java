package org.generation.italy.collectionarchive.models.service;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.repositories.specifications.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaUserService implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public JpaUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

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

}
