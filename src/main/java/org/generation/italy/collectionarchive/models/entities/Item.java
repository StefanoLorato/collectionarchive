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
    @ManyToOne(cascade = CascadeType.MERGE)
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
    private Double purchasePrice;
    @Column (name = "sale_price")
    private Double salePrice;
    @Column (name= "item_version")
    private String itemVersion;
    @Column (name= "item_edition")
    private String itemEdition;
    @Column (name = "for_sale")
    private boolean forSale;
    @Column (name ="visibility_status")
    private String visibilityStatus;


    @OneToMany (mappedBy = "item")
    private List<ItemTag> itemTags = new ArrayList<>();

    @OneToMany (mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany (mappedBy = "item")
    private List<Discussion> discussions = new ArrayList<>();

    @OneToMany (mappedBy = "item")
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany ( mappedBy = "item", fetch = FetchType.EAGER)
    private List<UserLike> itemsLikes = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<UserComment> itemsComments = new ArrayList<>();

    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER)
    private List<Bookmark> itemBookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<Report>  reportedItems = new ArrayList<>();



    public Item() {
    }

    public Item(int itemId, Collection collection, User user,
                String itemName, String itemDescription, String itemPhoto, String condition,
                LocalDate purchaseDate, LocalDate releaseDate, Double purchasePrice, Double salePrice,
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

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
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

    public List<ItemTag> getItemTags() {
        return itemTags;
    }

    public void setItemTags(List<ItemTag> itemTags) {
        this.itemTags = itemTags;
    }

    public List<Bookmark> getItemBookmarks() {
        return itemBookmarks;
    }

    public void setItemBookmarks(List<Bookmark> itemBookmarks) {
        this.itemBookmarks = itemBookmarks;
    }

    public List<Report> getReportedItems() {
        return reportedItems;
    }

    public void setReportedItems(List<Report> reportedItems) {
        this.reportedItems = reportedItems;
    }
}
