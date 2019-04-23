package com.epam.hrynyshyn.repository.repositories.impl;

import com.epam.hrynyshyn.model.order.Order;
import com.epam.hrynyshyn.model.order.OrderedProduct;
import com.epam.hrynyshyn.repository.TransactionManager;
import com.epam.hrynyshyn.repository.TransactionOperation;
import com.epam.hrynyshyn.repository.querybuilder.constructors.AddOrderConstructor;
import com.epam.hrynyshyn.repository.querybuilder.constructors.AddOrderedProductConstructor;
import com.epam.hrynyshyn.repository.querybuilder.constructors.QueryConstructor;
import com.mysql.jdbc.Statement;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Deprecated
//@Repository
public class OrderRepositoryImpl /*implements OrderRepository */{
    private static Logger logger = Logger.getLogger(ProductRepositoryImpl.class);
    private TransactionManager manager;

    public OrderRepositoryImpl(TransactionManager manager) {
        this.manager = manager;
    }

//    @Override
    public void addOrder(Order order) {
        manager.executeTransaction((TransactionOperation<Void>) connection -> {
            OrderRepositoryImpl.this.addOrderInfo(connection, order);
            OrderRepositoryImpl.this.addOrderedProducts(connection, order.getProducts());
            return null;
        });
    }

    private void addOrderInfo(Connection connection, Order order) throws SQLException {
        QueryConstructor queryConstructor = new AddOrderConstructor(order);
        String query = queryConstructor.constructQuery();
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        queryConstructor.prepareStatement(statement);
        statement.executeUpdate();
        updateOrder(order, statement.getGeneratedKeys());
        statement.close();
    }

    private void updateOrder(Order order, ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            order.setOrderId(resultSet.getInt(1));
        }
        updateOrderedProducts(order);
    }

    private void addOrderedProducts(Connection connection, List<OrderedProduct> products) throws SQLException {
        for (OrderedProduct product : products) {
            QueryConstructor queryConstructor = new AddOrderedProductConstructor(product);
            PreparedStatement statement = connection.prepareStatement(queryConstructor.constructQuery());
            queryConstructor.prepareStatement(statement);
            statement.executeUpdate();
            statement.close();
        }
    }

    private void updateOrderedProducts(Order order) {
        List<OrderedProduct> orderedProducts = order.getProducts();
        for (OrderedProduct orderedProduct : orderedProducts) {
            orderedProduct.setOrderId(order.getOrderId());
        }
    }
}
