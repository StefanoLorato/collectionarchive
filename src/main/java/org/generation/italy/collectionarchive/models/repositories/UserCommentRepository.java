package org.generation.italy.collectionarchive.models.repositories;

import org.generation.italy.collectionarchive.models.entities.UserComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCommentRepository extends JpaRepository<UserComment, Integer> {
    List<UserComment> findByCollectionCollectionId(int collectionId);
}