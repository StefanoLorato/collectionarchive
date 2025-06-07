package org.generation.italy.collectionarchive.models.service;

import jakarta.persistence.PersistenceException;
import org.generation.italy.collectionarchive.models.entities.Category;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.entities.UserPreferredCategory;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;
import org.generation.italy.collectionarchive.models.repositories.CategoryRepository;
import org.generation.italy.collectionarchive.models.repositories.UserPreferredCategoryRepository;
import org.generation.italy.collectionarchive.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaCategoryService implements CategoryService{
    private CategoryRepository categoryRepo;
    private UserPreferredCategoryRepository userPreferredCategoryRepo;
    private UserRepository userRepo;

    @Autowired
    public JpaCategoryService(CategoryRepository categoryRepo,UserPreferredCategoryRepository userPreferredCategoryRepo, UserRepository userRepo){
        this.categoryRepo = categoryRepo;
        this.userPreferredCategoryRepo= userPreferredCategoryRepo;
        this.userRepo= userRepo;
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

    @Override
    public List<UserPreferredCategory> findAllUserPreferredCategories() throws DataException {
        return userPreferredCategoryRepo.findAll();
    }

    @Override
    public Optional<UserPreferredCategory> findUserPreferredCategoryById(int preferenceId) throws DataException {
        return userPreferredCategoryRepo.findById(preferenceId);
    }

    @Override
    public UserPreferredCategory createUserPreferredCategory(UserPreferredCategory upc, int userId, int categoryId) throws DataException, EntityNotFoundException {
        try{
            User u = userRepo.findById(userId).orElseThrow(()-> new EntityNotFoundException("Utente con user id" + userId + "nontrovato"));
            Category c = categoryRepo.findById(categoryId).orElseThrow(()-> new EntityNotFoundException("Categoria con id" +categoryId + "nontrovata"));
            upc.setUser(u);
            upc.setCategory(c);
            return userPreferredCategoryRepo.save(upc);
        }catch (PersistenceException pe) {
            throw new DataException("Errore nella creazione" , pe);
        }
    }

    @Override
    public boolean deleteUserPreferredCategory(int preferenceId) throws DataException {
        Optional<UserPreferredCategory> upc = userPreferredCategoryRepo.findById(preferenceId);
        if(upc.isEmpty()){
            return false;
        }
        userPreferredCategoryRepo.delete(upc.get());
        return true;
    }

    @Override
    public boolean updateUserPreferredCategory(UserPreferredCategory upc, int userId, int categoryId) {
        try {
            Optional<User> ou = userRepo.findById(userId);
            Optional<Category> oc = categoryRepo.findById(categoryId);
            if(ou.isEmpty() || oc.isEmpty()){
                return false;
            }
            upc.setUser(ou.get());
            upc.setCategory(oc.get());
            userPreferredCategoryRepo.save(upc);
            return true;
        }catch (PersistenceException pe) {
            throw new DataException("errore nella modifica della categoria", pe);
        }
    }

}
