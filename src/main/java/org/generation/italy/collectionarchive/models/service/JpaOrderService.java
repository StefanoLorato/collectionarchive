package org.generation.italy.collectionarchive.models.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import org.generation.italy.collectionarchive.models.entities.*;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.exceptions.LogicException;
import org.generation.italy.collectionarchive.models.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private ShippingAddressRepository shippingRepo;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public JpaOrderService(OrderRepository orderRepo,OrderItemRepository orderItemRepo,UserRepository userRepo,
                           CollectionRepository collectionRepo,ItemRepository itemRepo, CartItemRepository cartItemRepo,
                           ShippingAddressRepository shippingRepo){
      this.orderRepo= orderRepo;
      this.orderItemRepo = orderItemRepo;
      this.userRepo = userRepo;
      this.collectionRepo = collectionRepo;
      this.itemRepo = itemRepo;
      this.cartItemRepo = cartItemRepo;
      this.shippingRepo = shippingRepo;
    }

    //ORDER
    @Override
    public List<Order> findAllOrders() throws DataException {
        return orderRepo.findAll();
    }

    @Override
    public Optional<Order> findOrderById(int orderId) throws DataException {
        return orderRepo.findById(orderId);
    }

    @Override
    public Order createOrder(User user, Order o, Integer buyerId, Integer shippingId)
            throws DataException, EntityNotFoundException, LogicException{
//        if(buyerId == user.getUserId()){
//            throw new LogicException("non puoi compreare un oggetto che possiedi");
//        }
        try{
            Optional<User> ob = userRepo.findById(buyerId);
            User buyer = ob.orElseThrow(() -> new EntityNotFoundException(User.class, buyerId));
            Optional<ShippingAddress> osa = shippingRepo.findById(shippingId);
            ShippingAddress sa = osa.orElseThrow(() -> new EntityNotFoundException(ShippingAddress.class, shippingId));

            o.setBuyer(buyer);
            o.setShippingAddress(sa);

            orderRepo.save(o);
            return o;
        } catch (PersistenceException pe){
            throw new DataException("errore nella creazione di un ordine", pe);
        }
    }

    @Override
    public boolean deleteOrder(int orderId) throws DataException {
        Optional<Order>  oo = orderRepo.findById(orderId);
        if(oo.isEmpty()){
            return false;
        }
        orderRepo.delete(oo.get());
        return true;
    }

    @Transactional
    @Override
    public boolean updateOrder(Order o, Integer buyerId, Integer shippingId) throws DataException, EntityNotFoundException {
        try{
            Optional<Order> oo = orderRepo.findById(o.getOrderId());
            if(oo.isEmpty()){
                return false;
            }

            Optional<User> ob = userRepo.findById(buyerId);
            User buyer = ob.orElseThrow(() -> new EntityNotFoundException(User.class, buyerId));
            Optional<ShippingAddress> osa = shippingRepo.findById(shippingId);
            ShippingAddress sa = osa.orElseThrow(() -> new EntityNotFoundException(ShippingAddress.class, shippingId));

            o.setBuyer(buyer);
            o.setShippingAddress(sa);

            orderRepo.save(o);
            return true;
        } catch (PersistenceException pe){
            throw new DataException("errore nella modifica di un ordine", pe);
        }
    }

    @Override
    public List<Order> findOrderByBuyerId(int id) throws DataException {
        return orderRepo.findByBuyerUserId(id);
    }

    @Override
    public List<Order> findOrderByItemsSeller(int id) throws DataException {
        List<Order> orders = orderRepo.findOrdersWithItemsFromSeller(id);
        for(Order order : orders){
            entityManager.detach(order);
            List<OrderItem> filtered = order.getOrderItems().stream()
                    .filter(item -> item.getSeller().getUserId() == id).toList();
            order.setOrderItems(filtered);
        }
        return orders;
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
    public OrderItem createOrderItem(OrderItem oi, Integer orderId, Integer sellerId, Integer itemId, Integer collectionId) throws DataException, EntityNotFoundException {
        try{
            Optional<Order> ob = orderRepo.findById(orderId);
            Order order = ob.orElseThrow(() -> new EntityNotFoundException(User.class, orderId));
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

            oi.setOrder(order);
            oi.setSeller(seller);
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
    public boolean updateOrderItem(OrderItem oi, Integer orderId, Integer sellerId, Integer itemId, Integer collectionId) throws DataException, EntityNotFoundException {
        try{
            Optional<OrderItem> ooi = orderItemRepo.findById(oi.getOrderItemId());
            if(ooi.isEmpty()){
                return false;
            }

            Optional<Order> ob = orderRepo.findById(orderId);
            Order order = ob.orElseThrow(() -> new EntityNotFoundException(User.class, orderId));
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

            oi.setOrder(order);
            oi.setSeller(seller);
            oi.setItem(i);
            oi.setCollection(c);

            orderItemRepo.save(oi);
            return true;
        } catch (PersistenceException pe){
            throw new DataException("errore nella modifica di un order item", pe);
        }
    }

    @Override
    public List<OrderItem> findOrderItemsBySellerId(int id) throws DataException {
        return orderItemRepo.findBySellerUserId(id);
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
