package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.Collection;
import org.generation.italy.collectionarchive.models.entities.Discussion;
import org.generation.italy.collectionarchive.models.entities.Item;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.repositories.CollectionRepository;
import org.generation.italy.collectionarchive.models.repositories.DiscussionRepository;
import org.generation.italy.collectionarchive.models.repositories.ItemRepository;
import org.generation.italy.collectionarchive.models.repositories.UserRepository;
import org.generation.italy.collectionarchive.restdto.DiscussionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpaDiscussionService implements DiscussionService {

    private DiscussionRepository discussionRepository;
    private UserRepository userRepository;
    private CollectionRepository collectionRepository;
    private ItemRepository itemRepository;

    @Autowired
    public JpaDiscussionService(DiscussionRepository discussionRepository, UserRepository userRepository,
                                CollectionRepository collectionRepository, ItemRepository itemRepository) {
        this.discussionRepository = discussionRepository;
        this.userRepository = userRepository;
        this.collectionRepository = collectionRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Discussion createDiscussion(Discussion discussion, Integer buyerId, Integer sellerId, Integer itemId, Integer collectionId) throws DataException {
        User buyer = userRepository.findById(buyerId)
                .orElseThrow(() -> new IllegalArgumentException("Compratore non trovato!"));

        User seller = userRepository.findById(sellerId)
                .orElseThrow(() -> new IllegalArgumentException("Venditore non trovato!"));

        discussion.setBuyer(buyer);
        discussion.setSeller(seller);

        if (itemId != null) {
            Item item = itemRepository.findById(itemId)
                    .orElseThrow(() -> new IllegalArgumentException("Oggetto non trovato!"));
            discussion.setItem(item);
            discussion.setCollection(null);
        } else if (collectionId != null) {
            Collection collection = collectionRepository.findById(collectionId)
                    .orElseThrow(() -> new IllegalArgumentException("Collezione non trovata!"));
            discussion.setCollection(collection);
            discussion.setItem(null);
        } else {
            throw new IllegalArgumentException("Devi passare o un oggetto o una collezione!");
        }

        Discussion saved = discussionRepository.save(discussion);

        return saved;
    }
}