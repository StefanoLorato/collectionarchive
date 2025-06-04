package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookmarks")
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "bookmark_id")
    private int bookmarkId;
    @ManyToOne
    @JoinColumn(name = "user_id" )
    private User user;
    @ManyToOne
    @JoinColumn(name = "object_id" )
    private Item item;
    @ManyToOne
    @JoinColumn(name = "collection_id")
    private Collection collection;
    @Column(name = "saved_at")
    private LocalDateTime savedAt;

    public Bookmark() {
    }

    public Bookmark(int bookmarkId, User user, Item item, Collection collection, LocalDateTime savedAt) {
        this.bookmarkId = bookmarkId;
        this.user = user;
        this.item = item;
        this.collection = collection;
        this.savedAt = savedAt;
    }

    public int getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(int bookmarkId) {
        this.bookmarkId = bookmarkId;
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

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(LocalDateTime savedAt) {
        this.savedAt = savedAt;
    }
}
