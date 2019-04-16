package com.epam.hrynyshyn.repository.repositories;

import com.epam.hrynyshyn.model.entity.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> getAll();
}
