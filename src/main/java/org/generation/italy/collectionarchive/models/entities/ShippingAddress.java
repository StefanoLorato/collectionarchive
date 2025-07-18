package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shipping_addresses")
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_id")
    private Integer shippingId;
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "postalcode")
    private String postalCode;

    @OneToMany(mappedBy = "shippingAddress")
    private List<Order> shippingAddresses = new ArrayList<>();

    public ShippingAddress() {
    }

    public ShippingAddress(Integer shippingId, User user, String address, String city, String country, String postalCode) {
        this.shippingId = shippingId;
        this.user = user;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }

    public Integer getShippingId() {
        return shippingId;
    }
    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
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

    public List<Order> getShippingAddresses() {
        return shippingAddresses;
    }
    public void setShippingAddresses(List<Order> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }
}