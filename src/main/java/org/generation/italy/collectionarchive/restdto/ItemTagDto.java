package org.generation.italy.collectionarchive.restdto;

import jakarta.persistence.*;
import org.generation.italy.collectionarchive.models.entities.ItemTag;

public class ItemTagDto {
    private int itemTagId;
    private int itemId;
    private int tagId;

    public ItemTagDto() {
    }

    public ItemTagDto(int itemTagId, int itemId, int tagId) {
        this.itemTagId = itemTagId;
        this.itemId = itemId;
        this.tagId = tagId;
    }

    private ItemTag toItemTag(){
        ItemTag it = new ItemTag(itemTagId, null, null);
        return it;
    }

    private ItemTagDto toDto(ItemTag i){
        return new ItemTagDto(i.getItemTagId(), i.getItem().getItemId(), i.getTag().getTagId() );
    }

    public int getItemTagId() {
        return itemTagId;
    }

    public void setItemTagId(int itemTagId) {
        this.itemTagId = itemTagId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
