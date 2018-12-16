package com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.constructors;


import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.syntax.PreparedQueryBuilder;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.syntax.SQLBuilder;
import com.epam.hrynyshyn.model.persistense.order.Order;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.hrynyshyn.constants.Constants.Order.*;

public class AddOrderConstructor implements QueryConstructor {
    private SQLBuilder sqlBuilder;
    private Order order;

    public AddOrderConstructor(Order order) {
        this.order = order;
    }

    @Override
    public String constructQuery() {
        sqlBuilder = new PreparedQueryBuilder();
        sqlBuilder.insertInto(ORDERS)
                .openBracket()
                .columns(getInsertColumns())
                .closeBracket()
                .values(getInsertColumns());
        return sqlBuilder.getQuery();
    }

    @Override
    public void prepareStatement(PreparedStatement statement) throws SQLException {
        statement.setString(1, order.getStatus());
        statement.setString(2, order.getStatusDescription());
        statement.setString(3, order.getShipmentAddress());
        statement.setString(4, order.getPaymentCard());
        statement.setInt(5, order.getCustomer().getId());
    }

    private List<String> getInsertColumns() {
        List<String> columns = new ArrayList<>();
        columns.add(STATUS);
        columns.add(STATUS_DESCRIPTION);
        columns.add(SHIPMENT_ADDRESS);
        columns.add(PAYMENT_CARD);
        columns.add(CUSTOMER_ID);
        return columns;
    }
}