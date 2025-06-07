package org.generation.italy.collectionarchive.models.service;

import org.generation.italy.collectionarchive.models.entities.Category;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.entities.UserPreferredCategory;
import org.generation.italy.collectionarchive.models.exceptions.DataException;
import org.generation.italy.collectionarchive.models.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CategoryService  {

    //CATEGORY
    List<Category> findAllCategories() throws DataException;
    Optional<Category> findCategoryId(int categoryId) throws DataException;
    Category createCategory(Category c) throws DataException, EntityNotFoundException;
    boolean deleteCategory(int categoryId) throws DataException;
    boolean updateCategory(Category c);
    //USER PREFERRED CATEGORY
    List<UserPreferredCategory> findAllUserPreferredCategories() throws DataException;
    Optional<UserPreferredCategory> findUserPreferredCategoryById(int preferenceId) throws DataException;
    UserPreferredCategory createUserPreferredCategory(UserPreferredCategory upc, int userId, int categoryId) throws DataException, EntityNotFoundException;
    boolean deleteUserPreferredCategory(int preferenceId) throws DataException;
    boolean updateUserPreferredCategory(UserPreferredCategory upc, int userId, int categoryId);
}
