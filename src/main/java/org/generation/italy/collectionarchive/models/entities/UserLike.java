package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name="user_likes")
public class UserLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name= "like_id")
    private int likeId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "object_id")
    private Item item;

    public UserLike() {
    }

    public UserLike(int likeId, User user, Item item) {
        this.likeId = likeId;
        this.user = user;
        this.item = item;
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
