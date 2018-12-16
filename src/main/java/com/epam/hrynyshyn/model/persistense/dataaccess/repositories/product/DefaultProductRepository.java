package com.epam.hrynyshyn.model.persistense.dataaccess.repositories.product;

import com.epam.hrynyshyn.exceptions.TransactionFailureException;
import com.epam.hrynyshyn.model.persistense.dataaccess.TransactionManager;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.constructors.GetFullProductInfoConstructor;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.constructors.GetProductByIdConstructor;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.constructors.ProductsCountConstructor;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.constructors.QueryConstructor;
import com.epam.hrynyshyn.model.persistense.entity.Category;
import com.epam.hrynyshyn.model.persistense.entity.Manufacturer;
import com.epam.hrynyshyn.model.persistense.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DefaultProductRepository implements ProductRepository {
    private TransactionManager manager;

    public DefaultProductRepository(TransactionManager manager) {
        this.manager = manager;
    }

    @Override
    public Product getProductById(int productId) {
        QueryConstructor constructor = new GetProductByIdConstructor(productId);
        return manager.executeTransaction(connection -> {
            PreparedStatement statement = connection.prepareStatement(constructor.constructQuery());
            constructor.prepareStatement(statement);
            ResultSet resultSet = statement.executeQuery();
            Product product = new Product();
            if (resultSet.next()) {
                product = getProductFromResultSet(resultSet);
            }
            return product;
        });
    }

    @Override
    public int getProductsCountBySelectParameters(Map<String, Object> parameters) throws TransactionFailureException {

        QueryConstructor constructor = new ProductsCountConstructor(parameters);
        return manager.executeTransaction(connection -> {
            PreparedStatement statement = connection.prepareStatement(constructor.constructQuery());
            constructor.prepareStatement(statement);
            ResultSet resultSet = statement.executeQuery();
            int count = 0;
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        });
    }

    @Override
    public List<Product> getProductsBySelectParameters(Map<String, Object> parameters) throws TransactionFailureException {
        QueryConstructor constructor = new GetFullProductInfoConstructor(parameters);
        return manager.executeTransaction(connection -> {
            PreparedStatement statement = connection.prepareStatement(constructor.constructQuery());
            constructor.prepareStatement(statement);
            return parseProductsResultSet(statement.executeQuery());
        });
    }

    private List<Product> parseProductsResultSet(ResultSet resultSet) throws SQLException {
        List<Product> products = new ArrayList<Product>();
        while (resultSet.next()) {
            products.add(getProductFromResultSet(resultSet));
        }
        return products;
    }

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt(1));
        product.setName(resultSet.getString(2));
        product.setCategory(new Category(resultSet.getInt(3), resultSet.getString(4)));
        product.setManufacturer(new Manufacturer(resultSet.getInt(5), resultSet.getString(6)));
        product.setPrice(resultSet.getInt(7));
        product.setDescription(resultSet.getString(8));
        product.setImageSource(resultSet.getString(9));
        return product;
    }


}
