package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "wishlist")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "desired_object_id")
    private int desiredObjectId;
    @ManyToOne
    @JoinColumn (name = "collection_id")
    private Collection collection;
    @Column(name= "object_name")
    private String objectName;
    @Column(name= "object_description")
    private String objectDescription;
    @Column(name= "release_date")
    private LocalDate releaseDate;
    @Column(name= "object_version")
    private String objectVersion;
    @Column(name= "object_edition")
    private String objectEdition;

    public WishList() {
    }

    public WishList(int desiredObjectId, Collection collection, String objectName,
                    String objectDescription, LocalDate releaseDate,
                    String objectVersion, String objectEdition) {
        this.desiredObjectId = desiredObjectId;
        this.collection = collection;
        this.objectName = objectName;
        this.objectDescription = objectDescription;
        this.releaseDate = releaseDate;
        this.objectVersion = objectVersion;
        this.objectEdition = objectEdition;
    }

    public int getDesiredObjectId() {
        return desiredObjectId;
    }

    public void setDesiredObjectId(int desiredObjectId) {
        this.desiredObjectId = desiredObjectId;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
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


    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
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
}
