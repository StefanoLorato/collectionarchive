package org.generation.italy.collectionarchive.restcontrollers;


import org.generation.italy.collectionarchive.models.entities.Order;
import org.generation.italy.collectionarchive.models.entities.OrderItem;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.service.OrderService;
import org.generation.italy.collectionarchive.restdto.OrderDto;
import org.generation.italy.collectionarchive.restdto.OrderItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/orders")
public class OrderRestController {
    private OrderService orderService;

    @Autowired
    public OrderRestController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() throws DataException{
        List<OrderDto> orders = orderService.findAllOrders().stream().map(OrderDto::toDto).toList();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Integer id){
        Optional<Order> oo = orderService.findOrderById(id);
        if(oo.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        OrderDto orderDto = OrderDto.toDto(oo.get());
        return ResponseEntity.ok(orderDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto dto) throws DataException, EntityNotFoundException{
        Order o = dto.toOrder();
        o.setOrderedAt(LocalDateTime.now());

        orderService.createOrder(o, dto.getBuyerId(), dto.getShippingAddressId());
        dto.getOrderItems().forEach(oiDto -> {
            OrderItem oi = oiDto.toOrderItem();
            orderService.createOrderItem(oi, o.getOrderId(), oiDto.getSellerId(), oiDto.getItemId(), oiDto.getCollectionId());
            o.addOrderItem(oi);
        });
        Order saved = orderService.findOrderById(o.getOrderId()).orElseThrow(() -> new EntityNotFoundException("errore nel caricamento dell'ordine con id" + o.getOrderId()));
        OrderDto savedDto = OrderDto.toDto(o);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedDto.getOrderId())
                .toUri();
        return ResponseEntity.created(location).body(savedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable int id) throws DataException{
        boolean deleted = orderService.deleteOrder(id);
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
        Optional<Order> oo = orderService.findOrderById(id);
        if(oo.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Order o = dto.toOrder();
        boolean updated = orderService.updateOrder(o, dto.getBuyerId(), dto.getShippingAddressId());
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/orderItems")
    public ResponseEntity<OrderItemDto> createOrderItem(@RequestBody OrderItemDto dto, @PathVariable int id) throws DataException, EntityNotFoundException {
        OrderItem o = dto.toOrderItem();
        orderService.createOrderItem(o, id, dto.getSellerId(), dto.getItemId(), dto.getCollectionId());
        OrderItemDto saved = OrderItemDto.toDto(o);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getOrderItemId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}/orderItems/{orderItemId}")
    public ResponseEntity<?> updateOrderItem(@PathVariable int id, @RequestBody OrderItemDto dto, @PathVariable int orderItemId) throws DataException, EntityNotFoundException{
        if(orderItemId != dto.getOrderItemId()){
            return ResponseEntity.badRequest().body("l'id del path non corrisponde all'id del dto");
        }
        Optional<OrderItem> oo = orderService.findOrderItemById(id);
        if(oo.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        OrderItem o = dto.toOrderItem();
        boolean updated = orderService.updateOrderItem(o,orderItemId, dto.getSellerId(), dto.getItemId(), dto.getCollectionId());
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}