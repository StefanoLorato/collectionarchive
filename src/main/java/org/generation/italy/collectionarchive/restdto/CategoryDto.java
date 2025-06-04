package org.generation.italy.collectionarchive.restdto;

import jakarta.persistence.*;
import org.generation.italy.collectionarchive.models.entities.Category;

public class CategoryDto {
        private int categoryId;
        private String categoryName;


        public CategoryDto() {
        }

        public CategoryDto(int categoryId, String categoryName) {
            this.categoryId = categoryId;
            this.categoryName = categoryName;
        }

        public Category toCategory(){
            Category c = new Category(categoryId, categoryName);
            return c;
        }

        public static CategoryDto toDto(Category c){
            return new CategoryDto(c.getCategoryId(), c.getCategoryName());
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

}
