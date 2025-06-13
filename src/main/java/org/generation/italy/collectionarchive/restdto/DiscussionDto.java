package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.Collection;
import org.generation.italy.collectionarchive.models.entities.Discussion;
import org.generation.italy.collectionarchive.models.entities.Item;
import org.generation.italy.collectionarchive.models.entities.User;

public class DiscussionDto {
    private Integer discussionId;
    private Integer buyerId;
    private Integer sellerId;
    private Integer itemId;
    private Integer collectionId;

    public DiscussionDto() {
    }

    public DiscussionDto(Integer discussionId, Integer buyerId, Integer sellerId, Integer itemId, Integer collectionId) {
        this.discussionId = discussionId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.itemId = itemId;
        this.collectionId = collectionId;
    }

    public Discussion toDiscussion(User buyer, User seller, Item item, Collection collection){
        return new Discussion(discussionId, buyer, seller, item, collection);
    }


    public static DiscussionDto toDto(Discussion d){
        return new DiscussionDto(d.getDiscussionId(), d.getBuyer().getUserId(), d.getSeller().getUserId(),
                                d.getItem().getItemId(), d.getCollection().getCollectionId());
    }

    public Integer getDiscussionId() {
        return discussionId;
    }
    public void setDiscussionId(Integer discussionId) {
        this.discussionId = discussionId;
    }

    public Integer getBuyerId() {
        return buyerId;
    }
    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getSellerId() {
        return sellerId;
    }
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
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
}
