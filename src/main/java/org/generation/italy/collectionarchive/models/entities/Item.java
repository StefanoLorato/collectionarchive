package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "item_id")
    private int itemId;
    @ManyToOne
    @JoinColumn (name = "collection_id")
    private Collection collection;
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;
    @Column (name = "item_name")
    private String itemName;
    @Column (name = "item_description")
    private String itemDescription;
    @Column (name = "item_photo")
    private String itemPhoto;
    private String condition;
    @Column(name= "purchase_date")
    private LocalDate purchaseDate;
    @Column(name= "release_date")
    private LocalDate releaseDate;
    @Column(name= "purchase_price")
    private double purchasePrice;
    @Column (name = "sale_price")
    private double salePrice;
    @Column (name= "item_version")
    private String itemVersion;
    @Column (name= "item_edition")
    private String itemEdition;
    @Column (name = "for_sale")
    private boolean forSale;
    @Column (name ="visibility_status")
    private String visibilityStatus;

    @OneToMany (mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();
    @OneToMany (mappedBy = "item")
    private List<Discussion> discussions = new ArrayList<>();
    @OneToMany (mappedBy = "item")
    private List<CartItem> cartItems = new ArrayList<>();
    @OneToMany ( mappedBy = "item")
    private List<UserLike> itemsLikes = new ArrayList<>();
    @OneToMany(mappedBy = "item")
    private List<UserComment> itemsComments = new ArrayList<>();

    public Item() {
    }

    public Item(int itemId, Collection collection, User user,
                String itemName, String itemDescription, String itemPhoto, String condition,
                LocalDate purchaseDate, LocalDate releaseDate, double purchasePrice, double salePrice,
                String itemVersion, String itemEdition, boolean forSale, String visibilityStatus) {
        this.itemId = itemId;
        this.collection = collection;
        this.user = user;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPhoto = itemPhoto;
        this.condition = condition;
        this.purchaseDate = purchaseDate;
        this.releaseDate = releaseDate;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.itemVersion = itemVersion;
        this.itemEdition = itemEdition;
        this.forSale = forSale;
        this.visibilityStatus = visibilityStatus;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemPhoto() {
        return itemPhoto;
    }

    public void setItemPhoto(String itemPhoto) {
        this.itemPhoto = itemPhoto;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public String getItemVersion() {
        return itemVersion;
    }

    public void setItemVersion(String itemVersion) {
        this.itemVersion = itemVersion;
    }

    public String getItemEdition() {
        return itemEdition;
    }

    public void setItemEdition(String itemEdition) {
        this.itemEdition = itemEdition;
    }

    public boolean isForSale() {
        return forSale;
    }

    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }

    public String getVisibilityStatus() {
        return visibilityStatus;
    }

    public void setVisibilityStatus(String visibilityStatus) {
        this.visibilityStatus = visibilityStatus;
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

    public List<UserLike> getItemsLikes() {
        return itemsLikes;
    }

    public void setItemsLikes(List<UserLike> itemsLikes) {
        this.itemsLikes = itemsLikes;
    }

    public List<UserComment> getItemsComments() {
        return itemsComments;
    }

    public void setItemsComments(List<UserComment> itemsComments) {
        this.itemsComments = itemsComments;
    }
}
