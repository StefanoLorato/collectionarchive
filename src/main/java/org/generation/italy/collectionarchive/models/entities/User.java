package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "user_id")
    private int userId;
    private String name;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String country;
    private String role;
    private boolean active;

    @OneToMany (mappedBy = "user" )
    private List<Collection> collections = new ArrayList<>();
    @OneToMany (mappedBy = "user")
    private List<Item> items = new ArrayList<>();
    @OneToMany (mappedBy = "buyer")
    private List<Order> buyerOrders = new ArrayList<>();
    @OneToMany (mappedBy = "seller")
    private List<Order> sellerOrders = new ArrayList<>();
    @OneToMany (mappedBy = "buyer")
    private List<Discussion> buyerDiscussion = new ArrayList<>();
    @OneToMany (mappedBy = "seller")
    private List<Discussion> sellerDiscussion = new ArrayList<>();
    @OneToMany (mappedBy = "buyer")
    private List<CartItem> buyerCartItems = new ArrayList<>();
    @OneToMany (mappedBy = "seller")
    private List<CartItem> sellerCartItems = new ArrayList<>();


    public User() {
    }

    public User(int userId, String name, String lastname, String username,
                String password, String email, String country, String role, boolean active) {
        this.userId = userId;
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.country = country;
        this.role = role;
        this.active = active;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Collection> getCollections() {
        return collections;
    }

    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Order> getBuyerOrders() {
        return buyerOrders;
    }

    public void setBuyerOrders(List<Order> buyerOrders) {
        this.buyerOrders = buyerOrders;
    }

    public List<Order> getSellerOrders() {
        return sellerOrders;
    }

    public void setSellerOrders(List<Order> sellerOrders) {
        this.sellerOrders = sellerOrders;
    }

    public List<Discussion> getBuyerDiscussion() {
        return buyerDiscussion;
    }

    public void setBuyerDiscussion(List<Discussion> buyerDiscussion) {
        this.buyerDiscussion = buyerDiscussion;
    }

    public List<Discussion> getSellerDiscussion() {
        return sellerDiscussion;
    }

    public void setSellerDiscussion(List<Discussion> sellerDiscussion) {
        this.sellerDiscussion = sellerDiscussion;
    }

    public List<CartItem> getBuyerCartItems() {
        return buyerCartItems;
    }

    public void setBuyerCartItems(List<CartItem> buyerCartItems) {
        this.buyerCartItems = buyerCartItems;
    }

    public List<CartItem> getSellerCartItems() {
        return sellerCartItems;
    }

    public void setSellerCartItems(List<CartItem> sellerCartItems) {
        this.sellerCartItems = sellerCartItems;
    }
}
