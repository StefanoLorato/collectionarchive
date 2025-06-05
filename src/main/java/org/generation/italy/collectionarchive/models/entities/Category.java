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
    private int categoryId;
    @Column (name = "category_name")
    private String categoryName;

    @OneToMany (mappedBy = "category")
    private List<Collection> collections = new ArrayList<>();

    @OneToMany (mappedBy = "category")
    private List<UserPreferredCategory> userPreferredCategories = new ArrayList<>();


    public Category() {
    }

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
