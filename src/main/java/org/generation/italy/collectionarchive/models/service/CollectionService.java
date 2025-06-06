package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.*;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CollectionService {
    //COLLECTION
    List<Collection> findAllCollection() throws DataException;
    Optional<Collection> findCollectionById(int id) throws DataException;
    Collection createCollection(Collection c, int userId, int categoryId) throws DataException, EntityNotFoundException;
    boolean deleteCollection(int id) throws DataException;
    boolean updateCollection(Collection c, int userId, int categoryId) throws DataException, EntityNotFoundException;
    //ITEM
    List<Item> findAllItem() throws DataException;
    Optional<Item> findItemById(int id) throws DataException;
    Item createItem(Item i,int userId, int collectionId) throws DataException, EntityNotFoundException;
    boolean deleteItem(int id) throws DataException;
    boolean updateItem(Item t, int userId, int collectionId) throws DataException, EntityNotFoundException;
    //ORDER
    List<Order> findAllOrders() throws DataException;
    Optional<Order> findOrderById(int orderId) throws DataException;
    Order createOrder(Order o, Integer buyerId, Integer sellerId) throws DataException, EntityNotFoundException;
    boolean deleteOrder(int orderId) throws DataException;
    boolean updateOrder(Order o, Integer buyerId, Integer sellerId) throws DataException, EntityNotFoundException;
    //ORDER ITEM
    List<OrderItem> findAllOrderItems() throws DataException;
    Optional<OrderItem> findOrderItemById(int orderItemId) throws DataException;
    OrderItem createOrderItem(OrderItem oi, Integer orderId, Integer itemId, Integer collectionId) throws DataException, EntityNotFoundException;
    boolean deleteOrderItem(int orderItemId) throws DataException;
    boolean updateOrderItem(OrderItem oi, Integer orderId, Integer itemId, Integer collectionId) throws DataException, EntityNotFoundException;

    //CATEGORY
    List<Category> findAllCategories() throws DataException;
    Optional<Category> findCategoryId(int categoryId) throws DataException;
    Category createCategory(Category c) throws DataException, EntityNotFoundException;
    boolean deleteCategory(int categoryId) throws DataException;
    boolean updateCategory(Category c);

}
