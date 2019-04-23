package com.epam.hrynyshyn.services;

import com.epam.hrynyshyn.model.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    List<Category> getByNames(List<String> names);
}
