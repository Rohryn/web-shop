package com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.constructors;

import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.SQLUtil;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.syntax.PreparedQueryBuilder;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.syntax.SQLBuilder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.hrynyshyn.constants.Constants.Product.*;
import static com.epam.hrynyshyn.constants.Constants.SQL.EQUALITY_MARK;
import static com.epam.hrynyshyn.constants.Constants.SQL.VALUE_INPUT;

public class GetProductByIdConstructor implements QueryConstructor {
    int productId;
    private SQLBuilder builder;

    public GetProductByIdConstructor(int productId) {
        this.productId = productId;
    }

    @Override
    public String constructQuery() {
        builder = new PreparedQueryBuilder();
        builder.select()
                .columns(getSelectedColumns())
                .from(PRODUCTS)
                .join(MANUFACTURERS)
                .on(SQLUtil.getTableColumn(PRODUCTS, MANUFACTURER_ID),
                        SQLUtil.getTableColumn(MANUFACTURERS, MANUFACTURER_ID))
                .join(CATEGORIES)
                .on(SQLUtil.getTableColumn(PRODUCTS, CATEGORY_ID),
                        SQLUtil.getTableColumn(CATEGORIES, CATEGORY_ID))
                .where("product_id", EQUALITY_MARK, VALUE_INPUT);
        return builder.getQuery();
    }

    @Override
    public void prepareStatement(PreparedStatement statement) throws SQLException {
        statement.setInt(1, productId);
    }

    protected List<String> getSelectedColumns() {
        List<String> columns = new ArrayList<>();
        columns.add(PRODUCT_ID);
        columns.add(NAME);
        columns.add(SQLUtil.getTableColumn(PRODUCTS, CATEGORY_ID));
        columns.add(SQLUtil.getTableColumn(CATEGORIES, CATEGORY_NAME));
        columns.add(SQLUtil.getTableColumn(PRODUCTS, MANUFACTURER_ID));
        columns.add(SQLUtil.getTableColumn(MANUFACTURERS, MANUFACTURER_NAME));
        columns.add(PRICE);
        columns.add(DESCRIPTION);
        columns.add(IMAGE_SOURCE);
        return columns;
    }

}
