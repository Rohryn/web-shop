package com.epam.hrynyshyn.model.persistense.dataaccess.repositories.order;

import com.epam.hrynyshyn.model.persistense.dataaccess.TransactionManager;
import com.epam.hrynyshyn.model.persistense.dataaccess.TransactionOperation;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.constructors.AddOrderConstructor;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.constructors.AddOrderedProductConstructor;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.constructors.QueryConstructor;
import com.epam.hrynyshyn.model.persistense.dataaccess.repositories.product.DefaultProductRepository;
import com.epam.hrynyshyn.model.persistense.order.Order;
import com.epam.hrynyshyn.model.persistense.order.product.OrderedProduct;
import com.mysql.jdbc.Statement;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DefaultOrderRepository implements OrderRepository {
    private static Logger logger = Logger.getLogger(DefaultProductRepository.class);
    private TransactionManager manager;

    public DefaultOrderRepository(TransactionManager manager) {
        this.manager = manager;
    }

    @Override
    public void addOrder(Order order) {
        manager.executeTransaction((TransactionOperation<Void>) connection -> {
            DefaultOrderRepository.this.addOrderInfo(connection, order);
            DefaultOrderRepository.this.addOrderedProducts(connection, order.getProducts());
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
