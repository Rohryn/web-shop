package com.epam.hrynyshyn.model.persistense.dataaccess.repositories.category;

import com.epam.hrynyshyn.model.persistense.dataaccess.TransactionManager;
import com.epam.hrynyshyn.model.persistense.entity.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.epam.hrynyshyn.constants.Constants.Queries.GET_ALL_CATEGORIES;

public class DefaultCategoryRepository implements CategoryRepository {
    private TransactionManager manager;

    public DefaultCategoryRepository(TransactionManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Category> getAll() {
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
