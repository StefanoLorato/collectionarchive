package org.generation.italy.collectionarchive.models.repositories;

import org.generation.italy.collectionarchive.models.entities.UserFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserFeedbackRepository extends JpaRepository<UserFeedback, Integer> {
    Optional<UserFeedback> findByOrderOrderId(Integer orderId);
}