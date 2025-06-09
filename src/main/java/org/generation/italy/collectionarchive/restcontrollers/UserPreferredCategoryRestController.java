package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.entities.UserPreferredCategory;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.service.CategoryService;
import org.generation.italy.collectionarchive.restdto.UserPreferredCategoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.xml.crypto.Data;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/userPreferredCategories")
public class UserPreferredCategoryRestController {
    private CategoryService categoryService;

    public UserPreferredCategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUserPreferredCategory() throws DataException {
        List<UserPreferredCategoryDto> dtos = categoryService.findAllUserPreferredCategories()
                .stream()
                .map(UserPreferredCategoryDto::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserPreferredCategoryById(@PathVariable int id) throws DataException{
        Optional<UserPreferredCategory> oupc = categoryService.findUserPreferredCategoryById(id);
        return oupc.map(upc -> ResponseEntity.ok(UserPreferredCategoryDto.toDto(upc)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
    @PostMapping
    public ResponseEntity<UserPreferredCategoryDto> createUserPreferredCategory(@RequestBody UserPreferredCategoryDto dto)throws DataException, EntityNotFoundException {
        UserPreferredCategory upc = dto.toUserPreferredCategory();
        UserPreferredCategory created = categoryService.createUserPreferredCategory(upc, dto.getCategoryId(), dto.getUserId());
        UserPreferredCategoryDto saveDto = UserPreferredCategoryDto.toDto(created);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveDto.getPreferenceId())
                .toUri();
        return ResponseEntity.created(location).body(saveDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserPreferredCategory(@PathVariable int id, @RequestBody UserPreferredCategoryDto dto) throws DataException, EntityNotFoundException {
        if(id != dto.getPreferenceId()){
            return ResponseEntity.badRequest().body("ID path diverso da ID DTO");
        }
        Optional<UserPreferredCategory> oupc = categoryService.findUserPreferredCategoryById(id);
        if(oupc.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        UserPreferredCategory upc = dto.toUserPreferredCategory();
        upc.setPreferenceId(id);
        boolean updated = categoryService.updateUserPreferredCategory(upc, dto.getUserId(), dto.getCategoryId());
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserPreferredCategory(@PathVariable int id) throws DataException {
        boolean deleted = categoryService.deleteUserPreferredCategory(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
