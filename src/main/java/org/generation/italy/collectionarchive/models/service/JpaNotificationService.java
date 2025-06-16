package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.Notification;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaNotificationService implements NotificationService{


    @Override
    public List<Notification> findAllNotification() throws DataException {
        return List.of();
    }

    @Override
    public Optional<Notification> findNotificationId(int notificationId) throws DataException {
        return Optional.empty();
    }

    @Override
    public Notification createNotification(Notification c, int userId, int fromUserId, Integer likeId, Integer commentId, Integer messageId, Integer feedbackId) throws DataException, EntityNotFoundException {
        return null;
    }

    @Override
    public boolean deleteNotification(int notificationId) throws DataException {
        return false;
    }

    @Override
    public boolean updateNotification(Notification c, int userId, int fromUserId, Integer likeId, Integer commentId, Integer messageId, Integer feedbackId) {
        return false;
    }
}
