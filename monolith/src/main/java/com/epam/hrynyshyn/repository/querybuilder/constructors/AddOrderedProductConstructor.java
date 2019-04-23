package com.epam.hrynyshyn.repository.querybuilder.constructors;

import com.epam.hrynyshyn.repository.querybuilder.syntax.PreparedQueryBuilder;
import com.epam.hrynyshyn.repository.querybuilder.syntax.SQLBuilder;
import com.epam.hrynyshyn.model.order.OrderedProduct;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.hrynyshyn.constants.Constants.Order.ORDERED_PRODUCTS;
import static com.epam.hrynyshyn.constants.Constants.Order.ORDER_ID;
import static com.epam.hrynyshyn.constants.Constants.Order.PRODUCT_COUNT;
import static com.epam.hrynyshyn.constants.Constants.Order.PRODUCT_DESCRIPTION;
import static com.epam.hrynyshyn.constants.Constants.Order.PRODUCT_MANUFACTURER;
import static com.epam.hrynyshyn.constants.Constants.Order.PRODUCT_NAME;
import static com.epam.hrynyshyn.constants.Constants.Order.PRODUCT_PRICE;

public class AddOrderedProductConstructor implements QueryConstructor {
    private SQLBuilder sqlBuilder;
    private OrderedProduct orderedProduct;

    public AddOrderedProductConstructor(OrderedProduct orderedProduct) {
        this.orderedProduct = orderedProduct;
    }

    @Override
    public String constructQuery() {
        sqlBuilder = new PreparedQueryBuilder();
        sqlBuilder.insertInto(ORDERED_PRODUCTS)
                .openBracket()
                .columns(getInsertColumns())
                .closeBracket()
                .values(getInsertColumns());
        return sqlBuilder.getQuery();
    }

    @Override
    public void prepareStatement(PreparedStatement statement) throws SQLException {
        statement.setInt(1, orderedProduct.getOrderId());
        statement.setString(2, orderedProduct.getName());
        statement.setInt(3, orderedProduct.getManufacturerId());
        statement.setString(4, orderedProduct.getDescription());
        statement.setInt(5, orderedProduct.getPrice());
        statement.setInt(6, orderedProduct.getCount());
    }

    private List<String> getInsertColumns() {
        List<String> columns = new ArrayList<>();
        columns.add(ORDER_ID);
        columns.add(PRODUCT_NAME);
        columns.add(PRODUCT_MANUFACTURER);
        columns.add(PRODUCT_DESCRIPTION);
        columns.add(PRODUCT_PRICE);
        columns.add(PRODUCT_COUNT);
        return columns;
    }


}
