package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;

public class Tag {

    @Entity
    @Table(name = "tags")
    public class Tags{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "tag_id")
        private int tagId;
        @Column(name = "tag_name")
        private String tagName;
    }

    public Tag() {
    }


}
