package com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.constructors;


import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface QueryConstructor extends NotPreparableQueryConstructor {

    void prepareStatement(PreparedStatement statement) throws SQLException;
}
