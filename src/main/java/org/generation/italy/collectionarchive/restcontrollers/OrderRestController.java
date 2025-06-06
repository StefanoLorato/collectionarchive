package org.generation.italy.collectionarchive.restcontrollers;


import org.generation.italy.collectionarchive.models.entities.Order;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.service.CollectionService;
import org.generation.italy.collectionarchive.restdto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/orders")
public class OrderRestController {
    private CollectionService collectionService;

    @Autowired
    public OrderRestController(CollectionService collectionService){
        this.collectionService = collectionService;
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() throws DataException{
        List<OrderDto> orders = collectionService.findAllOrders().stream().map(OrderDto::toDto).toList();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Integer id){
        Optional<Order> oo = collectionService.findOrderById(id);
        if(oo.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        OrderDto orderDto = OrderDto.toDto(oo.get());
        return ResponseEntity.ok(orderDto);
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto dto) throws DataException, EntityNotFoundException{
        Order o = dto.toOrder();
        collectionService.createOrder(o, dto.getBuyerId(), dto.getSellerId());
        OrderDto saved = OrderDto.toDto(o);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getOrderId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable int id) throws DataException{
        boolean deleted = collectionService.deleteOrder(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable int id, @RequestBody OrderDto dto) throws DataException, EntityNotFoundException{
        if(id != dto.getOrderId()){
            return ResponseEntity.badRequest().body("l'id del path non corrisponde all'id del dto");
        }
        Optional<Order> oo = collectionService.findOrderById(id);
        if(oo.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Order o = dto.toOrder();
        boolean updated = collectionService.updateOrder(o, dto.getBuyerId(), dto.getSellerId());
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
