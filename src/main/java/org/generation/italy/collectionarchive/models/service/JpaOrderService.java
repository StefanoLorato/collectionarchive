package org.generation.italy.collectionarchive.models.service;

import jakarta.persistence.PersistenceException;
import org.generation.italy.collectionarchive.models.entities.*;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.repositories.specifications.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JpaOrderService implements OrderService {
    private OrderRepository orderRepo;
    private OrderItemRepository orderItemRepo;
    private UserRepository userRepo;
    private CollectionRepository collectionRepo;
    private ItemRepository itemRepo;
    private CartItemRepository cartItemRepo;

    @Autowired
    public JpaOrderService(OrderRepository orderRepo,OrderItemRepository orderItemRepo,UserRepository userRepo,
                           CollectionRepository collectionRepo,ItemRepository itemRepo, CartItemRepository cartItemRepo){
      this.orderRepo= orderRepo;
      this.orderItemRepo = orderItemRepo;
      this.userRepo = userRepo;
      this.collectionRepo = collectionRepo;
      this.itemRepo = itemRepo;
      this.cartItemRepo = cartItemRepo;
    }

    //ORDER
    @Override
    public List<org.generation.italy.collectionarchive.models.entities.Order> findAllOrders() throws DataException {
        return orderRepo.findAll();
    }

    @Override
    public Optional<org.generation.italy.collectionarchive.models.entities.Order> findOrderById(int orderId) throws DataException {
        return orderRepo.findById(orderId);
    }

    @Override
    public org.generation.italy.collectionarchive.models.entities.Order createOrder(org.generation.italy.collectionarchive.models.entities.Order o, Integer buyerId, Integer sellerId) throws DataException, EntityNotFoundException {
        try{
            Optional<User> ob = userRepo.findById(buyerId);
            User buyer = ob.orElseThrow(() -> new EntityNotFoundException(User.class, buyerId));
            Optional<User> os = userRepo.findById(sellerId);
            User seller = os.orElseThrow(() -> new EntityNotFoundException(User.class, sellerId));

            o.setBuyer(buyer);
            o.setSeller(seller);

            orderRepo.save(o);
            return o;
        } catch (PersistenceException pe){
            throw new DataException("errore nella creazione di un ordine", pe);
        }
    }

    @Override
    public boolean deleteOrder(int orderId) throws DataException {
        Optional<org.generation.italy.collectionarchive.models.entities.Order>  oo = orderRepo.findById(orderId);
        if(oo.isEmpty()){
            return false;
        }
        orderRepo.delete(oo.get());
        return true;
    }

    @Transactional
    @Override
    public boolean updateOrder(org.generation.italy.collectionarchive.models.entities.Order o, Integer buyerId, Integer sellerId) throws DataException, EntityNotFoundException {
        try{
            Optional<org.generation.italy.collectionarchive.models.entities.Order> oo = orderRepo.findById(o.getOrderId());
            if(oo.isEmpty()){
                return false;
            }

            Optional<User> ob = userRepo.findById(buyerId);
            User buyer = ob.orElseThrow(() -> new EntityNotFoundException(User.class, buyerId));
            Optional<User> os = userRepo.findById(sellerId);
            User seller = os.orElseThrow(() -> new EntityNotFoundException(User.class, sellerId));

            o.setBuyer(buyer);
            o.setSeller(seller);

            orderRepo.save(o);
            return true;
        } catch (PersistenceException pe){
            throw new DataException("errore nella modifica di un ordine", pe);
        }
    }

    //ORDER ITEM
    @Override
    public List<OrderItem> findAllOrderItems() throws DataException {
        return orderItemRepo.findAll();
    }

    @Override
    public Optional<OrderItem> findOrderItemById(int orderItemId) throws DataException {
        return orderItemRepo.findById(orderItemId);
    }

    @Override
    public OrderItem createOrderItem(OrderItem oi, Integer orderId, Integer itemId, Integer collectionId) throws DataException, EntityNotFoundException {
        try{
            Optional<Order> ob = orderRepo.findById(orderId);
            Order order = ob.orElseThrow(() -> new EntityNotFoundException(User.class, orderId));

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

            oi.setOrder(order);
            oi.setItem(i);
            oi.setCollection(c);

            orderItemRepo.save(oi);
            return oi;
        } catch (PersistenceException pe){
            throw new DataException("errore nella creazione di un order item", pe);
        }
    }

    @Override
    public boolean deleteOrderItem(int orderItemId) throws DataException {
        Optional<OrderItem> ooi = orderItemRepo.findById(orderItemId);
        if(ooi.isEmpty()){
            return false;
        }
        orderItemRepo.delete(ooi.get());
        return true;
    }

    @Transactional
    @Override
    public boolean updateOrderItem(OrderItem oi, Integer orderId, Integer itemId, Integer collectionId) throws DataException, EntityNotFoundException {
        try{
            Optional<OrderItem> ooi = orderItemRepo.findById(oi.getOrderItemId());
            if(ooi.isEmpty()){
                return false;
            }

            Optional<Order> ob = orderRepo.findById(orderId);
            Order order = ob.orElseThrow(() -> new EntityNotFoundException(User.class, orderId));

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

            oi.setOrder(order);
            oi.setItem(i);
            oi.setCollection(c);

            orderItemRepo.save(oi);
            return true;
        } catch (PersistenceException pe){
            throw new DataException("errore nella modifica di un order item", pe);
        }
    }

    //CART iTEM
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
