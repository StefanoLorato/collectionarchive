package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.Order;

import java.time.LocalDateTime;

public class OrderDto {
    private int orderId;
    private Integer buyerId;
    private Integer sellerId;
    private LocalDateTime orderedAt;
    private String status;
    private Integer shippingAddressId;

    public OrderDto() {
    }

    public OrderDto(int orderId, Integer buyerId, Integer sellerId, LocalDateTime orderedAt, String status, Integer shippingAddressId) {
        this.orderId = orderId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.orderedAt = orderedAt;
        this.status = status;
        this.shippingAddressId = shippingAddressId;
    }

    public Order toOrder(){
        Order o = new Order(orderId, null, null, orderedAt, status, null);
        return o;
    }

    public static OrderDto toDto(Order o){
        return new OrderDto(o.getOrderId(), o.getBuyer().getUserId(), o.getSeller().getUserId(), o.getOrderedAt(), o.getStatus(), o.getShippingAddress().getShippingId());
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }
    public void setOrderedAt(LocalDateTime orderedAt) {
        this.orderedAt = orderedAt;
    }

    public Integer getBuyerId() {
        return buyerId;
    }
    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getSellerId() {
        return sellerId;
    }
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getShippingAddressId() {
        return shippingAddressId;
    }
    public void setShippingAddressId(Integer shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }
}
