package org.generation.italy.collectionarchive.models.service;

import jakarta.persistence.PersistenceException;
import org.generation.italy.collectionarchive.models.entities.*;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.repositories.CategoryRepository;
import org.generation.italy.collectionarchive.models.repositories.CollectionRepository;
import org.generation.italy.collectionarchive.models.repositories.UserRepository;
import org.generation.italy.collectionarchive.models.repositories.specifications.CollectionSpecification;
import org.generation.italy.collectionarchive.restdto.CollectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JpaCollectionService implements CollectionService{
    private CollectionRepository collectionRepo;
    private UserRepository userRepo;
    private CategoryRepository categoryRepo;


    @Autowired
    public JpaCollectionService( CollectionRepository collectionRepo, UserRepository userRepo, CategoryRepository categoryRepo){
        this.collectionRepo = collectionRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
    }

    //COLLECTION
    @Override
    public List<Collection> findAllCollection() throws DataException {
        return  collectionRepo.findAll();
    }

    @Override
    public Optional<Collection> findCollectionById(int id) throws DataException {
        return collectionRepo.findById(id);
    }

    @Override
    @Transactional
    public Collection createCollection(Collection c, int userId, int categoryId) throws DataException, EntityNotFoundException {
        try {
            Optional<User> os = userRepo.findById(userId);
            if(os.isEmpty()){
                throw new EntityNotFoundException(User.class, userId);
            }
            User u = os.get();
            Optional<Category> oc = categoryRepo.findById(categoryId);
            Category ca = oc.orElseThrow(()-> new EntityNotFoundException(Category.class, categoryId));
            c.setUser(u);
            c.setCategory(ca);
            collectionRepo.save(c);
            return c;
        } catch (PersistenceException pe) {
            throw new DataException("errore nella creazione di un collection", pe);
        }
    }

    @Override
    @Transactional
    public boolean deleteCollection(int id) throws DataException {
        Optional<Collection> op = collectionRepo.findById(id);
        if(op.isPresent()){
            collectionRepo.delete(op.get());
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean updateCollection(Collection c, int userId, int categoryId) throws DataException, EntityNotFoundException {
        try {
            Optional<Collection> co = collectionRepo.findById(c.getCollectionId());
            if(co.isEmpty()){
                return false;
            }

            Optional<User> uo = userRepo.findById(userId);
            User u = uo.orElseThrow(()->new EntityNotFoundException(User.class, userId));
            Optional<Category> oc = categoryRepo.findById(categoryId);
            Category ca = oc.orElseThrow(()-> new EntityNotFoundException(Category.class, categoryId));
            c.setUser(u);
            c.setCategory(ca);
            collectionRepo.save(c);

            return true;
        } catch (PersistenceException pe) {
            throw new DataException("errore nella modifica di una collection", pe);
        }
    }

    @Override
    public List<Collection> findAllByUserEmail(String email) throws DataException {
        try {
            return collectionRepo.findByUserEmail(email);
        } catch (Exception e) {
            throw new DataException("Errore nel recupero delle collection dell'utente", e);
        }
    }

    @Override
    public List<Collection> searchCollection(CollectionDto dto) throws DataException {
        try {
            return collectionRepo.findAll(
                    Specification.where(CollectionSpecification.hasNameLike(dto.getCollectionName())
                                    .and(CollectionSpecification.hasCategoryName(dto.getCategoryId())))
                            .and(CollectionSpecification.hasUserId(dto.getUserId()))
                            .and(CollectionSpecification.hasSalePrice(dto.getSalePrice(), dto.getPriceComparation()))
            );
        } catch (PersistenceException pe) {
            throw new DataException("errore nella modifica di una collection", pe);
        }
    }
}


