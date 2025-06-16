package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.Discussion;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.restdto.DiscussionDto;

public interface DiscussionService {
    Discussion createDiscussion(Discussion discussion, Integer buyerId, Integer sellerId, Integer itemId, Integer collectionId) throws DataException;
}
