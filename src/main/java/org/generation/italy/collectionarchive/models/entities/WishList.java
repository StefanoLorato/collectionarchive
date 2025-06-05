package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "wishlist")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "desired_item_id")
    private int desiredItemId;
    @ManyToOne
    @JoinColumn (name = "collection_id")
    private Collection collection;
    @Column(name= "item_name")
    private String itemName;
    @Column(name= "item_description")
    private String itemDescription;
    @Column(name= "release_date")
    private LocalDate releaseDate;
    @Column(name= "item_version")
    private String itemVersion;
    @Column(name= "item_edition")
    private String itemEdition;

    public WishList() {
    }

    public WishList(int desiredItemId, Collection collection, String itemName,
                    String itemDescription, LocalDate releaseDate,
                    String itemVersion, String itemEdition) {
        this.desiredItemId = desiredItemId;
        this.collection = collection;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.releaseDate = releaseDate;
        this.itemVersion = itemVersion;
        this.itemEdition = itemEdition;
    }

    public int getDesiredItemId() {
        return desiredItemId;
    }

    public void setDesiredItemId(int desiredItemId) {
        this.desiredItemId = desiredItemId;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
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


    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
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
}
