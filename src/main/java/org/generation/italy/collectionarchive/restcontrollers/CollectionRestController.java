package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.entities.Bookmark;
import org.generation.italy.collectionarchive.models.entities.Collection;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.service.CollectionService;
import org.generation.italy.collectionarchive.restdto.CollectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/collections")
public class CollectionRestController {
    private CollectionService collectionService;

    @Autowired
    public CollectionRestController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCollectionById(@AuthenticationPrincipal User user, @PathVariable int id) throws DataException {

        Optional<Collection> c = collectionService.findCollectionById(id);
        if(c.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        CollectionDto co = CollectionDto.toDto(c.get(), user);
        return ResponseEntity.ok(co);
    }

    @GetMapping("/loggedUser")
    public ResponseEntity<?> getUserCollections(@AuthenticationPrincipal User user) throws DataException {
        List<CollectionDto> collections = collectionService.findAllByUserEmail(user.getEmail())
                .stream().map( c -> CollectionDto.toDto(c, user)).toList();
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(collections);
    }

    @GetMapping
    public ResponseEntity<?> getAllCollection(@RequestParam(required = false) String collectionName,
                                              @RequestParam(required = false) Integer categoryId,
                                              @RequestParam(required = false) Integer userId,
                                              @RequestParam(required = false) Double salePrice,
                                              @RequestParam(required = false) String priceComparation,
                                              @RequestParam(required = false) Boolean bookmarked,
                                              @AuthenticationPrincipal User user) throws DataException {
        //TODO da integrare nei filtri
        if(bookmarked != null && bookmarked){
            List<Collection> collections = collectionService.findCollectionsByBookmarkUserId(user.getUserId());
            return ResponseEntity.ok(collections.stream().map(c -> CollectionDto.toDto(c, user)).toList());
        }

        CollectionDto filters = new CollectionDto();
        filters.setCollectionName(collectionName);
        filters.setCategory(categoryId);
        filters.setUserId(userId);
        filters.setSalePrice(salePrice);
        filters.setPriceComparation(priceComparation);
        List<Collection> collections = collectionService.searchCollection(filters);
        List<CollectionDto> collectionDtos = collections.stream().map( c -> CollectionDto.toDto(c, user)).toList();
        return ResponseEntity.ok(collectionDtos);
    }

    @PostMapping
    public ResponseEntity<CollectionDto> createCollection(@AuthenticationPrincipal User user, @RequestBody CollectionDto dto) throws DataException, EntityNotFoundException {
        Collection c = dto.toCollection();
        c.setCreatedAt(LocalDateTime.now());
        collectionService.createCollection(c, dto.getUserId(), dto.getCategoryId());

        CollectionDto saved = CollectionDto.toDto(c, user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getCollectionId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollectionById(@PathVariable int id) throws DataException{
        boolean deleted = collectionService.deleteCollection(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCollection(@PathVariable int id, @RequestBody CollectionDto dto) throws DataException, EntityNotFoundException{
        if(id != dto.getCollectionId()){
            return ResponseEntity.badRequest().body("L'id del path non corrisponde all'id del dto");
        }

        Optional<Collection> co = collectionService.findCollectionById(id);
        if(co.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Collection c = dto.toCollection();
        c.setCreatedAt(co.get().getCreatedAt());
        c.setCollectionId(id);

        boolean updated = collectionService.updateCollection(c, dto.getUserId(), dto.getCategoryId());
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/visibility")
    public ResponseEntity<?> toggleVisibility(@PathVariable Integer id) throws DataException {
        Optional<Collection> ot = collectionService.findCollectionById(id);
        if(ot.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Collection t = ot.get();
        t.setVisibility(t.getVisibility().equals("visible") ? "hidden" : "visible");
        boolean updated = collectionService.updateCollection(t, t.getUser().getUserId(), t.getCategory().getCategoryId());
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }}
}

