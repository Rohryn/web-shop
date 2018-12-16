package com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder;

import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.syntax.PreparedQueryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PreparedQueryBuilderTest {
    PreparedQueryBuilder builder;
    List<String> manufacturers;
    List<String> categories;
    List<String> columns;

    @Before
    public void setUp() throws Exception {
        builder = new PreparedQueryBuilder();
        manufacturers = new ArrayList<>();
        manufacturers.add("manufacturer");
        manufacturers.add("manufacturer");
        manufacturers.add("manufacturer");
        categories = new ArrayList<>();
        categories.add("category");
        categories.add("category");
        columns = new ArrayList<>();
        columns.add("name");
        columns.add("price");
        columns.add("description");
    }

    @Test
    public void testSimpleSelectQuery() throws Exception {
        String expectedQuery = "SELECT * FROM products WHERE  manufacturer=?  AND category=? OR price=?";
        builder.select()
                .allColumns()
                .from("products")
                .where("manufacturer")
                .and("category")
                .or("price");
        String constructedQuery = builder.getQuery();
        System.out.println(constructedQuery);

        assertEquals(expectedQuery, constructedQuery);
    }

    @Test
    public void testSelectQueryWithMultipleParameters() throws Exception {
        String expectedQuery = "SELECT name, price, description FROM products WHERE ((manufacturer=? OR manufacturer=? OR manufacturer=?) ) AND ((category=? OR category=?) )";
        builder.select()
                .columns(columns)
                .from("products")
                .where()
                .openBracket()
                .or(manufacturers)
                .closeBracket()
                .and()
                .openBracket()
                .or(categories)
                .closeBracket();
        String constructedQuery = builder.getQuery();
        System.out.println(constructedQuery);
        assertEquals(expectedQuery,constructedQuery);
    }

}