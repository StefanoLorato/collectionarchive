package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.*;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CollectionService {

    //COLLECTION
    List<Collection> findAllCollection() throws DataException;
    Optional<Collection> findCollectionById(int id) throws DataException;
    Collection createCollection(Collection c, int userId, int categoryId) throws DataException, EntityNotFoundException;
    boolean deleteCollection(int id) throws DataException;
    boolean updateCollection(Collection c, int userId, int categoryId) throws DataException, EntityNotFoundException;
    List<Collection> findAllByUserEmail(String email) throws DataException;
}
