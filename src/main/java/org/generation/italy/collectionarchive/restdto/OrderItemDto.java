package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.OrderItem;

public class OrderItemDto {
    private int orderItemId;
    private int orderId;
    private Integer itemId;
    private Integer collectionId;
    private Double price;

    public OrderItemDto() {
    }

    public OrderItemDto(int orderItemId, Integer orderId, Integer itemId,
                     int collectionId, Double price) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.itemId = itemId;
        this.collectionId = collectionId;
        this.price = price;
    }

    public OrderItem toOrderItem(){
        return new OrderItem(orderItemId, null, null, null, price);
    }

    public static OrderItemDto toDto(OrderItem oi){
        OrderItemDto dto =  new OrderItemDto();

        dto.setOrderItemId(oi.getOrderItemId());
        dto.setOrderId(oi.getOrder().getOrderId());
        dto.setPrice(oi.getPrice());

        if (oi.getItem() != null) {
            dto.setItemId(oi.getItem().getItemId());
            dto.setCollectionId(null);
        }
        if (oi.getCollection() != null) {
            dto.setCollectionId(oi.getCollection().getCollectionId());
        }

        return dto;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
