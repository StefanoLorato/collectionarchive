package org.generation.italy.collectionarchive.models.repositories;

import org.generation.italy.collectionarchive.models.entities.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Integer> {
    List<Discussion> findByCollectionCollectionIdAndBuyerUserId(int collectionId, int buyerId);
    @Query("SELECT DISTINCT d FROM Discussion d WHERE d.buyer.userId = :id OR d.seller.userId = :id")
    List<Discussion> findByBuyerUserIdAndSellerUserId(@Param("id") int id);
}