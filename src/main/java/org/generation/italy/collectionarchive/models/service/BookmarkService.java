package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.Bookmark;
import org.generation.italy.collectionarchive.models.exceptions.DataException;

import java.util.List;
import java.util.Optional;

public interface BookmarkService {
    List<Bookmark> findAllBookmarks() throws DataException;
    Optional<Bookmark> findBookmarkById(int bookmarkId) throws DataException;
    Bookmark createBookmark(Bookmark b, int userId, int itemId,  int collectionId) throws DataException;
    boolean deleteBookmark(int bookmarkId);
    boolean updateBookmark(Bookmark b, int userId, int itemId, int collectionId) throws DataException;

}
