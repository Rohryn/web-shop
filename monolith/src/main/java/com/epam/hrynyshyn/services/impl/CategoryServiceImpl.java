package com.epam.hrynyshyn.services.impl;

import com.epam.hrynyshyn.repository.repositories.CategoryRepository;
import com.epam.hrynyshyn.model.entity.Category;
import com.epam.hrynyshyn.services.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> getAllCategories() {
        return repository.getAll();
    }
}
