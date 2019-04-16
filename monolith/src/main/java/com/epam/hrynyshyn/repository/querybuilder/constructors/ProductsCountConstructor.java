package com.epam.hrynyshyn.repository.querybuilder.constructors;

import com.epam.hrynyshyn.repository.querybuilder.syntax.PreparedQueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductsCountConstructor extends BasicGetProductQueryConstructor {
    public ProductsCountConstructor(Map<String, Object> parameters) {
        super(parameters);
    }

    @Override
    public String constructQuery() {
        queryBuilder = new PreparedQueryBuilder();
        constructMainPart();
        constructParametrizedPart();
        logger.info("built query: " + queryBuilder.getQuery());
        return queryBuilder.getQuery();
    }

    @Override
    protected List<String> getSelectedColumns() {
        List<String> columns = new ArrayList<>();
        columns.add("COUNT(*)");
        return columns;
    }
}
