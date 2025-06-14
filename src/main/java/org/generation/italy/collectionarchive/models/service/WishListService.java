package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.WishList;
import org.generation.italy.collectionarchive.models.exceptions.DataException;

import java.util.List;
import java.util.Optional;

public interface WishListService {
    List<WishList> findAllWIshList() throws DataException;

    Optional<WishList> findWIshListById(int desiredItemId) throws DataException;

    WishList createWishList(WishList w, int collectionId) throws DataException;

    boolean deleteWishList(int desiredItemId) throws DataException;

    boolean updateWishList(WishList w, int collectionId) throws DataException;
    List<WishList> findWishListByCollectionId(Integer id) throws DataException;
}
