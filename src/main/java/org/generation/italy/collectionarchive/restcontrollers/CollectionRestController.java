package org.generation.italy.collectionarchive.restcontrollers;

import org.generation.italy.collectionarchive.models.entities.Collection;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.service.CollectionService;
import org.generation.italy.collectionarchive.restdto.CollectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/collection")

public class CollectionRestController {
    private CollectionService collectionService;

    @Autowired
    public CollectionRestController(CollectionService collectionService){
        this.collectionService = collectionService;
    }


    @GetMapping
    public ResponseEntity<?> getAllCollection() throws DataException {

        List<CollectionDto> collectionDtos = collectionService.findAllCollection()
                .stream().map(CollectionDto::toDto).toList();
        return ResponseEntity.ok( collectionDtos);
    }
}
