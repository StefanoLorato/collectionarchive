package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.entities.WishList;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.service.WishListService;
import org.generation.italy.collectionarchive.restdto.WishListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/wishlist")
public class WishListRestController {
    private WishListService wishListService;

    @Autowired
    public WishListRestController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWishListById(@PathVariable int id) throws DataException {
        Optional<WishList> wl = wishListService.findWIshListById(id);
        if(wl.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        WishListDto wls = WishListDto.toDto(wl.get());
        return ResponseEntity.ok(wls);
    }
    @GetMapping
    public ResponseEntity<?> getAllWishList(@RequestParam(required = false) Integer id) throws DataException {
        List<WishListDto> wishListDtos= wishListService.findAllWIshList()
                    .stream().map(WishListDto::toDto).toList();
        return ResponseEntity.ok(wishListDtos);

    }
    @GetMapping("/collection/{id}")
    public ResponseEntity<?> getWishListByCollectionId(@PathVariable Integer id) throws DataException {
        List<WishListDto> wishListDtos = wishListService.findWishListByCollectionId(id)
                    .stream().map(WishListDto::toDto).toList();
        return ResponseEntity.ok(wishListDtos);
    }

    @PostMapping
    public ResponseEntity<WishListDto> createWishList(@RequestBody WishListDto dto) throws DataException, EntityNotFoundException {
        WishList wl = dto.ToWishList();
        wishListService.createWishList(wl, dto.getCollectionId());

        WishListDto saved = WishListDto.toDto(wl);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getDesiredObjectId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishListById(@PathVariable int id) throws DataException {
        boolean deleted = wishListService.deleteWishList(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateWishList(@PathVariable int id, @RequestBody WishListDto dto)throws DataException,  EntityNotFoundException{
        if(id != dto.getDesiredObjectId()){
            return ResponseEntity.badRequest().body("L'id del path non corrisponde all'id del dto");
        }
        Optional<WishList> wl = wishListService.findWIshListById(id);
        if (wl.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        WishList wls = dto.ToWishList();
        wls.setDesiredItemId(id);
        boolean updated = wishListService.updateWishList(wls, dto.getDesiredObjectId());
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
