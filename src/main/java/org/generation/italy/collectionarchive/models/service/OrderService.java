package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.Order;
import org.generation.italy.collectionarchive.models.entities.OrderItem;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface OrderService {
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
}
