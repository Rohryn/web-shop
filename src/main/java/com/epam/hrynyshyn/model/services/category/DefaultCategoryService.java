package com.epam.hrynyshyn.model.services.category;

import com.epam.hrynyshyn.model.persistense.dataaccess.repositories.category.CategoryRepository;
import com.epam.hrynyshyn.model.persistense.entity.Category;

import java.util.List;

public class DefaultCategoryService implements CategoryService {
    private CategoryRepository repository;

    public DefaultCategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> getAllCategories() {
        return repository.getAll();
    }
}
