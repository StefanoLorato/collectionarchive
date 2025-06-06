package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.entities.Category;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.service.CollectionService;
import org.generation.italy.collectionarchive.restdto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryRestController {
    private CollectionService categoryService;

    @Autowired
    public CategoryRestController(CollectionService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> searchCategories() throws DataException {
        List<CategoryDto> categories = categoryService.findAllCategories().stream().map(CategoryDto::toDto).toList();
        return  ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCategoryById(@PathVariable int id) throws DataException {
        Optional<Category> ca = categoryService.findCategoryId(id);
        if(ca.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        CategoryDto co = CategoryDto.toDto(ca.get());
        return ResponseEntity.ok(co);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id) throws DataException {
        boolean deleted = categoryService.deleteCategory(id);
        if(!deleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto dto) throws DataException, EntityNotFoundException {
        Category s = dto.toCategory();
        categoryService.createCategory(s);
        CategoryDto saved = CategoryDto.toDto(s);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getCategoryId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable int id, @RequestBody CategoryDto dto) throws DataException, EntityNotFoundException{
        if(id != dto.getCategoryId()){
            return ResponseEntity.badRequest().body("L'id del path non corrisponde all'id del dto");
        }
        Optional<Category> ca = categoryService.findCategoryId(id);
        if(ca.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Category c = dto.toCategory();
        c.setCategoryId(id);

        boolean updated = categoryService.updateCategory(c);
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
