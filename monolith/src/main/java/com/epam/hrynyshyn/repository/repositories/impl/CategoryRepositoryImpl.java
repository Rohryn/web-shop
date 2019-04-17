package com.epam.hrynyshyn.repository.repositories.impl;

import com.epam.hrynyshyn.model.entity.Category;
import com.epam.hrynyshyn.repository.TransactionManager;
import com.epam.hrynyshyn.repository.repositories.CategoryRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.epam.hrynyshyn.constants.Constants.Queries.GET_ALL_CATEGORIES;

@Deprecated
//@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    private TransactionManager manager;

    public CategoryRepositoryImpl(TransactionManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Category> findAll() {
        return manager.executeTransaction(connection -> {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_CATEGORIES);
            ResultSet resultSet = statement.executeQuery();
            List<Category> categories = new ArrayList<>();
            while (resultSet.next()) {
                Category category = new Category(resultSet.getInt(1), resultSet.getString(2));
                categories.add(category);
            }
            return categories;
        });
    }
}
