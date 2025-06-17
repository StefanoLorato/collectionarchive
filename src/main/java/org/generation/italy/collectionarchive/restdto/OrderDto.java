package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.Order;
import org.generation.italy.collectionarchive.models.entities.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {
    private int orderId;
    private Integer buyerId;
    private String buyerEmail;
    private LocalDateTime orderedAt;
    private Integer shippingAddressId;
    private List<OrderItemDto> orderItems;

    public OrderDto() {
    }

    public OrderDto(int orderId, Integer buyerId, String buyerEmail, LocalDateTime orderedAt, Integer shippingAddressId, List<OrderItemDto> orderItems) {
        this.orderId = orderId;
        this.buyerId = buyerId;
        this.buyerEmail = buyerEmail;
        this.orderedAt = orderedAt;
        this.shippingAddressId = shippingAddressId;
        this.orderItems = orderItems;
    }

    public Order toOrder(){
        Order o = new Order(orderId, null, orderedAt, null);
        // List<OrderItem> ois = orderItems.stream().map(OrderItemDto::toOrderItem).toList();
        // ois.forEach(oi -> oi.setOrder(o));
        // o.setOrderItems(ois);
        return o;
    }

    public static OrderDto toDto(Order o){
        return new OrderDto(o.getOrderId(), o.getBuyer().getUserId(), o.getBuyer().getEmail(), o.getOrderedAt(),
                o.getShippingAddress().getShippingId(),
                o.getOrderItems().stream().map(OrderItemDto::toDto).toList());
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

    public Integer getShippingAddressId() {
        return shippingAddressId;
    }
    public void setShippingAddressId(Integer shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }
    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }
}
