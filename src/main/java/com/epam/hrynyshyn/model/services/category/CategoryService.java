package com.epam.hrynyshyn.model.services.category;

import com.epam.hrynyshyn.model.persistense.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();
}
