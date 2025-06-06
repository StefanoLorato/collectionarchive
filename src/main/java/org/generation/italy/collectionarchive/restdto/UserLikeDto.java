package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.UserLike;

public class UserLikeDto {
    private int likeId;
    private int userId;
    private int itemId;

    public UserLikeDto() {
    }

    public UserLikeDto(int likeId, int userId, int itemId) {
        this.likeId = likeId;
        this.userId = userId;
        this.itemId = itemId;
    }


    public UserLike toUserLike(){
        UserLike ul = new UserLike(likeId, null, null);
        return ul;
    }

    public static UserLikeDto toDto(UserLike ul){
        return new UserLikeDto(ul.getLikeId(), ul.getUser().getUserId(), ul.getItem().getItemId());
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public int getUser() {
        return userId;
    }

    public void setUser(int user) {
        this.userId = user;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
