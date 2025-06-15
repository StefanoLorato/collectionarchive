package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.Bookmark;

import java.time.LocalDateTime;

public class BookmarkDto {
    private int bookmarkId;
    private int userId;
    private Integer itemId;
    private Integer collectionId;
    private LocalDateTime savedAt;

    public BookmarkDto() {
    }

    public BookmarkDto(int bookmarkId, int userId, Integer itemId, Integer collectionId, LocalDateTime savedAt) {
        this.bookmarkId = bookmarkId;
        this.userId = userId;
        this.itemId = itemId;
        this.collectionId = collectionId;
        this.savedAt = savedAt;
    }

    public Bookmark ToBookMark() {
        Bookmark bm = new Bookmark(bookmarkId, null, null, null, savedAt);
        return bm;
    }

    public static BookmarkDto toDto(Bookmark bm) {
        BookmarkDto bDto =  new BookmarkDto();

        bDto.setBookmarkId(bm.getBookmarkId());
        bDto.setUserId(bm.getUser().getUserId());
        bDto.setSavedAt(bm.getSavedAt());

        if(bm.getItem() != null){
           bDto.setItemId(bm.getItem().getItemId());
        }
        if(bm.getCollection() != null){
            bDto.setCollectionId(bm.getCollection().getCollectionId());
        }
        return bDto;
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

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(LocalDateTime savedAt) {
        this.savedAt = savedAt;
    }
}

