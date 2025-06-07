package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.entities.CartItem;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.service.OrderService;
import org.generation.italy.collectionarchive.restdto.CartItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cartItems")
public class CartItemsRestController {
    private OrderService orderService;

    @Autowired
    public CartItemsRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCartItems() throws DataException {
        List<CartItemDto> cartItems = orderService.findAllCartItems().stream().map(CartItemDto::toDto).toList();
        return ResponseEntity.ok(cartItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderItemById(@PathVariable Integer id){
        Optional<CartItem> oci = orderService.findCartItemById(id);
        if(oci.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        CartItemDto cartItemDto = CartItemDto.toDto(oci.get());
        return ResponseEntity.ok(cartItemDto);
    }

    @PostMapping
    public ResponseEntity<CartItemDto> createCartItem(@RequestBody CartItemDto dto) throws DataException, EntityNotFoundException {
        CartItem ci = dto.toCartItem();
        orderService.createCartItem(ci, dto.getBuyerId(), dto.getSellerId(), dto.getItemId(), dto.getCollectionId());
        CartItemDto saved = CartItemDto.toDto(ci);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getCartItemId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItemById(@PathVariable int id) throws DataException{
        boolean deleted = orderService.deleteCartItem(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCartItem(@PathVariable int id, @RequestBody CartItemDto dto) throws DataException, EntityNotFoundException{
        if(id != dto.getCartItemId()){
            return ResponseEntity.badRequest().body("l'id del path non corrisponde all'id del dto");
        }
        Optional<CartItem> oci = orderService.findCartItemById(id);
        if(oci.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        CartItem ci = dto.toCartItem();
        boolean updated = orderService.updateCartItem(ci, dto.getBuyerId(), dto.getSellerId(), dto.getItemId(), dto.getCollectionId());
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
