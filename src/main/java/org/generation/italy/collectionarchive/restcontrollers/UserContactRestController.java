package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.entities.UserContact;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.service.UserProfileService;
import org.generation.italy.collectionarchive.restdto.UserContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/userContacts")
public class UserContactRestController {

    private final UserProfileService contactService;

    @Autowired
    public UserContactRestController(UserProfileService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<UserContactDto>> getAllUserContacts() throws DataException {
        List<UserContactDto> dtos = contactService.findAllUserContacts()
                .stream()
                .map(UserContactDto::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserContactDto> getUserContactById(@PathVariable int id) throws DataException {
        Optional<UserContact> oc = contactService.findUserContactById(id);
        return oc.map(contact -> ResponseEntity.ok(UserContactDto.toDto(contact)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserContactDto> createUserContact(@RequestBody UserContactDto dto) throws DataException, EntityNotFoundException {
        UserContact contact = dto.toUserContact();
        contactService.createUserContact(contact, dto.getUser());
        UserContactDto saved = UserContactDto.toDto(contact);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getContactId())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserContact(@PathVariable int id, @RequestBody UserContactDto dto) throws DataException, EntityNotFoundException {
        if (id != dto.getContactId()) {
            return ResponseEntity.badRequest().body("ID nel path e nel body non coincidono.");
        }

        Optional<UserContact> existing = contactService.findUserContactById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserContact contact = dto.toUserContact();
        contact.setContactId(id);
        boolean updated = contactService.updateUserContact(contact, dto.getUser());

        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserContact(@PathVariable int id) throws DataException {
        boolean deleted = contactService.deleteUserContact(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}