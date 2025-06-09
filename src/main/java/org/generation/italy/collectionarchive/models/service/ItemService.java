package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.Item;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    //ITEM
    List<Item> findAllItem() throws DataException;
    Optional<Item> findItemById(int id) throws DataException;
    Item createItem(Item i,int userId, int collectionId) throws DataException, EntityNotFoundException;
    boolean deleteItem(int id) throws DataException;
    boolean updateItem(Item t, int userId, int collectionId) throws DataException, EntityNotFoundException;
    List<Item> findItemByCollectionId(int collectionId);
}
