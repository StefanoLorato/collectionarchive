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
public class JpaCollectionService implements CollectionService{
    private CollectionRepository collectionRepo;
    private UserRepository userRepo;
    private CategoryRepository categoryRepo;
    private OrderRepository orderRepo;
    private OrderItemRepository orderItemRepo;
    private ItemRepository itemRepo;


    @Autowired
    public JpaCollectionService( CollectionRepository collectionRepo, UserRepository userRepo,
                                 CategoryRepository categoryRepo, OrderRepository orderRepo,
                                 OrderItemRepository orderItemRepo, ItemRepository itemRepo){
        this.collectionRepo = collectionRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.itemRepo = itemRepo;
    }

    @Override
    public List<Collection> findAllCollection() throws DataException {
        return  collectionRepo.findAll();
    }

    @Override
    public Optional<Collection> findCollectionById(int id) throws DataException {
        return collectionRepo.findById(id);
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
            collectionRepo.save(c);
            return c;
        } catch (PersistenceException pe) {
            throw new DataException("errore nella creazione di una collezione", pe);
        }
    }

    @Override
    @Transactional
    public boolean deleteCollection(int id) throws DataException {
        Optional<Collection> op = collectionRepo.findById(id);
        if(op.isPresent()){
            collectionRepo.delete(op.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCollection(Collection c, int userId, int categoryId) throws DataException, EntityNotFoundException {
        try {
            Optional<Collection> co = collectionRepo.findById(c.getCollectionId());
            if(co.isEmpty()){
                return false;
            }

            Optional<User> uo = userRepo.findById(userId);
            User u = uo.orElseThrow(()->new EntityNotFoundException(User.class, userId));
            Optional<Category> oc = categoryRepo.findById(categoryId);
            Category ca = oc.orElseThrow(()-> new EntityNotFoundException(Category.class, categoryId));
            c.setUser(u);
            c.setCategory(ca);
            collectionRepo.save(c);

            return true;
        } catch (PersistenceException pe) {
            throw new DataException("errore nella modifica di una collezione", pe);
        }
    }

    @Override
    public List<Order> findAllOrders() throws DataException {
        return orderRepo.findAll();
    }

    @Override
    public Optional<Order> findOrderById(int orderId) throws DataException {
        return orderRepo.findById(orderId);
    }

    @Override
    public Order createOrder(Order o, Integer buyerId, Integer sellerId) throws DataException, EntityNotFoundException {
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
        Optional<Order>  oo = orderRepo.findById(orderId);
        if(oo.isEmpty()){
            return false;
        }
        orderRepo.delete(oo.get());
        return true;
    }

    @Transactional
    @Override
    public boolean updateOrder(Order o, Integer buyerId, Integer sellerId) throws DataException, EntityNotFoundException {
        try{
            Optional<Order> oo = orderRepo.findById(o.getOrderId());
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

//            Optional<Item> opi = itemRepo.findById(itemId);
//            Item i = opi.orElse(null);
//
//            Optional<Collection> opc = collectionRepo.findById(collectionId);
//            Collection c = opc.orElse(null);

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

//            Optional<Item> opi = itemRepo.findById(itemId);
//            Item i = opi.orElse(null);
//
//            Optional<Collection> opc = collectionRepo.findById(collectionId);
//            Collection c = opc.orElse(null);

            oi.setOrder(order);
            oi.setItem(i);
            oi.setCollection(c);

            orderItemRepo.save(oi);
            return true;
        } catch (PersistenceException pe){
            throw new DataException("errore nella modifica di un order item", pe);
        }
    }
}
