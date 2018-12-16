package com.epam.hrynyshyn.repository.querybuilder.syntax;

import java.util.Iterator;
import java.util.List;

import static com.epam.hrynyshyn.constants.Constants.SQL.ALL;
import static com.epam.hrynyshyn.constants.Constants.SQL.AND;
import static com.epam.hrynyshyn.constants.Constants.SQL.CLOSE_BRACKET;
import static com.epam.hrynyshyn.constants.Constants.SQL.COMMA;
import static com.epam.hrynyshyn.constants.Constants.SQL.COUNT;
import static com.epam.hrynyshyn.constants.Constants.SQL.EQUALITY_MARK;
import static com.epam.hrynyshyn.constants.Constants.SQL.FROM;
import static com.epam.hrynyshyn.constants.Constants.SQL.INSERT_INTO;
import static com.epam.hrynyshyn.constants.Constants.SQL.JOIN;
import static com.epam.hrynyshyn.constants.Constants.SQL.LIMIT;
import static com.epam.hrynyshyn.constants.Constants.SQL.ON;
import static com.epam.hrynyshyn.constants.Constants.SQL.OPEN_BRACKET;
import static com.epam.hrynyshyn.constants.Constants.SQL.OR;
import static com.epam.hrynyshyn.constants.Constants.SQL.ORDER_BY;
import static com.epam.hrynyshyn.constants.Constants.SQL.SELECT;
import static com.epam.hrynyshyn.constants.Constants.SQL.VALUES;
import static com.epam.hrynyshyn.constants.Constants.SQL.VALUE_HOLDER;
import static com.epam.hrynyshyn.constants.Constants.SQL.VALUE_INPUT;
import static com.epam.hrynyshyn.constants.Constants.SQL.WHERE;

public class PreparedQueryBuilder implements SQLBuilder {

    private StringBuilder queryBuilder;

    public PreparedQueryBuilder() {
        queryBuilder = new StringBuilder();
    }

    @Override
    public PreparedQueryBuilder select() {
        append(SELECT);
        appendWhiteSpace();
        return this;
    }

    @Override
    public PreparedQueryBuilder insertInto(String table) {
        append(INSERT_INTO);
        appendWhiteSpace();
        append(table);
        appendWhiteSpace();
        return this;
    }

    @Override
    public PreparedQueryBuilder allColumns() {
        append(ALL);
        appendWhiteSpace();
        return this;
    }

    @Override
    public PreparedQueryBuilder column(String column) {
        append(column);
        return this;
    }

    @Override
    public PreparedQueryBuilder columns(List<String> columns) {
        appendList(columns);
        return this;
    }

    @Override
    public SQLBuilder values(List<String> columns) {
        append(VALUES);
        Iterator<String> iterator = columns.iterator();
        if (iterator.hasNext()) {
            openBracket();
            while (iterator.hasNext()) {
                iterator.next();
                append(VALUE_INPUT);
                if (iterator.hasNext()) {
                    append(COMMA);
                    appendWhiteSpace();
                }
            }
            closeBracket();
        }
        return this;
    }

    @Override
    public SQLBuilder condition(String firstArgument, String operation, String secondArgument) {
        append(firstArgument);
        append(operation);
        append(secondArgument);
        appendWhiteSpace();
        return this;
    }

    @Override
    public PreparedQueryBuilder from(String table) {
        append(FROM);
        appendWhiteSpace();
        append(table);
        appendWhiteSpace();
        return this;
    }

    @Override
    public PreparedQueryBuilder from(List<String> tables) {
        append(FROM);
        appendWhiteSpace();
        appendList(tables);
        return this;
    }

    @Override
    public PreparedQueryBuilder join(String table) {
        append(JOIN);
        appendWhiteSpace();
        append(table);
        appendWhiteSpace();
        return this;
    }

    @Override
    public PreparedQueryBuilder on(String column, String foreignColumn) {
        append(ON);
        appendWhiteSpace();
        append(column);
        append(EQUALITY_MARK);
        append(foreignColumn);
        appendWhiteSpace();
        return this;
    }

    @Override
    public PreparedQueryBuilder where() {
        append(WHERE);
        appendWhiteSpace();
        return this;
    }

    @Override
    public PreparedQueryBuilder where(String parameter) {
        append(WHERE);
        appendWhiteSpace();
        appendParameter(parameter);
        appendWhiteSpace();
        return this;
    }

    @Override
    public SQLBuilder where(String firstParameter, String operation, String secondParameter) {
        append(WHERE);
        appendWhiteSpace();
        appendCondition(firstParameter, operation, secondParameter);
        return this;
    }


    @Override
    public PreparedQueryBuilder or(String parameter) {
        append(OR);
        appendParameter(parameter);
        return this;
    }

    @Override
    public PreparedQueryBuilder or(List<String> parameters) {
        appendParameters(parameters, OR);
        return this;
    }

    @Override
    public SQLBuilder or(String firstParameter, String operation, String secondParameter) {
        append(OR);
        appendCondition(firstParameter, operation, secondParameter);
        return this;
    }

    @Override
    public SQLBuilder orderBy(String columnName, String direction) {
        append(ORDER_BY);
        appendWhiteSpace();
        append(columnName);
        appendWhiteSpace();
        append(direction);
        appendWhiteSpace();
        return this;
    }

    @Override
    public PreparedQueryBuilder and(String parameter) {
        append(AND);
        appendParameter(parameter);
        return this;
    }

    @Override
    public PreparedQueryBuilder and(List<String> parameters) {
        appendParameters(parameters, AND);
        return this;
    }

    @Override
    public SQLBuilder and(String firstParameter, String operation, String secondParameter) {
        append(AND);
        appendCondition(firstParameter, operation, secondParameter);
        return this;
    }

    @Override
    public PreparedQueryBuilder and() {
        append(AND);
        appendWhiteSpace();
        return this;
    }

    @Override
    public PreparedQueryBuilder openBracket() {
        append(OPEN_BRACKET);
        return this;
    }

    @Override
    public SQLBuilder limit(String offset, String rowsCount) {
        append(LIMIT);
        appendWhiteSpace();
        append(offset);
        append(COMMA);
        append(rowsCount);
        return this;
    }

    @Override
    public PreparedQueryBuilder closeBracket() {
        append(CLOSE_BRACKET);
        appendWhiteSpace();
        return this;
    }

    @Override
    public SQLBuilder count() {
        append(COUNT);
        appendWhiteSpace();
        return this;
    }

    @Override
    public String getQuery() {
        return queryBuilder.toString().trim();
    }

    private void appendParameter(String parameter) {
        appendWhiteSpace();
        append(parameter);
        append(VALUE_HOLDER);
        appendWhiteSpace();
    }

    private void appendList(List<String> items) {
        Iterator<String> iterator = items.iterator();
        while (iterator.hasNext()) {
            append(iterator.next());
            if (iterator.hasNext()) {
                append(COMMA);
            }
        }
        appendWhiteSpace();
    }

    private void appendParameters(List<String> parameters, String separator) {
        Iterator<String> iterator = parameters.iterator();
        if (iterator.hasNext()) {
            openBracket();
        }
        while (iterator.hasNext()) {
            append(iterator.next());
            append(VALUE_HOLDER);
            if (iterator.hasNext()) {
                appendWhiteSpace();
                append(separator);
                appendWhiteSpace();
            } else {
                closeBracket();
            }
        }
    }

    private void appendCondition(String firstParameter, String operation, String secondParameter) {
        appendWhiteSpace();
        append(firstParameter);
        append(operation);
        append(secondParameter);
        appendWhiteSpace();
    }

    private void append(String value) {
        queryBuilder.append(value);
    }

    private void appendWhiteSpace() {
        queryBuilder.append(" ");
    }
}
