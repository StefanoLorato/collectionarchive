package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.Order;

import java.time.LocalDateTime;

public class OrderDto {
    private int orderId;
    private int buyerId;
    private int sellerId;
    private LocalDateTime orderedAt;

    public OrderDto() {
    }

    public OrderDto(int orderId, int buyerId, int sellerId, LocalDateTime orderedAt) {
        this.orderId = orderId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.orderedAt = orderedAt;
    }

    public Order toOrder(){
        Order o = new Order(orderId, null, null, orderedAt);
        return o;
    }

    public static OrderDto toDto(Order o){
        return new OrderDto(o.getOrderId(), o.getBuyer().getUserId(), o.getSeller().getUserId(), o.getOrderedAt());
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(LocalDateTime orderedAt) {
        this.orderedAt = orderedAt;
    }
}
