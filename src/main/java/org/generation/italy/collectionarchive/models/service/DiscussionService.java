package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.Discussion;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.restdto.DiscussionDto;

import java.util.List;
import java.util.Optional;

public interface DiscussionService {
    Discussion createDiscussion(Discussion discussion, Integer buyerId, Integer sellerId, Integer itemId, Integer collectionId) throws DataException;
    Optional<Discussion> findDiscussionById(int id) throws DataException;
    List<Discussion> getAllDiscussions() throws DataException;
    List<Discussion> getDiscussionByCollectionIdAndUserId(int collectionId, int userId) throws DataException;
    List<Discussion> getDiscussionsByUserId(int id) throws DataException;
    List<List<Discussion>> getDiscussionByBuyerAndSeller(int userId) throws DataException;
}
