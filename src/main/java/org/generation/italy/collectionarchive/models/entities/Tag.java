package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private int tagId;
    @Column(name = "tag_name")
    private String tagName;

    @OneToMany (mappedBy = "tag")
    private List<ItemTag> itemTags = new ArrayList<>();

    public Tag() {
    }

    public Tag(int tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<ItemTag> getItemTags() {
        return itemTags;
    }

    public void setItemTags(List<ItemTag> itemTags) {
        this.itemTags = itemTags;
    }
}
