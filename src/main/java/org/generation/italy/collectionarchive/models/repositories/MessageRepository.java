package org.generation.italy.collectionarchive.models.repositories;

import org.generation.italy.collectionarchive.models.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query("SELECT m FROM Message m WHERE m.discussion.id = :discussionId ORDER BY m.sentAt ASC")
    List<Message> findByDiscussion_DiscussionIdOrderBySentAtAsc(Integer discussionId);
}
