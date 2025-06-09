package org.generation.italy.collectionarchive.models.repositories;

import org.generation.italy.collectionarchive.models.entities.Collection;
import org.generation.italy.collectionarchive.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CollectionRepository extends JpaRepository<Collection , Integer> {
    List<Collection> findByOwner(User owner);
    Optional<Collection> findByIdAndOwner(Long id, User owner);
}
