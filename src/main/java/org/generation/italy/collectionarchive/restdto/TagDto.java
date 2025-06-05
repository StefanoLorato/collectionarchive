package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.Tag;

public class TagDto {
    private int tagId;
    private String tagName;

    public TagDto() {
    }

    public TagDto(int tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public Tag toTag(){
        Tag t = new Tag(tagId, tagName);
        return t;
    }

    public static TagDto toDto(Tag t){
        return new TagDto(t.getTagId(), t.getTagName());
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
}
