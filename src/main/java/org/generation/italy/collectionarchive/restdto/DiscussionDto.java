package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.Collection;
import org.generation.italy.collectionarchive.models.entities.Discussion;
import org.generation.italy.collectionarchive.models.entities.Item;
import org.generation.italy.collectionarchive.models.entities.User;

import java.util.Collections;
import java.util.List;

public class DiscussionDto {
    private Integer discussionId;
    private Integer buyerId;
    private Integer sellerId;
    private Integer itemId;
    private Integer collectionId;
    private List<MessageDto> messages;
    private String name;
    private String buyerName;
    private String sellerName;



    public DiscussionDto() {
    }

    public DiscussionDto(Integer discussionId, Integer buyerId, Integer sellerId, Integer itemId, Integer collectionId, List<MessageDto> messages) {
        this.discussionId = discussionId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.itemId = itemId;
        this.collectionId = collectionId;
        this.messages = messages;

    }

    public Discussion toDiscussion(){
        return new Discussion(discussionId, null, null, null, null);
    }

    public static DiscussionDto toDto(Discussion d, boolean includeMessages){
        Integer itemId = (d.getItem() != null) ? d.getItem().getItemId() : null;
        Integer collectionId = (d.getCollection() != null) ? d.getCollection().getCollectionId() : null;
        Integer buyerId = d.getBuyer().getUserId();
        Integer sellerId = d.getSeller().getUserId();
        List<MessageDto> msg = d.getMessages().stream().map(MessageDto::toDto).toList();

        List<MessageDto> discussionMsg = includeMessages
                ? (d.getMessages() != null
                ? d.getMessages().stream().map(MessageDto::toDto).toList()
                : Collections.emptyList())
                : null;


        DiscussionDto dto = new DiscussionDto(
                d.getDiscussionId(),
                buyerId,
                sellerId,
                itemId,
                collectionId,
                msg
        );

        dto.setBuyerName(d.getBuyer().getName());
        dto.setSellerName(d.getSeller().getName());

        if(d.getItem() != null){
            dto.setName(d.getItem().getItemName());
        }

        if(d.getCollection() != null) {
            dto.setName(d.getCollection().getCollectionName());
        }
        return dto;

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

    public List<MessageDto> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDto> messages) {
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}