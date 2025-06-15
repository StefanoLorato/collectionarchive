package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.ShippingAddress;

public class ShippingDto {
    private Integer shippingId;
    private Integer userId;
    private String address;
    private String city;
    private String country;
    private String postalCode;

    public ShippingDto(Integer shippingId, Integer userId, String address, String city, String country, String postalCode) {
        this.shippingId = shippingId;
        this.userId = userId;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }
    public ShippingAddress toShip(){
        ShippingAddress s = new ShippingAddress(shippingId, null, address, city, country, postalCode);
        return s;
    }

    public static ShippingDto toDto(ShippingAddress s){
        return new ShippingDto(s.getShippingId(), s.getUser().getUserId(), s.getAddress(), s.getCity(), s.getCountry(), s.getPostalCode());

    }

    public Integer getShippingId() {
        return shippingId;
    }

    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}




