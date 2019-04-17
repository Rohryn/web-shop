package com.epam.hrynyshyn.repository.repositories;

import com.epam.hrynyshyn.model.entity.Category;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CategoryRepository extends Repository<Category, Integer> {
    List<Category> findAll();
}
