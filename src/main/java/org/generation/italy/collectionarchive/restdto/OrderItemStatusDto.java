package org.generation.italy.collectionarchive.restdto;

public class OrderItemStatusDto {
    private String status;

    public OrderItemStatusDto() {
    }

    public OrderItemStatusDto(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
