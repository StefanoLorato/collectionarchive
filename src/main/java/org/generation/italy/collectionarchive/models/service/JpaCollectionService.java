package org.generation.italy.collectionarchive.models.service;

import jakarta.persistence.PersistenceException;
import org.generation.italy.collectionarchive.models.entities.Category;
import org.generation.italy.collectionarchive.models.entities.Collection;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.entities.WishList;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.repositories.specifications.CategoryRepository;
import org.generation.italy.collectionarchive.models.repositories.specifications.CollectionRepository;
import org.generation.italy.collectionarchive.models.repositories.specifications.UserRepository;
import org.generation.italy.collectionarchive.models.repositories.specifications.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JpaCollectionService implements CollectionService{
    private CollectionRepository collecionRepo;
    private UserRepository userRepo;
    private CategoryRepository categoryRepo;
    private WishListRepository wishListRepo;


    @Autowired
    public JpaCollectionService( CollectionRepository collectionRepo, UserRepository userRepo,
                                 CategoryRepository categoryRepo, WishListRepository wishListRepo){
        this.collecionRepo = collectionRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
        this.wishListRepo = wishListRepo;
    }

    @Override
    public List<Collection> findAllCollection() throws DataException {
        return  collecionRepo.findAll();
    }

    @Override
    public Optional<Collection> findCollectionById(int id) throws DataException {
        return collecionRepo.findById(id);
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
            collecionRepo.save(c);
            return c;
        } catch (PersistenceException pe) {
            throw new DataException("errore nella creazione di un nuovo prodotto", pe);
        }
    }

    @Override
    @Transactional
    public boolean deleteCollection(int id) throws DataException {
        Optional<Collection> op = collecionRepo.findById(id);
        if(op.isPresent()){
            collecionRepo.delete(op.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCollection(Collection c, int userId, int categoryId) throws DataException, EntityNotFoundException {
        try {
            Optional<Collection> co = collecionRepo.findById(c.getCollectionId());
            if(co.isEmpty()){
                return false;
            }

            Optional<User> uo = userRepo.findById(userId);
            User u = uo.orElseThrow(()->new EntityNotFoundException(User.class, userId));
            Optional<Category> oc = categoryRepo.findById(categoryId);
            Category ca = oc.orElseThrow(()-> new EntityNotFoundException(Category.class, categoryId));
            c.setUser(u);
            c.setCategory(ca);
            collecionRepo.save(c);

            return true;
        } catch (PersistenceException pe) {
            throw new DataException("errore nella modifica di un prodotto", pe);
        }
    }

    @Override
    public List<WishList> findAllWishList() {
        return List.of();
    }

    @Override
    public Optional<WishList> findWishListById(int id) {
        return Optional.empty();
    }

    @Override
    public WishList createWishList(WishList w, int collectionId) {
        return null;
    }

    @Override
    public boolean deleteWishList(int id) {
        return false;
    }

    @Override
    public boolean updateItem(WishList w , int collectionId) {
        return false;
    }
}
