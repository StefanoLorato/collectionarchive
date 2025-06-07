package org.generation.italy.collectionarchive.models.service;

import jakarta.persistence.PersistenceException;
import org.generation.italy.collectionarchive.models.entities.*;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.repositories.specifications.CartItemRepository;
import org.generation.italy.collectionarchive.models.repositories.specifications.CollectionRepository;
import org.generation.italy.collectionarchive.models.repositories.specifications.ItemRepository;
import org.generation.italy.collectionarchive.models.repositories.specifications.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class JpaOrderService implements OrderService{
    private CartItemRepository cartItemRepo;
    private UserRepository userRepo;
    private ItemRepository itemRepo;
    private CollectionRepository collectionRepo;

    public JpaOrderService(CartItemRepository cartItemRepo, UserRepository userRepo, ItemRepository itemRepo,
                           CollectionRepository collectionRepo) {
        this.cartItemRepo = cartItemRepo;
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
        this.collectionRepo = collectionRepo;
    }

    @Autowired


    @Override
    public List<CartItem> findAllCartItems() throws DataException {
        return cartItemRepo.findAll();
    }

    @Override
    public Optional<CartItem> findCartItemById(int id) throws DataException {
        return cartItemRepo.findById(id);
    }

    @Override
    public CartItem createCartItem(CartItem ci, Integer buyerId, Integer sellerId, Integer itemId, Integer collectionId) throws DataException {
       try{
           Optional<User> ob = userRepo.findById(buyerId);
           User buyer = ob.orElseThrow(() -> new EntityNotFoundException(User.class, buyerId));
           Optional<User> os = userRepo.findById(sellerId);
           User seller = os.orElseThrow(() -> new EntityNotFoundException(User.class, sellerId));

           Item i = null;
           Collection c = null;
           if (itemId != null) {
               i = itemRepo.findById(itemId)
                       .orElseThrow(() -> new EntityNotFoundException(Item.class, itemId));
           }
           if (collectionId != null) {
               c = collectionRepo.findById(collectionId)
                       .orElseThrow(() -> new EntityNotFoundException(Collection.class, collectionId));
           }

           ci.setBuyer(buyer);
           ci.setSeller(seller);
           ci.setItem(i);
           ci.setCollection(c);

           cartItemRepo.save(ci);
           return ci;
       } catch (PersistenceException pe){
           throw new DataException("errore nella creazione di un cart item", pe);
       }
    }

    @Override
    public boolean deleteCartItem(int id) throws DataException {
        Optional<CartItem> oci = cartItemRepo.findById(id);
        if(oci.isEmpty()){
            return false;
        }
        cartItemRepo.delete(oci.get());
        return true;
    }

    @Override
    public boolean updateCartItem(CartItem ci, Integer buyerId, Integer sellerId, Integer itemId, Integer collectionId) throws DataException {
        try{
            Optional<CartItem> oci = cartItemRepo.findById(ci.getCartItemId());
            if(oci.isEmpty()){
                return false;
            }

            Optional<User> ob = userRepo.findById(buyerId);
            User buyer = ob.orElseThrow(() -> new EntityNotFoundException(User.class, buyerId));
            Optional<User> os = userRepo.findById(sellerId);
            User seller = os.orElseThrow(() -> new EntityNotFoundException(User.class, sellerId));

            Item i = null;
            Collection c = null;
            if (itemId != null) {
                i = itemRepo.findById(itemId)
                        .orElseThrow(() -> new EntityNotFoundException(Item.class, itemId));
            }
            if (collectionId != null) {
                c = collectionRepo.findById(collectionId)
                        .orElseThrow(() -> new EntityNotFoundException(Collection.class, collectionId));
            }

            ci.setBuyer(buyer);
            ci.setSeller(seller);
            ci.setItem(i);
            ci.setCollection(c);

            cartItemRepo.save(ci);
            return true;
        } catch (PersistenceException pe){
            throw new DataException("errore nella modifica di un cart item", pe);
        }
    }
}
