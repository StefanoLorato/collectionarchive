package org.generation.italy.collectionarchive.models.service;

import jakarta.persistence.PersistenceException;
import org.generation.italy.collectionarchive.models.entities.Bookmark;
import org.generation.italy.collectionarchive.models.entities.Collection;
import org.generation.italy.collectionarchive.models.entities.Item;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.repositories.BookmarkRepository;
import org.generation.italy.collectionarchive.models.repositories.CollectionRepository;
import org.generation.italy.collectionarchive.models.repositories.ItemRepository;
import org.generation.italy.collectionarchive.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class JpaBookmarkService implements BookmarkService{
    private BookmarkRepository bookmarkRepo;
    private UserRepository userRepo;
    private ItemRepository itemRepo;
    private CollectionRepository collectionRepo;

    @Autowired
    public JpaBookmarkService(BookmarkRepository bookmarkRepo, UserRepository userRepo, ItemRepository itemRepo, CollectionRepository collectionRepo) {
        this.bookmarkRepo = bookmarkRepo;
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
        this.collectionRepo = collectionRepo;
    }

    @Override
    public List<Bookmark> findAllBookmarks() throws DataException {
        return bookmarkRepo.findAll();
    }

    @Override
    public Optional<Bookmark> findBookmarkById(int bookmarkId) throws DataException {
        return bookmarkRepo.findById(bookmarkId);
    }

    @Override
    public Bookmark createBookmark(Bookmark b, int userId, int itemId, int collectionId) throws DataException {
        try{
            Optional<User> ou = userRepo.findById(userId);
            User u = ou.orElseThrow(() -> new EntityNotFoundException(User.class, userId));
            Optional<Item> oi= itemRepo.findById(itemId);
            Item i = oi.orElseThrow(() -> new EntityNotFoundException(Item.class, itemId));
            Optional<Collection> oc = collectionRepo.findById(collectionId);
            Collection c = oc.orElseThrow(() -> new EntityNotFoundException(Collection.class, collectionId));

            b.setUser(u);
            b.setItem(i);
            b.setCollection(c);
            bookmarkRepo.save(b);
            return b;
        }catch (PersistenceException pe){
            throw new DataException("errore nella creazione di un bookmark", pe);
        }
    }

    @Override
    public boolean deleteBookmark(int bookmarkId) {
        return false;
    }

    @Override
    public boolean updateBookmark(Bookmark b, int userId, int itemId, int collectionId) throws DataException {
        return false;
    }
}
