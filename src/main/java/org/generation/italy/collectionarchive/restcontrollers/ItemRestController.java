package org.generation.italy.collectionarchive.restcontrollers;


import org.generation.italy.collectionarchive.models.entities.Item;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.service.ItemService;
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
@RequestMapping("/api/items")

public class ItemRestController {
    private ItemService itemService;

    @Autowired
    public  ItemRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable int id) throws DataException {

        Optional<Item> c = itemService.findItemById(id);
        if(c.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        ItemDto io = ItemDto.toDto(c.get());
        return ResponseEntity.ok(c);
    }

    @GetMapping
    public ResponseEntity<?> getAllItem(@RequestBody(required = false) String itemName,
                                        @RequestBody(required = false) Boolean forSale,
                                        @RequestBody(required = false) Integer userId) throws DataException {
        ItemDto filters = new ItemDto();
        filters.setItemName(itemName);
        filters.setForSale(forSale);
        filters.setUserId(userId);
        List<ItemDto> itemDtos = itemService.findAllItem()
                .stream().map(ItemDto::toDto).toList();
        return ResponseEntity.ok(itemDtos);
    }

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto dto) throws DataException, EntityNotFoundException {
        Item c = dto.toItem();
        itemService.createItem(c, dto.getUserId(),dto.getCollection());
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
        boolean deleted = itemService.deleteItem(id);
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
        Optional<Item> co = itemService.findItemById(id);
        if(co.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Item i = dto.toItem();
        i.setItemId(id);

        boolean updated = itemService.updateItem(i, dto.getUserId(),dto.getCollection());
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
