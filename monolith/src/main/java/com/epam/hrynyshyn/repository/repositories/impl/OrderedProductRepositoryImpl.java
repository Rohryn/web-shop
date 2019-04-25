package com.epam.hrynyshyn.repository.repositories.impl;

import com.epam.hrynyshyn.model.order.OrderedProduct;
import com.epam.hrynyshyn.repository.TransactionManager;
import com.epam.hrynyshyn.repository.TransactionOperation;
import com.epam.hrynyshyn.repository.querybuilder.constructors.AddOrderedProductConstructor;
import com.epam.hrynyshyn.repository.querybuilder.constructors.QueryConstructor;

import java.sql.PreparedStatement;
@Deprecated
//@Repository
public class OrderedProductRepositoryImpl /*implements OrderedProductRepository*/ {
    private TransactionManager manager;

//    public OrderedProductRepositoryImpl(TransactionManager manager) {
//        this.manager = manager;
//    }

//    @Override
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
