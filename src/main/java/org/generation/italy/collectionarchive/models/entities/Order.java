package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name= "order_id")
    private int orderId;
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;
    @Column (name="ordered_at")
    private LocalDateTime orderedAt;
    @ManyToOne
    @JoinColumn (name="shipping_id")
    private ShippingAddress shippingAddress;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems = new ArrayList<>();
    @OneToMany(mappedBy = "order")
    private List<UserFeedback> orderFeedbacks = new ArrayList<>();

    public Order() {
    }

    public Order(int orderId, User buyer, LocalDateTime orderedAt, ShippingAddress shippingAddress) {
        this.orderId = orderId;
        this.buyer = buyer;
        this.orderedAt = orderedAt;
        this.shippingAddress = shippingAddress;
    }

    public void addOrderItem(OrderItem oi){
        orderItems.add(oi);
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getBuyer() {
        return buyer;
    }
    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }
    public void setOrderedAt(LocalDateTime orderedAt) {
        this.orderedAt = orderedAt;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }
    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
