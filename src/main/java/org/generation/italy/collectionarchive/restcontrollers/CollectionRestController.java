package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.entities.Collection;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.service.CollectionService;
import org.generation.italy.collectionarchive.restdto.CollectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/collection")

public class CollectionRestController {
    private CollectionService collectionService;

    @Autowired
    public CollectionRestController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCollectionById(@PathVariable int id) throws DataException {

        Optional<Collection> c = collectionService.findCollectionById(id);
        if(c.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        CollectionDto co = CollectionDto.toDto(c.get());
        return ResponseEntity.ok(c);
    }

    @GetMapping
    public ResponseEntity<?> getAllCollection() throws DataException {

        List<CollectionDto> collectionDtos = collectionService.findAllCollection()
                .stream().map(CollectionDto::toDto).toList();
        return ResponseEntity.ok(collectionDtos);
    }

    @PostMapping
    public ResponseEntity<CollectionDto> createCollection(@RequestBody CollectionDto dto) throws DataException, EntityNotFoundException {
        Collection c = dto.toCollection();
        collectionService.createCollection(c, dto.getUserId(), dto.getCategoryId());

        CollectionDto saved = CollectionDto.toDto(c);
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
        c.setCollectionId(id);

        boolean updated = collectionService.updateCollection(c, dto.getUserId(), dto.getCategoryId());
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
