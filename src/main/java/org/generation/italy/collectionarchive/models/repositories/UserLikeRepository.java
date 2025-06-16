package org.generation.italy.collectionarchive.models.repositories;

import org.generation.italy.collectionarchive.models.entities.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLikeRepository extends JpaRepository<UserLike, Integer> {
    List<UserLike> findByUserUserId(int userId);
    boolean existsByUserUserIdAndItemItemIdAndCollectionCollectionId(Integer userId, Integer itemId, Integer collectionId);
}