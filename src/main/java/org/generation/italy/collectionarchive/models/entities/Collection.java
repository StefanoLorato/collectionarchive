package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "collections")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="collection_id")
    private int collectionId;
    @Column(name="collection_name")
    private String collectionName;
    private boolean completed;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;
    private String visibility;
    private String description;
    @Column(name = "collection_date")
    private LocalDate collectionDate;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column (name = "for_sale")
    private boolean forSale;
    @Column (name="sale_price")
    private Double salePrice;
    @Column (name ="visibility_status")
    private String visibilityStatus;

    @OneToMany (mappedBy = "collection")
    private List<Item> items = new ArrayList<>();

    @OneToMany (mappedBy = "collection")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany (mappedBy = "collection")
    private List<Discussion> discussions = new ArrayList<>();

    @OneToMany (mappedBy = "collection")
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "collection")
    private List<Bookmark> collectionBookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "collection")
    private List<Report>  reportedCollections = new ArrayList<>();

    @OneToMany(mappedBy = "collection")
    private List<WishList> desiredItems = new ArrayList<>();

    @OneToMany(mappedBy = "collection", fetch = FetchType.EAGER)
    private List<UserLike> likes = new ArrayList<>();

    public Collection() {
    }

    public Collection(int collectionId, String collectionName, boolean completed,
                      Category category, User user, String visibility, String description, LocalDate collectionDate, LocalDateTime createdAt, boolean forSale, Double salePrice, String visibilityStatus) {
        this.collectionId = collectionId;
        this.collectionName = collectionName;
        this.completed = completed;
        this.category = category;
        this.user = user;
        this.visibility = visibility;
        this.description = description;
        this.collectionDate = collectionDate;
        this.createdAt = createdAt;
        this.forSale = forSale;
        this.salePrice = salePrice;
        this.visibilityStatus = visibilityStatus;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(LocalDate collectionDate) {
        this.collectionDate = collectionDate;
    }

    public boolean isForSale() {
        return forSale;
    }

    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public String getVisibilityStatus() {
        return visibilityStatus;
    }

    public void setVisibilityStatus(String visibilityStatus) {
        this.visibilityStatus = visibilityStatus;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<Discussion> getDiscussions() {
        return discussions;
    }

    public void setDiscussions(List<Discussion> discussions) {
        this.discussions = discussions;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public List<Bookmark> getCollectionBookmarks() {
        return collectionBookmarks;
    }

    public void setCollectionBookmarks(List<Bookmark> collectionBookmarks) {
        this.collectionBookmarks = collectionBookmarks;
    }

    public List<Report> getReportedCollections() {
        return reportedCollections;
    }

    public void setReportedCollections(List<Report> reportedCollections) {
        this.reportedCollections = reportedCollections;
    }

    public List<WishList> getDesiredItems() {
        return desiredItems;
    }

    public void setDesiredItems(List<WishList> desiredItems) {
        this.desiredItems = desiredItems;
    }

    public List<UserLike> getLikes() {
        return likes;
    }

    public void setLikes(List<UserLike> likes) {
        this.likes = likes;
    }
}
