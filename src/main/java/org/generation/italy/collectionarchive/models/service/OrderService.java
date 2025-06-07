package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.CartItem;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.exceptions.DataException;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    // CART ITEM
    List<CartItem> findAllCartItems() throws DataException;
    Optional<CartItem> findCartItemById(int id) throws DataException;
    CartItem createCartItem(CartItem ci, Integer buyerId, Integer sellerId, Integer itemId, Integer collectionId) throws DataException;
    boolean deleteCartItem(int id) throws DataException;
    boolean updateCartItem(CartItem ci, Integer buyerId, Integer sellerId, Integer itemId, Integer collectionId) throws DataException;
}
