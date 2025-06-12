package org.generation.italy.collectionarchive.models.repositories;

import org.generation.italy.collectionarchive.models.entities.Collection;
import org.generation.italy.collectionarchive.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CollectionRepository extends JpaRepository<Collection , Integer>, JpaSpecificationExecutor<Collection> {
    List<Collection> findByUser(User user);
    Optional<Collection> findByCollectionIdAndUser(Long id, User user);
    List<Collection> findByUserEmail(String email);

}
