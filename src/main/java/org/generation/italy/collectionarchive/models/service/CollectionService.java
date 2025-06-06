package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.Collection;
import org.generation.italy.collectionarchive.models.entities.WishList;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CollectionService {
    List<Collection> findAllCollection() throws DataException;
    Optional<Collection> findCollectionById(int id) throws DataException;
    Collection createCollection(Collection c, int userId, int categoryId) throws DataException, EntityNotFoundException;
    boolean deleteCollection(int id) throws DataException;
    boolean updateCollection(Collection c, int userId, int categoryId) throws DataException, EntityNotFoundException;
    List<WishList> findAllWishList();
    Optional<WishList> findWishListById(int id);
    WishList createWishList(WishList w, int collectionId);
    boolean deleteWishList(int id);
    boolean updateItem( WishList w, int collectionId);


}
