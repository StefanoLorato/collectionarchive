package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.entities.Bookmark;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.service.BookmarkService;
import org.generation.italy.collectionarchive.restdto.BookmarkDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/bookmarks")
public class BookmarkRestController {
    private BookmarkService bookmarkService;

    @Autowired
    public BookmarkRestController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookmarkById(@PathVariable int id) throws DataException {
        Optional<Bookmark> b = bookmarkService.findBookmarkById(id);
        if(b.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        BookmarkDto bm = BookmarkDto.toDto(b.get());
        return ResponseEntity.ok(bm);
    }

    @GetMapping
    public ResponseEntity<?> getAllBookmark() throws DataException {
        List<BookmarkDto> bookmarkDtos= bookmarkService.findAllBookmarks()
                .stream().map(BookmarkDto::toDto).toList();
        return ResponseEntity.ok(bookmarkDtos);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getBookmarksByUserId(@PathVariable int id) throws DataException{
        List<BookmarkDto> bookmarkDtos = bookmarkService.findBookmarksByUserId(id)
                .stream().map(BookmarkDto::toDto).toList();
        return ResponseEntity.ok(bookmarkDtos);
    }

    @PostMapping
    public ResponseEntity<BookmarkDto> createBookmark(@RequestBody BookmarkDto dto) throws DataException, EntityNotFoundException {
        Bookmark bm = dto.ToBookMark();
        bm.setSavedAt(LocalDateTime.now());
        bookmarkService.createBookmark(bm, dto.getUserId(), dto.getItemId(), dto.getCollectionId());
        BookmarkDto saved = BookmarkDto.toDto(bm);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getBookmarkId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookmarkById(@PathVariable int id) throws DataException{
        boolean deleted = bookmarkService.deleteBookmark(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBookmark(@PathVariable int id, @RequestBody BookmarkDto dto) throws DataException{
        if(id != dto.getBookmarkId()){
            return ResponseEntity.badRequest().body("l'id del path non corrisponde all'id del dto");
        }

        Optional<Bookmark> obm = bookmarkService.findBookmarkById(id);
        if(obm.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Bookmark bm = dto.ToBookMark();
        boolean updated = bookmarkService.updateBookmark(bm, dto.getUserId(), dto.getItemId(), dto.getCollectionId());
        if(updated){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}