package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private int tagId;
    @Column(name = "tag_name")
    private int tagName;

    public Tag() {
    }

    public Tag(int tagId, int tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getTagName() {
        return tagName;
    }

    public void setTagName(int tagName) {
        this.tagName = tagName;
    }
}
