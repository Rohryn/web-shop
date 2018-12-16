package com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.constructors;

import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.SQLUtil;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.syntax.PreparedQueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.epam.hrynyshyn.constants.Constants.Product.CATEGORIES;
import static com.epam.hrynyshyn.constants.Constants.Product.CATEGORY_ID;
import static com.epam.hrynyshyn.constants.Constants.Product.CATEGORY_NAME;
import static com.epam.hrynyshyn.constants.Constants.Product.DESCRIPTION;
import static com.epam.hrynyshyn.constants.Constants.Product.IMAGE_SOURCE;
import static com.epam.hrynyshyn.constants.Constants.Product.MANUFACTURERS;
import static com.epam.hrynyshyn.constants.Constants.Product.MANUFACTURER_ID;
import static com.epam.hrynyshyn.constants.Constants.Product.MANUFACTURER_NAME;
import static com.epam.hrynyshyn.constants.Constants.Product.NAME;
import static com.epam.hrynyshyn.constants.Constants.Product.PRICE;
import static com.epam.hrynyshyn.constants.Constants.Product.PRODUCTS;
import static com.epam.hrynyshyn.constants.Constants.Product.PRODUCT_ID;

public class GetFullProductInfoConstructor extends BasicGetProductQueryConstructor {
    public GetFullProductInfoConstructor(Map<String, Object> parameters) {
        super(parameters);
    }

    @Override
    public String constructQuery() {
        queryBuilder = new PreparedQueryBuilder();
        constructMainPart();
        constructParametrizedPart();
        constructSortPart();
        constructLimit();
        logger.info("built query: " + queryBuilder.getQuery());
        return queryBuilder.getQuery();
    }

    @Override
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
