package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.Collection;
import org.generation.italy.collectionarchive.models.entities.Discussion;
import org.generation.italy.collectionarchive.models.entities.Item;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.repositories.CollectionRepository;
import org.generation.italy.collectionarchive.models.repositories.DiscussionRepository;
import org.generation.italy.collectionarchive.models.repositories.ItemRepository;
import org.generation.italy.collectionarchive.models.repositories.UserRepository;
import org.generation.italy.collectionarchive.restdto.DiscussionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscussionService {

    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private ItemRepository itemRepository;

    public DiscussionDto createDiscussion(DiscussionDto dto) {
        User buyer = userRepository.findById(dto.getBuyerId())
                .orElseThrow(() -> new IllegalArgumentException("Buyer not found"));

        User seller = userRepository.findById(dto.getSellerId())
                .orElseThrow(() -> new IllegalArgumentException("Seller not found"));

        Discussion discussion = new Discussion();
        discussion.setBuyer(buyer);
        discussion.setSeller(seller);

        if (dto.getItemId() != null) {
            Item item = itemRepository.findById(dto.getItemId())
                    .orElseThrow(() -> new IllegalArgumentException("Item not found"));
            discussion.setItem(item);
            discussion.setCollection(null);
        } else if (dto.getCollectionId() != null) {
            Collection collection = collectionRepository.findById(dto.getCollectionId())
                    .orElseThrow(() -> new IllegalArgumentException("Collection not found"));
            discussion.setCollection(collection);
            discussion.setItem(null);
        } else {
            throw new IllegalArgumentException("Devi passare o itemId o collectionId, non entrambi null");
        }

        Discussion saved = discussionRepository.save(discussion);
        dto.setDiscussionId(saved.getDiscussionId());

        return dto;
    }
}
