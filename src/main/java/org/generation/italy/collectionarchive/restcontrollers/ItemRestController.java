package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.entities.Collection;
import org.generation.italy.collectionarchive.models.entities.Item;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.service.CollectionService;
import org.generation.italy.collectionarchive.restdto.CollectionDto;
import org.generation.italy.collectionarchive.restdto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/item")

public class ItemRestController {
    private CollectionService collectionService;

    @Autowired
    public  ItemRestController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable int id) throws DataException {

        Optional<Item> c = collectionService.findItemById(id);
        if(c.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        ItemDto io = ItemDto.toDto(c.get());
        return ResponseEntity.ok(c);
    }

    @GetMapping
    public ResponseEntity<?> getAllItem() throws DataException {

        List<ItemDto> itemDtos = collectionService.findAllItem()
                .stream().map(ItemDto::toDto).toList();
        return ResponseEntity.ok(itemDtos);
    }

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto dto) throws DataException, EntityNotFoundException {
        Item c = dto.toItem();
        collectionService.createItem(c, dto.getUser(),dto.getCollection());
        ItemDto saved = ItemDto.toDto(c);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getItemId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemById(@PathVariable int id) throws DataException{
        boolean deleted = collectionService.deleteItem(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable int id, @RequestBody ItemDto dto) throws DataException, EntityNotFoundException{
        if(id != dto.getItemId()){
            return ResponseEntity.badRequest().body("L'id del path non corrisponde all'id del dto");
        }
        Optional<Item> co = collectionService.findItemById(id);
        if(co.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Item i = dto.toItem();
        i.setItemId(id);

        boolean updated = collectionService.updateItem(i, dto.getUser(),dto.getCollection());
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
