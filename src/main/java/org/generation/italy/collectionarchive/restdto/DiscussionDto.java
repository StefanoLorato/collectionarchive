package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.Discussion;

public class DiscussionDto {
    private int discussionId;
    private int buyerId;
    private int sellerId;
    private int itemId;
    private int collectionId;

    public DiscussionDto() {
    }

    public DiscussionDto(int discussionId, int buyerId, int sellerId, int itemId, int collectionId) {
        this.discussionId = discussionId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.itemId = itemId;
        this.collectionId = collectionId;
    }

    public Discussion toDiscussion(){
        return new Discussion(discussionId, null, null, null, null);
    }

    public static DiscussionDto toDto(Discussion d){
        return new DiscussionDto(d.getDiscussionId(), d.getBuyer().getUserId(), d.getSeller().getUserId(),
                                d.getItem().getItemId(), d.getCollection().getCollectionId());
    }

    public int getDiscussionId() {
        return discussionId;
    }
    public void setDiscussionId(int discussionId) {
        this.discussionId = discussionId;
    }

    public int getBuyerId() {
        return buyerId;
    }
    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getSellerId() {
        return sellerId;
    }
    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
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
}
