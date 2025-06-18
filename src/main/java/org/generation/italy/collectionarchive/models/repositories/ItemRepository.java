package org.generation.italy.collectionarchive.models.repositories;

import org.generation.italy.collectionarchive.models.entities.Collection;
import org.generation.italy.collectionarchive.models.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item> {
    List<Item> findByCollectionCollectionId(int collectionId);
    List<Item> findByCollectionCollectionIdIsNullAndUserUserId(int id);

    @Query("""
            SELECT DISTINCT i
            FROM Item i
            JOIN i.itemBookmarks b
            WHERE b.user.userId = :userId
            """)
    List<Item> findByBookmarkUserId(@Param("userId") int userId);
}
