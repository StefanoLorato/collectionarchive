package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "item_tags")
public class ItemTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_tag_id")
    private int itemTagId;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public ItemTag() {
    }

    public ItemTag(int itemTagId, Item item, Tag tag) {
        this.itemTagId = itemTagId;
        this.item = item;
        this.tag = tag;
    }

    public int getItemTagId() {
        return itemTagId;
    }

    public void setItemTagId(int itemTagId) {
        this.itemTagId = itemTagId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
