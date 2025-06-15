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
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;
    @Column (name="ordered_at")
    private LocalDateTime orderedAt;
    private String status;
    @ManyToOne
    @JoinColumn (name="shipping_id")
    private ShippingAddress shippingAddress;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {
    }

    public Order(int orderId, User buyer, User seller, LocalDateTime orderedAt, String status, ShippingAddress shippingAddress) {
        this.orderId = orderId;
        this.buyer = buyer;
        this.seller = seller;
        this.orderedAt = orderedAt;
        this.status = status;
        this.shippingAddress = shippingAddress;
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

    public User getSeller() {
        return seller;
    }
    public void setSeller(User seller) {
        this.seller = seller;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }
    public void setOrderedAt(LocalDateTime orderedAt) {
        this.orderedAt = orderedAt;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
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
