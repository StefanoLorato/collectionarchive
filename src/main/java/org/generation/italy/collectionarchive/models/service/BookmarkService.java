package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.Bookmark;
import org.generation.italy.collectionarchive.models.exceptions.DataException;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface BookmarkService {
    List<Bookmark> findAllBookmarks() throws DataException;
    Optional<Bookmark> findBookmarkById(int bookmarkId) throws DataException;
    Bookmark createBookmark(Bookmark b, int userId, Integer itemId,  Integer collectionId) throws DataException;
    boolean deleteBookmark(int bookmarkId) throws DataException;
    boolean updateBookmark(Bookmark b, int userId, Integer itemId, Integer collectionId) throws DataException;
    List<Bookmark> findBookmarksByUserId(int id) throws DataException;
}
