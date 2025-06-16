package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.Notification;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    List<Notification> findAllNotification() throws DataException;
    Optional<Notification> findNotificationId(int notificationId) throws DataException;
    Notification createNotification(Notification c, int userId, int fromUserId, Integer likeId, Integer commentId, Integer messageId, Integer feedbackId) throws DataException, EntityNotFoundException;
    boolean deleteNotification(int notificationId) throws DataException;
    boolean updateNotification(Notification c, int userId, int fromUserId, Integer likeId, Integer commentId, Integer messageId, Integer feedbackId);
}
