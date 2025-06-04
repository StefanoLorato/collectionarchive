package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.OrderItem;

public class OrderItemDto {
    private int orderItemId;
    private int orderId;
    private int objectId;
    private int collectionId;
    private double  price;

    public OrderItemDto() {
    }

    public OrderItemDto(int orderItemId, int orderId, int objectId,
                     int collectionId, double price) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.objectId = objectId;
        this.collectionId = collectionId;
        this.price = price;
    }

    public OrderItem toOrderItem(){
        return new OrderItem(orderItemId, null, null, null, price);
    }

    public static OrderItemDto toDto(OrderItem oi){
        return new OrderItemDto(oi.getOrderItemId(), oi.getOrder().getOrderId(),
                                oi.getItem().getItemId(), oi.getCollection().getCollectionId(),
                                oi.getPrice());
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

    public void setOrder(int orderId) {
        this.orderId = orderId;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObject(int object) {
        this.objectId = objectId;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
