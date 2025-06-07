package org.generation.italy.collectionarchive.models.service;

import jakarta.persistence.PersistenceException;
import org.generation.italy.collectionarchive.models.entities.Collection;
import org.generation.italy.collectionarchive.models.entities.WishList;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.repositories.specifications.CollectionRepository;
import org.generation.italy.collectionarchive.models.repositories.specifications.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class JpaWishListService implements WishListService{
    private CollectionRepository collectionRepo;
    private WishListRepository wishListRepo;

    @Autowired

    public JpaWishListService(WishListRepository wishListRepo, CollectionRepository collectionRepo) {
        this.wishListRepo = wishListRepo;
    }
    //WISHLIST
    @Override
    public List<WishList> findAllWIshList() throws DataException {
        return wishListRepo.findAll();
    }

    @Override
    public Optional<WishList> findWIshListById(int desiredItemId) throws DataException {
        return wishListRepo.findById(desiredItemId);
    }

    @Override
    public WishList createWishList(WishList w, int collectionId) throws DataException {
        try {
            Optional<Collection> cl = collectionRepo.findById(collectionId);
            Collection co = cl.orElseThrow(() -> new EntityNotFoundException(Collection.class, collectionId));
            w.setCollection(co);
            wishListRepo.save(w);
            return w;
        } catch (PersistenceException pe) {
            throw new DataException("errore nella creazione di una nuova wishList", pe);
        }
    }

    @Override
    public boolean deleteWishList(int desiredItemId) throws DataException{
        Optional<WishList> wl= wishListRepo.findById(desiredItemId);
        if(wl.isPresent()){
            wishListRepo.delete(wl.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean updateWishList(WishList w, int collectionId) {
        Optional<WishList> wl = wishListRepo.findById(w.getDesiredItemId());
        if(wl.isEmpty()){
            return false;
        }
        Optional<Collection> op = collectionRepo.findById(collectionId);
        if(op.isEmpty()){
            return false;
        }
        Optional<Collection> cl = collectionRepo.findById(collectionId);
        Collection co = cl.orElseThrow(() -> new EntityNotFoundException(Collection.class, collectionId));
        w.setCollection(co);
        wishListRepo.save(w);
        return true;
    }
}
