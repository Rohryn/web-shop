package com.epam.hrynyshyn.repository.repositories;

import com.epam.hrynyshyn.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByNameIn(List<String> names);
}
