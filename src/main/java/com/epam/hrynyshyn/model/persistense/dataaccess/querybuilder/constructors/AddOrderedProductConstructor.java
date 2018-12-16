package com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.constructors;

import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.syntax.PreparedQueryBuilder;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.syntax.SQLBuilder;
import com.epam.hrynyshyn.model.persistense.order.product.OrderedProduct;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.hrynyshyn.constants.Constants.Order.*;

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
        statement.setString(3, orderedProduct.getManufacturer());
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
