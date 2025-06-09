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

    //ORDERS
    @OneToMany (mappedBy = "buyer")
    private List<Order> buyerOrders = new ArrayList<>();
    @OneToMany (mappedBy = "seller")
    private List<Order> sellerOrders = new ArrayList<>();

    //DISCUSSIONS
    @OneToMany (mappedBy = "buyer")
    private List<Discussion> buyerDiscussions = new ArrayList<>();
    @OneToMany (mappedBy = "seller")
    private List<Discussion> sellerDiscussions = new ArrayList<>();

    //CARTITEMS
    @OneToMany (mappedBy = "buyer")
    private List<CartItem> buyerCartItems = new ArrayList<>();
    @OneToMany (mappedBy = "seller")
    private List<CartItem> sellerCartItems = new ArrayList<>();

    //USERS
    @OneToMany(mappedBy = "user")
    private List<UserContact>  userContacts = new ArrayList<>();
    @OneToMany(mappedBy = "fromUser")
    private List<UserFeedback>  feedbacksFromUser = new ArrayList<>();
    @OneToMany (mappedBy =  "toUser")
    private List<UserFeedback> feedbacksToUser = new ArrayList<>();
    @OneToMany (mappedBy = "user")
    private List<UserLike> userLikes = new ArrayList<>();
    @OneToMany (mappedBy = "user")
    private List<UserComment> userComment = new ArrayList<>();
    @OneToMany (mappedBy = "user")
    private List<UserPreferredCategory> userPreferredCategories = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Bookmark> userBookmarks = new ArrayList<>();
    @OneToMany (mappedBy = "user")
    private List<ShippingAddress> shippingAddresses = new ArrayList<>();

    //NOTIFICATION
    @OneToMany (mappedBy = "user")
    private List<Notification> userNotification = new ArrayList<>();
    @OneToMany (mappedBy = "fromUser")
    private List<Notification> fromUserNotification = new ArrayList<>();

    //REPORTS
    @OneToMany(mappedBy = "reporter")
    private List<Report>  reporters = new ArrayList<>();
    @OneToMany (mappedBy =  "reportedUser")
    private List<Report> reportedUsers = new ArrayList<>();


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

    public List<Discussion> getBuyerDiscussions() {
        return buyerDiscussions;
    }

    public void setBuyerDiscussions(List<Discussion> buyerDiscussions) {
        this.buyerDiscussions = buyerDiscussions;
    }

    public List<Discussion> getSellerDiscussions() {
        return sellerDiscussions;
    }

    public void setSellerDiscussions(List<Discussion> sellerDiscussions) {
        this.sellerDiscussions = sellerDiscussions;
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

    public List<UserFeedback> getFeedbacksToUser() {
        return feedbacksToUser;
    }

    public void setFeedbacksToUser(List<UserFeedback> feedbacksToUser) {
        this.feedbacksToUser = feedbacksToUser;
    }

    public List<UserFeedback> getFeedbacksFromUser() {
        return feedbacksFromUser;
    }

    public void setFeedbacksFromUser(List<UserFeedback> feedbacksFromUser) {
        this.feedbacksFromUser = feedbacksFromUser;
    }

    public List<UserLike> getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(List<UserLike> userLikes) {
        this.userLikes = userLikes;
    }

    public List<UserComment> getUserComment() {
        return userComment;
    }

    public void setUserComment(List<UserComment> userComment) {
        this.userComment = userComment;
    }

    public List<UserContact> getUserContacts() {
        return userContacts;
    }

    public void setUserContacts(List<UserContact> userContacts) {
        this.userContacts = userContacts;
    }

    public List<UserPreferredCategory> getUserPreferredCategories() {
        return userPreferredCategories;
    }

    public void setUserPreferredCategories(List<UserPreferredCategory> userPreferredCategories) {
        this.userPreferredCategories = userPreferredCategories;
    }

    public List<Bookmark> getUserBookmarks() {
        return userBookmarks;
    }

    public void setUserBookmarks(List<Bookmark> userBookmarks) {
        this.userBookmarks = userBookmarks;
    }

    public List<ShippingAddress> getShippingAdresses() {
        return shippingAddresses;
    }

    public void setShippingAdresses(List<ShippingAddress> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }

    public List<Notification> getUserNotification() {
        return userNotification;
    }

    public void setUserNotification(List<Notification> userNotification) {
        this.userNotification = userNotification;
    }

    public List<Notification> getFromUserNotification() {
        return fromUserNotification;
    }

    public void setFromUserNotification(List<Notification> fromUserNotification) {
        this.fromUserNotification = fromUserNotification;
    }

    public List<Report> getReporters() {
        return reporters;
    }

    public void setReporters(List<Report> reporters) {
        this.reporters = reporters;
    }

    public List<Report> getReportedUsers() {
        return reportedUsers;
    }

    public void setReportedUsers(List<Report> reportedUsers) {
        this.reportedUsers = reportedUsers;
    }

    public Long getId() {
        return 0L;
    }
    public void masterSetName(String masterUser) {
    }
}
