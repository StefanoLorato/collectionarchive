package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private int orderItemId;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "object_id")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "collection_id")
    private Collection collection;
    private double  price;

    public OrderItem() {
    }

    public OrderItem(int orderItemId, Order order, Item item,
                     Collection collection, double price) {
        this.orderItemId = orderItemId;
        this.order = order;
        this.item = item;
        this.collection = collection;
        this.price = price;
    }

    public int getOrderItemId() {
        return orderItemId;
    }
    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }

    public Collection getCollection() {
        return collection;
    }
    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
