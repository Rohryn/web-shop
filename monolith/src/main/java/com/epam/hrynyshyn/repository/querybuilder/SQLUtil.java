package com.epam.hrynyshyn.repository.querybuilder;


public class SQLUtil {
    public static String getTableColumn(String table, String column) {
        return String.format("%1s.%2s", table, column);
    }
}
