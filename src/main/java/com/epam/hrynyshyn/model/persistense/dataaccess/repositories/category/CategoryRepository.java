package com.epam.hrynyshyn.model.persistense.dataaccess.repositories.category;

import com.epam.hrynyshyn.model.persistense.entity.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> getAll();
}
