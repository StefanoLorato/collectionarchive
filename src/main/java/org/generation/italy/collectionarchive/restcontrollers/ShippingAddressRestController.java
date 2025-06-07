package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.entities.ShippingAddress;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.service.UserService;
import org.generation.italy.collectionarchive.restdto.ShippingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/shipping-address")
public class ShippingAddressRestController {

    private final UserService userContactService;

    @Autowired
    public ShippingAddressRestController(UserService userContactService) {
        this.userContactService = userContactService;
    }

    @GetMapping
    public ResponseEntity<?> getAllShippingAddresses() throws DataException {
        List<ShippingDto> dtos = userContactService.findAllShippingAddresses()
                .stream()
                .map(ShippingDto::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getShippingAddressById(@PathVariable int id) throws DataException {
        Optional<ShippingAddress> opt = userContactService.findShippingAddressById(id);
        return opt.map(sa -> ResponseEntity.ok(ShippingDto.toDto(sa)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ShippingDto> createShippingAddress(@RequestBody ShippingDto dto) throws DataException, EntityNotFoundException {
        ShippingAddress sa = dto.toShip();
        ShippingAddress created = userContactService.createShippingAddress(sa, dto.getUserId());

        ShippingDto savedDto = ShippingDto.toDto(created);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedDto.getShippingId())
                .toUri();

        return ResponseEntity.created(location).body(savedDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateShippingAddress(@PathVariable int id, @RequestBody ShippingDto dto) throws DataException, EntityNotFoundException {
        if (id != dto.getShippingId()) {
            return ResponseEntity.badRequest().body("ID path diverso da ID DTO");
        }

        Optional<ShippingAddress> opt = userContactService.findShippingAddressById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ShippingAddress sa = dto.toShip();
        sa.setShippingId(id);

        boolean updated = userContactService.updateShippingAddress(sa, dto.getUserId());
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShippingAddress(@PathVariable int id) throws DataException {
        boolean deleted = userContactService.deleteShippingAddress(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}