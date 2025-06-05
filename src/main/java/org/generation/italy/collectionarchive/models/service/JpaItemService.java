package org.generation.italy.collectionarchive.models.service;

import jakarta.persistence.PersistenceException;
import org.generation.italy.collectionarchive.models.entities.Collection;
import org.generation.italy.collectionarchive.models.entities.Item;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.repositories.specifications.CollectionRepository;
import org.generation.italy.collectionarchive.models.repositories.specifications.ItemRepository;
import org.generation.italy.collectionarchive.models.repositories.specifications.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serial;
import java.util.List;
import java.util.Optional;

@Service
public class JpaItemService implements ItemService {
    private ItemRepository itemRepo;
    private CollectionRepository collecionRepo;
    private UserRepository userRepo;

    @Autowired
    public JpaItemService( ItemRepository itemRepo,CollectionRepository collectionRepo,
                           UserRepository userRepo){
        this.itemRepo = itemRepo;
        this.collecionRepo = collectionRepo;
        this.userRepo = userRepo;
    }


    @Override
    public List<Item> findAllItem() throws DataException {
        return itemRepo.findAll();
    }

    @Override
    public Optional<Item> findItemById(int id) throws DataException {
        return itemRepo.findById(id);
    }

    @Override
    @Transactional
    public Item createItem(Item i,int userId, int collectionId) throws DataException, EntityNotFoundException {
        try {
            Optional<User> os = userRepo.findById(userId);
            if(os.isEmpty()){
                throw new EntityNotFoundException(User.class, userId);
            }
            User u = os.get();
            Optional<Collection> oc = collecionRepo.findById(collectionId);
            Collection ca = oc.orElseThrow(()-> new EntityNotFoundException(Collection.class, collectionId));
            i.setUser(u);
            i.setCollection(ca);
            itemRepo.save(i);
            return i;
        } catch (PersistenceException pe) {
            throw new DataException("errore nella creazione di un nuovo prodotto", pe);
        }
    }

    @Override
    public boolean deleteItem(int id) throws DataException {
        Optional<Item> io = itemRepo.findById(id);
        if(io.isPresent()){
            itemRepo.delete(io.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean updateItem(Item i,int userId, int collectionId) throws DataException, EntityNotFoundException {
        try {
            Optional<Item> io = itemRepo.findById(i.getItemId());
            if(io.isEmpty()){
                return false;
            }
            Optional<User> uo = userRepo.findById(userId);
            User u = uo.orElseThrow(()->new EntityNotFoundException(User.class, userId));
            Optional<Collection> oc = collecionRepo.findById(collectionId);
            Collection co = oc.orElseThrow(()-> new EntityNotFoundException(Collection.class, collectionId));
            i.setUser(u);
            i.setCollection(co);
            itemRepo.save(i);

            return true;
        } catch (PersistenceException pe) {
            throw new DataException("errore nella modifica di un prodotto", pe);
        }
    }
}

