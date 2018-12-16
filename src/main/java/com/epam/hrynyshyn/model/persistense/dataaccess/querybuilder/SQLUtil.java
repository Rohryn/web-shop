package com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder;


public class SQLUtil {
    public static String getTableColumn(String table, String column) {
        return String.format("%1s.%2s", table, column);
    }
}
