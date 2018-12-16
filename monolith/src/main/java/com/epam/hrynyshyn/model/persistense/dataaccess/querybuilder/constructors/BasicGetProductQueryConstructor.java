package com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.constructors;

import com.epam.hrynyshyn.exceptions.TransactionFailureException;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.Function;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.SQLUtil;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.syntax.SQLBuilder;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.epam.hrynyshyn.constants.Constants.ErrorMessages.STATEMENT_PREPARE_FAILURE;
import static com.epam.hrynyshyn.constants.Constants.Product.ASC;
import static com.epam.hrynyshyn.constants.Constants.Product.CATEGORIES;
import static com.epam.hrynyshyn.constants.Constants.Product.CATEGORY_ID;
import static com.epam.hrynyshyn.constants.Constants.Product.CATEGORY_NAME;
import static com.epam.hrynyshyn.constants.Constants.Product.CURRENT_PAGE;
import static com.epam.hrynyshyn.constants.Constants.Product.DEFAULT_PRODUCT_PER_PAGE;
import static com.epam.hrynyshyn.constants.Constants.Product.DESC;
import static com.epam.hrynyshyn.constants.Constants.Product.MANUFACTURERS;
import static com.epam.hrynyshyn.constants.Constants.Product.MANUFACTURER_ID;
import static com.epam.hrynyshyn.constants.Constants.Product.MANUFACTURER_NAME;
import static com.epam.hrynyshyn.constants.Constants.Product.MAX_PRICE;
import static com.epam.hrynyshyn.constants.Constants.Product.MIN_PRICE;
import static com.epam.hrynyshyn.constants.Constants.Product.PRICE;
import static com.epam.hrynyshyn.constants.Constants.Product.PRODUCTS;
import static com.epam.hrynyshyn.constants.Constants.Product.PRODUCTS_PER_PAGE;
import static com.epam.hrynyshyn.constants.Constants.Product.SAMPLING_PARAMETERS;
import static com.epam.hrynyshyn.constants.Constants.Product.SORT;
import static com.epam.hrynyshyn.constants.Constants.Product.START_PAGE;
import static com.epam.hrynyshyn.constants.Constants.SQL.GT_EQ;
import static com.epam.hrynyshyn.constants.Constants.SQL.LT_EQ;
import static com.epam.hrynyshyn.constants.Constants.SQL.VALUE_INPUT;

public abstract class BasicGetProductQueryConstructor implements QueryConstructor {

    protected static Logger logger = Logger.getLogger(BasicGetProductQueryConstructor.class);

    protected Map<String, Object> parameters;
    protected SQLBuilder queryBuilder;
    protected int parametersCount;

    public BasicGetProductQueryConstructor(Map<String, Object> parameters) {
        this.parameters = parameters;
        this.parametersCount = 1;
    }

    public abstract String constructQuery();

    protected abstract List<String> getSelectedColumns();

    public void prepareStatement(PreparedStatement statement) {
        if (parameters.size() != 0) {
            doIfContains(parameters, MANUFACTURERS, () -> {
                for (String manufacturer : (List<String>) parameters.get(MANUFACTURERS)) {
                    try {
                        statement.setString(parametersCount++, manufacturer);
                    } catch (SQLException e) {
                        throw new TransactionFailureException(STATEMENT_PREPARE_FAILURE, e);
                    }
                }
            });
            doIfContains(parameters, CATEGORIES, () -> {
                for (String category : (List<String>) parameters.get(CATEGORIES)) {
                    try {
                        statement.setString(parametersCount++, category);
                    } catch (SQLException e) {
                        throw new RuntimeException(STATEMENT_PREPARE_FAILURE, e);
                    }
                }
            });
            doIfContains(parameters, MIN_PRICE, () -> {
                try {
                    statement.setInt(parametersCount++, (Integer) parameters.get(MIN_PRICE));
                } catch (SQLException e) {
                    throw new RuntimeException(STATEMENT_PREPARE_FAILURE, e);
                }
            });
            doIfContains(parameters, MAX_PRICE, () -> {
                try {
                    statement.setInt(parametersCount++, (Integer) parameters.get(MAX_PRICE));
                } catch (SQLException e) {
                    throw new RuntimeException(STATEMENT_PREPARE_FAILURE, e);
                }
            });
        }
    }


    protected void constructMainPart() {
        List<String> columns = getSelectedColumns();
        queryBuilder.select()
                .columns(columns)
                .from(PRODUCTS)
                .join(MANUFACTURERS)
                .on(SQLUtil.getTableColumn(PRODUCTS, MANUFACTURER_ID),
                        SQLUtil.getTableColumn(MANUFACTURERS, MANUFACTURER_ID))
                .join(CATEGORIES)
                .on(SQLUtil.getTableColumn(PRODUCTS, CATEGORY_ID),
                        SQLUtil.getTableColumn(CATEGORIES, CATEGORY_ID));
    }


    protected void constructParametrizedPart() {
        Map<String, Object> samplingParameters = getSamplingParameters();
        Iterator<Map.Entry<String, Object>> iterator = samplingParameters.entrySet().iterator();
        if (iterator.hasNext()) {
            queryBuilder.where();
        }
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            String key = entry.getKey();
            doIfEquals(key, MANUFACTURERS, () -> {
                int manufacturesListSize = ((List<String>) samplingParameters.get(MANUFACTURERS)).size();
                queryBuilder.or(new ArrayList<>(Collections.nCopies(manufacturesListSize, MANUFACTURER_NAME)));
            });
            doIfEquals(key, CATEGORIES, () -> {
                int manufacturesListSize = ((List<String>) samplingParameters.get(CATEGORIES)).size();
                queryBuilder.or(new ArrayList<>(Collections.nCopies(manufacturesListSize, CATEGORY_NAME)));
            });
            doIfEquals(key, MIN_PRICE, () -> queryBuilder.condition(PRICE, GT_EQ, VALUE_INPUT));
            doIfEquals(key, MAX_PRICE, () -> queryBuilder.condition(PRICE, LT_EQ, VALUE_INPUT));
            if (iterator.hasNext()) {
                queryBuilder.and();
            }
        }
    }

    private void doIfContains(Map<String, Object> parameters, String parameter, Function action) {
        if (parameters.containsKey(parameter)) {
            action.doAction();
        }
    }

    private void doIfEquals(String key, String parameter, Function action) {
        if (key.equals(parameter)) {
            action.doAction();
        }
    }

    private Map<String, Object> getSamplingParameters() {
        Map<String, Object> samplingParameters = new LinkedHashMap<>();
        List<String> suitableParameters = Arrays.asList(SAMPLING_PARAMETERS);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            if (suitableParameters.contains(entry.getKey())) {
                samplingParameters.put(entry.getKey(), entry.getValue());
            }
        }
        logger.info(samplingParameters.toString());
        return samplingParameters;
    }

    protected void constructSortPart() {
        if (parameters.containsKey(SORT)) {
            String sort = (String) parameters.get(SORT);
            String columnName = getSortColumn(sort);
            String sortMode = getSortMode(sort);
            if (columnName != null && sortMode != null) {
                queryBuilder.orderBy(columnName, sortMode);
            }
        }
    }

    protected void constructLimit() {
        int productsPerPage = DEFAULT_PRODUCT_PER_PAGE;
        int currentPage = START_PAGE;
        if (parameters.containsKey(PRODUCTS_PER_PAGE)) {
            productsPerPage = (Integer) parameters.get(PRODUCTS_PER_PAGE);
        }
        if (parameters.containsKey(CURRENT_PAGE)) {
            currentPage = (Integer) parameters.get(CURRENT_PAGE);
        }
        String offset = ((currentPage - 1) * productsPerPage) + "";
        String rowsCount = productsPerPage + "";
        queryBuilder.limit(offset, rowsCount);
    }

    private String getSortColumn(String sortParameter) {
        String input = sortParameter.split(" ")[0].toLowerCase();
        String column = null;
        if (input.equals(PRICE)) {
            column = PRICE;
        }
        if (input.equals(MANUFACTURER_NAME)) {
            column = SQLUtil.getTableColumn(MANUFACTURERS, MANUFACTURER_NAME);
        }
        return column;
    }

    private String getSortMode(String sortParameter) {
        String key = sortParameter.split(" ")[1];
        String mode = null;
        if (key.equals("asc")) {
            mode = ASC;
        }
        if (key.equals("desc")) {
            mode = DESC;
        }
        return mode;
    }

}
