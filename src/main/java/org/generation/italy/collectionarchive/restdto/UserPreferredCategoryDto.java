package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.UserPreferredCategory;

public class UserPreferredCategoryDto {
    private int preferenceId;
    private int userId;
    private int categoryId;

    public UserPreferredCategoryDto() {
    }

    public UserPreferredCategoryDto(int preferenceId, int userId, int categoryId) {
        this.preferenceId = preferenceId;
        this.userId = userId;
        this.categoryId = categoryId;
    }
    public UserPreferredCategory toUserPreferredCategory(){
        UserPreferredCategory upc = new UserPreferredCategory(preferenceId, null, null);
        return upc;
    }
    public static UserPreferredCategoryDto toDto(UserPreferredCategory upc){
        return new UserPreferredCategoryDto(upc.getPreferenceId(), upc.getUser().getUserId(), upc.getCategory().getCategoryId());
    }

    public int getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(int preferenceId) {
        this.preferenceId = preferenceId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
