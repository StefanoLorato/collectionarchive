package org.generation.italy.collectionarchive.restdto;

import jakarta.persistence.*;
import org.generation.italy.collectionarchive.models.entities.Category;

public class CategoryDto {
    private Integer categoryId;
    private String categoryName;
    private String photo;
    private String description;


    public CategoryDto() {
    }

    public CategoryDto(Integer categoryId, String categoryName, String photo, String description) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.photo = photo;
        this.description = description;
    }

    public Category toCategory(){
        return new Category(categoryId, categoryName, photo, description);
    }

    public static CategoryDto toDto(Category c){
        return new CategoryDto(c.getCategoryId(), c.getCategoryName(), c.getPhoto(), c.getDescription());
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
}
