package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.entities.OrderItem;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.service.CollectionService;
import org.generation.italy.collectionarchive.models.service.OrderService;
import org.generation.italy.collectionarchive.restdto.OrderItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/order-items")
public class OrderItemRestController {
    private OrderService orderItemService;

    @Autowired
    public OrderItemRestController(OrderService orderItemService){
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public ResponseEntity<?> getAllOrderItems() throws DataException {
        List<OrderItemDto> orderItems = orderItemService.findAllOrderItems().stream().map(OrderItemDto::toDto).toList();
        return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderItemById(@PathVariable Integer id){
        Optional<OrderItem> ooi = orderItemService.findOrderItemById(id);
        if(ooi.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        OrderItemDto orderItemDto = OrderItemDto.toDto(ooi.get());
        return ResponseEntity.ok(orderItemDto);
    }

    @PostMapping
    public ResponseEntity<OrderItemDto> createOrderItem(@RequestBody OrderItemDto dto) throws DataException, EntityNotFoundException {
        OrderItem o = dto.toOrderItem();
        orderItemService.createOrderItem(o, dto.getOrderId(), dto.getItemId(), dto.getCollectionId());
        OrderItemDto saved = OrderItemDto.toDto(o);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getOrderId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItemById(@PathVariable int id) throws DataException{
        boolean deleted = orderItemService.deleteOrderItem(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderItem(@PathVariable int id, @RequestBody OrderItemDto dto) throws DataException, EntityNotFoundException{
        if(id != dto.getOrderItemId()){
            return ResponseEntity.badRequest().body("l'id del path non corrisponde all'id del dto");
        }
        Optional<OrderItem> oo = orderItemService.findOrderItemById(id);
        if(oo.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        OrderItem o = dto.toOrderItem();
        boolean updated = orderItemService.updateOrderItem(o, dto.getOrderId(), dto.getItemId(), dto.getCollectionId());
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}