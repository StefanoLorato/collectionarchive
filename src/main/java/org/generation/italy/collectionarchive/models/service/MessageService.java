package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.Message;
import org.generation.italy.collectionarchive.models.exceptions.DataException;

import java.util.List;

public interface MessageService {
    Message saveMessage(Message msg, Integer discussionId, Integer senderId, Integer receiverId) throws DataException;
    List<Message> getMessagesByDiscussionId(Integer discussionId) throws DataException;

}
