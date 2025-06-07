package org.generation.italy.collectionarchive.models.service;

import jakarta.persistence.PersistenceException;
import org.generation.italy.collectionarchive.models.entities.Category;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.repositories.specifications.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaCategoryService implements CategoryService{
    private CategoryRepository categoryRepo;

    @Autowired
    public JpaCategoryService(CategoryRepository categoryRepo){
        this.categoryRepo = categoryRepo;
    }

    //CATEGORY
    @Override
    public List<Category> findAllCategories() throws DataException {
        return categoryRepo.findAll();
    }

    @Override
    public Optional<Category> findCategoryId(int categoryId) throws DataException {
        return categoryRepo.findById(categoryId);
    }

    @Override
    public Category createCategory(Category c) throws DataException, EntityNotFoundException {
        try {
            categoryRepo.save(c);
            return c;
        } catch (PersistenceException pe) {
            throw new DataException("Errore nella creazione della Categoria", pe);
        }
    }

    @Override
    public boolean deleteCategory(int categoryId) throws DataException {
        Optional<Category> co = categoryRepo.findById(categoryId);
        if(co.isEmpty()){
            return false;
        }
        categoryRepo.delete(co.get());
        return true;
    }

    @Override
    public boolean updateCategory(Category c) {
        try {
            Optional<Category> co = categoryRepo.findById(c.getCategoryId());
            if(co.isEmpty()){
                return false;
            }
            categoryRepo.save(c);
            return true;
        } catch (PersistenceException pe) {
            throw new DataException("errore nella modifica della categoria", pe);
        }
    }

}
