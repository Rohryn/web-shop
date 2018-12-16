package com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.syntax;

import java.util.List;

public interface SQLBuilder {

    SQLBuilder select();

    SQLBuilder insertInto(String table);

    SQLBuilder allColumns();

    SQLBuilder column(String column);

    SQLBuilder columns(List<String> columns);

    SQLBuilder values(List<String> columns);

    SQLBuilder condition(String firstArgument, String operation, String secondArgument);

    SQLBuilder from(String table);

    SQLBuilder from(List<String> tables);

    SQLBuilder join(String table);

    SQLBuilder on(String column, String foreignColumn);

    SQLBuilder where();

    SQLBuilder where(String parameter);

    SQLBuilder where(String firstParameter, String operation, String secondParameter);

    SQLBuilder or(String parameter);

    SQLBuilder or(List<String> parameters);

    SQLBuilder or(String firstParameter, String operation, String secondParameter);

    SQLBuilder orderBy(String columnName, String direction);

    SQLBuilder and();

    SQLBuilder and(String parameter);

    SQLBuilder and(List<String> parameters);

    SQLBuilder and(String firstParameter, String operation, String secondParameter);

    SQLBuilder openBracket();

    SQLBuilder limit(String offset, String rowsCount);

    SQLBuilder closeBracket();

    SQLBuilder count();

    String getQuery();
}
