package org.generation.italy.collectionarchive.models.repositories;

import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.entities.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository  extends JpaRepository<WishList, Integer> {
}
