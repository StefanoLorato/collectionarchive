package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.OrderItem;

public class OrderItemDto {
    private int orderItemId;
    private int orderId;
    private Integer sellerId;
    private Integer itemId;
    private Integer collectionId;
    private String name;
    private Double price;
    private String status;
    private String photo;

    public OrderItemDto() {
    }

    public OrderItemDto(int orderItemId, int orderId, Integer sellerId, Integer itemId,
                     Integer collectionId, String name, Double price, String status, String photo) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.sellerId = sellerId;
        this.itemId = itemId;
        this.collectionId = collectionId;
        this.name = name;
        this.price = price;
        this.status = status;
        this.photo = photo;
    }

    public OrderItem toOrderItem(){
        return new OrderItem(orderItemId, null, null, null, null, price, status);
    }

    public static OrderItemDto toDto(OrderItem oi){
        OrderItemDto dto =  new OrderItemDto();

        dto.setOrderItemId(oi.getOrderItemId());
        dto.setOrderId(oi.getOrder().getOrderId());
        dto.setSellerId(oi.getSeller().getUserId());
        dto.setPrice(oi.getPrice());
        dto.setStatus(oi.getStatus());

        if (oi.getItem() != null) {
            dto.setItemId(oi.getItem().getItemId());
            dto.setName(oi.getItem().getItemName());
            dto.setPhoto(oi.getItem().getItemPhoto());
            dto.setCollectionId(null);
        }
        if (oi.getCollection() != null) {
            dto.setCollectionId(oi.getCollection().getCollectionId());
            dto.setName(oi.getCollection().getCollectionName());
            if(!oi.getCollection().getItems().isEmpty()){
                String path = oi.getCollection().getItems().getFirst().getItemPhoto();
                dto.setPhoto(path);
            }
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

    public Integer getSellerId() {
        return sellerId;
    }
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
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

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
