package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;
    @Column (name = "category_name")
    private String categoryName;
    private String photo;
    private String description;

    @OneToMany (mappedBy = "category")
    private List<Collection> collections = new ArrayList<>();

    @OneToMany (mappedBy = "category")
    private List<UserPreferredCategory> userPreferredCategories = new ArrayList<>();


    public Category() {
    }

    public Category(Integer categoryId, String categoryName, String photo, String description) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.photo = photo;
        this.description = description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<Collection> getCollections() {
        return collections;
    }
    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }

    public List<UserPreferredCategory> getUserPreferredCategories() {
        return userPreferredCategories;
    }
    public void setUserPreferredCategories(List<UserPreferredCategory> userPreferredCategories) {
        this.userPreferredCategories = userPreferredCategories;
    }


}
