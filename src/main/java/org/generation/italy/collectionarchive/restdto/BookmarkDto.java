package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.Bookmark;

import java.time.LocalDateTime;

public class BookmarkDto {
    private int bookmarkId;
    private int userId;
    private int itemId;
    private int collectionId;
    private LocalDateTime savedAt;

    public BookmarkDto() {
    }

    public BookmarkDto(int bookmarkId, int userId, int itemId, int collectionId, LocalDateTime savedAt) {
        this.bookmarkId = bookmarkId;
        this.userId = userId;
        this.itemId = itemId;
        this.collectionId = collectionId;
        this.savedAt = savedAt;
    }

    private Bookmark ToBookMark() {
        Bookmark bm = new Bookmark(bookmarkId, null, null, null, savedAt);
        return bm;
    }

    private static BookmarkDto toDto(Bookmark bm) {
        return new BookmarkDto(bm.getBookmarkId(), bm.getUser().getUserId(),
                bm.getItem().getItemId(), bm.getCollection().getCollectionId(), bm.getSavedAt());
    }

    public int getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(int bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(LocalDateTime savedAt) {
        this.savedAt = savedAt;
    }
}

