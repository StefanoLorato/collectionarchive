package org.generation.italy.collectionarchive.restcontrollers;


import org.generation.italy.collectionarchive.models.entities.*;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.service.CollectionService;
import org.generation.italy.collectionarchive.models.service.ItemService;
import org.generation.italy.collectionarchive.models.service.OrderService;
import org.generation.italy.collectionarchive.models.service.UserService;
import org.generation.italy.collectionarchive.restdto.OrderDto;
import org.generation.italy.collectionarchive.restdto.OrderItemDto;
import org.generation.italy.collectionarchive.restdto.OrderItemStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private UserService userService;
    private CollectionService collectionService;
    private ItemService itemService;

    @Autowired
    public OrderRestController(OrderService orderService, UserService userService,
                               CollectionService collectionService, ItemService itemService){
        this.orderService = orderService;
        this.userService = userService;
        this.collectionService = collectionService;
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders(@RequestParam(required = false) Integer sellerId) throws DataException{
        List<Order> orders;
        if(sellerId != null){
            orders = orderService.findOrderByItemsSeller(sellerId);
        } else {
            orders = orderService.findAllOrders();
        }
        List<OrderDto> dtos = orders.stream().map(OrderDto::toDto).toList();
        return ResponseEntity.ok(dtos);
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

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getOrderByUserId(@PathVariable Integer id){
        Optional<User> buyer = userService.findUserById(id);
        if(buyer.isEmpty()){
            return ResponseEntity.badRequest().body("utente non trovato");
        }
        List<OrderDto> orders = orderService.findOrderByBuyerId(id).stream().map(OrderDto::toDto).toList();
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto dto) throws DataException, EntityNotFoundException{
        Order o = dto.toOrder();
        o.setOrderedAt(LocalDateTime.now());

        orderService.createOrder(o, dto.getBuyerId(), dto.getShippingAddressId());
        dto.getOrderItems().forEach(oiDto -> {
            OrderItem oi = oiDto.toOrderItem();
            oi.setStatus("pending");
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
        Optional<OrderItem> oo = orderService.findOrderItemById(orderItemId);
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

    @PatchMapping("/{id}/orderItems/{orderItemId}")
    @Transactional
    public ResponseEntity<OrderItemDto> updateOrderItemStatus(@AuthenticationPrincipal User user, @PathVariable int id, @RequestBody OrderItemStatusDto dto, @PathVariable int orderItemId) throws EntityNotFoundException{
        Order o = orderService.findOrderById(id).orElseThrow(()-> new EntityNotFoundException("ordine non trovato con id: " + id));

        OrderItem oi = orderService.findOrderItemById(orderItemId).orElseThrow(() -> new EntityNotFoundException("linea d'ordine non trovata con id: " + orderItemId));

        User buyer = userService.findUserById(o.getBuyer().getUserId()).orElseThrow(() -> new EntityNotFoundException(("Buyer non trovato con id: " + o.getBuyer().getUserId())));
        oi.setStatus(dto.getStatus());
        if(dto.getStatus().equals("accepted")){
            System.out.println("cambiando proprietario all'oggetto");
            if(oi.getCollection() != null){
                oi.getCollection().setUser(buyer);
            } else {
                oi.getItem().setUser(buyer);
            }
        }
        OrderItemDto updated = OrderItemDto.toDto(oi);
        return ResponseEntity.ok(updated);
    }
}