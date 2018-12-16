package com.epam.hrynyshyn.model.persistense.dataaccess.repositories.order;

import com.epam.hrynyshyn.model.persistense.dataaccess.TransactionManager;
import com.epam.hrynyshyn.model.persistense.dataaccess.TransactionOperation;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.constructors.AddOrderedProductConstructor;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.constructors.QueryConstructor;
import com.epam.hrynyshyn.model.persistense.order.product.OrderedProduct;

import java.sql.PreparedStatement;

public class DefaultOrderedProductRepository implements OrderedProductRepository {
    private TransactionManager manager;

    public DefaultOrderedProductRepository(TransactionManager manager) {
        this.manager = manager;
    }

    @Override
    public void addProduct(OrderedProduct product) {
        QueryConstructor constructor = new AddOrderedProductConstructor(product);
        manager.executeTransaction((TransactionOperation<Void>) connection -> {
            PreparedStatement statement = connection.prepareStatement(constructor.constructQuery());
            constructor.prepareStatement(statement);
            statement.executeUpdate();
            return null;
        });
    }
}
