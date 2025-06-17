package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.CartItem;
import org.generation.italy.collectionarchive.models.entities.Order;
import org.generation.italy.collectionarchive.models.entities.OrderItem;
import org.generation.italy.collectionarchive.models.entities.ShippingAddress;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    //ORDER
    List<Order> findAllOrders() throws DataException;
    Optional<Order> findOrderById(int orderId) throws DataException;
    Order createOrder(Order o, Integer buyerId, Integer shipppingId) throws DataException, EntityNotFoundException;
    boolean deleteOrder(int orderId) throws DataException;
    boolean updateOrder(Order o, Integer buyerId, Integer shippingId) throws DataException, EntityNotFoundException;
    List<Order> findOrderByBuyerId(int id) throws DataException;
    List<Order> findOrderByItemsSeller(int id) throws DataException;
    //ORDER ITEM
    List<OrderItem> findAllOrderItems() throws DataException;
    Optional<OrderItem> findOrderItemById(int orderItemId) throws DataException;
    OrderItem createOrderItem(OrderItem oi, Integer orderId, Integer sellerId, Integer itemId, Integer collectionId) throws DataException, EntityNotFoundException;
    boolean deleteOrderItem(int orderItemId) throws DataException;
    boolean updateOrderItem(OrderItem oi, Integer orderId, Integer sellerId, Integer itemId, Integer collectionId) throws DataException, EntityNotFoundException;
    List<OrderItem> findOrderItemsBySellerId(int id) throws DataException;
    // CART ITEM
    List<CartItem> findAllCartItems() throws DataException;
    Optional<CartItem> findCartItemById(int id) throws DataException;
    CartItem createCartItem(CartItem ci, Integer buyerId, Integer sellerId, Integer itemId, Integer collectionId) throws DataException;
    boolean deleteCartItem(int id) throws DataException;
    boolean updateCartItem(CartItem ci, Integer buyerId, Integer sellerId, Integer itemId, Integer collectionId) throws DataException;
}
