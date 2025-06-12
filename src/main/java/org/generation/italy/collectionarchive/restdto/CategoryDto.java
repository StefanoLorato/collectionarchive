package org.generation.italy.collectionarchive.restdto;

import jakarta.persistence.*;
import org.generation.italy.collectionarchive.models.entities.Category;

public class CategoryDto {
    private Integer categoryId;
    private String categoryName;
    private String photo;


    public CategoryDto() {
    }

    public CategoryDto(Integer categoryId, String categoryName, String photo) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.photo = photo;
    }

    public Category toCategory(){
        return new Category(categoryId, categoryName, photo);
    }

    public static CategoryDto toDto(Category c){
        return new CategoryDto(c.getCategoryId(), c.getCategoryName(), c.getPhoto());
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
}
