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
    private ItemRepository itemRepo;
    private OrderRepository orderRepo;
    private OrderItemRepository orderItemRepo;


    @Autowired
    public JpaCollectionService( CollectionRepository collectionRepo, UserRepository userRepo,
                                 CategoryRepository categoryRepo, ItemRepository itemRepo,
                                 OrderRepository orderRepo,
                                 OrderItemRepository orderItemRepo){
        this.collectionRepo = collectionRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
        this.itemRepo = itemRepo;
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
    }

    //COLLECTION
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
            throw new DataException("errore nella creazione di un nuovo prodotto", pe);
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
    @Transactional
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
            throw new DataException("errore nella modifica di un prodotto", pe);
        }
    }

    //ITEM
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
            Optional<Collection> oc = collectionRepo.findById(collectionId);
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
    @Transactional
    public boolean deleteItem(int id) throws DataException {
        Optional<Item> io = itemRepo.findById(id);
        if(io.isPresent()){
            itemRepo.delete(io.get());
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean updateItem(Item i,int userId, int collectionId) throws DataException, EntityNotFoundException {
        try {
            Optional<Item> io = itemRepo.findById(i.getItemId());
            if(io.isEmpty()){
                return false;
            }
            Optional<User> uo = userRepo.findById(userId);
            User u = uo.orElseThrow(()->new EntityNotFoundException(User.class, userId));
            Optional<Collection> oc = collectionRepo.findById(collectionId);
            Collection co = oc.orElseThrow(()-> new EntityNotFoundException(Collection.class, collectionId));
            i.setUser(u);
            i.setCollection(co);
            itemRepo.save(i);

            return true;
        } catch (PersistenceException pe) {
            throw new DataException("errore nella modifica di un prodotto", pe);
        }
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

            Optional<Item> opi = itemRepo.findById(itemId);
            Item i = opi.orElse(null);

            Optional<Collection> opc = collectionRepo.findById(collectionId);
            Collection c = opc.orElse(null);

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

            Optional<Item> opi = itemRepo.findById(itemId);
            Item i = opi.orElse(null);

            Optional<Collection> opc = collectionRepo.findById(collectionId);
            Collection c = opc.orElse(null);

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
