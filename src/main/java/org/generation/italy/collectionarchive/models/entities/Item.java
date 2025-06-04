package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "objects")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "object_id")
    private int itemId;
    @ManyToOne
    @JoinColumn (name = "collection_id")
    private Collection collection;
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;
    @Column (name = "object_name")
    private String objectName;
    @Column (name = "object_description")
    private String objectDescription;
    @Column (name = "object_photo")
    private String objectPhoto;
    private String condition;
    @Column(name= "purchase_date")
    private LocalDate purchaseDate;
    @Column(name= "release_date")
    private LocalDate releaseDate;
    @Column(name= "purchase_price")
    private double purchasePrice;
    @Column (name = "sale_price")
    private double salePrice;
    @Column (name= "object_version")
    private String objectVersion;
    @Column (name= "object_edition")
    private String objectEdition;
    @Column (name = "for_sale")
    private boolean forSale;
    @Column (name ="visibility_status")
    private String visibilityStatus;

    @OneToMany (mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();
    @OneToMany (mappedBy = "item")
    private List<Discussion> discussion = new ArrayList<>();
    @OneToMany (mappedBy = "item")
    private List<CartItem> cartItems = new ArrayList<>();

    public Item() {
    }

    public Item(int itemId, Collection collection, User user,
                String objectName, String objectDescription, String objectPhoto, String condition,
                LocalDate purchaseDate, LocalDate releaseDate, double purchasePrice, double salePrice,
                String objectVersion, String objectEdition, boolean forSale, String visibilityStatus) {
        this.itemId = itemId;
        this.collection = collection;
        this.user = user;
        this.objectName = objectName;
        this.objectDescription = objectDescription;
        this.objectPhoto = objectPhoto;
        this.condition = condition;
        this.purchaseDate = purchaseDate;
        this.releaseDate = releaseDate;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.objectVersion = objectVersion;
        this.objectEdition = objectEdition;
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

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectDescription() {
        return objectDescription;
    }

    public void setObjectDescription(String objectDescription) {
        this.objectDescription = objectDescription;
    }

    public String getObjectPhoto() {
        return objectPhoto;
    }

    public void setObjectPhoto(String objectPhoto) {
        this.objectPhoto = objectPhoto;
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

    public String getObjectVersion() {
        return objectVersion;
    }

    public void setObjectVersion(String objectVersion) {
        this.objectVersion = objectVersion;
    }

    public String getObjectEdition() {
        return objectEdition;
    }

    public void setObjectEdition(String objectEdition) {
        this.objectEdition = objectEdition;
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
}
