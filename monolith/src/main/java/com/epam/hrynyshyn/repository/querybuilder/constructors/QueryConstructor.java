package com.epam.hrynyshyn.repository.querybuilder.constructors;


import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface QueryConstructor extends NotPreparableQueryConstructor {

    void prepareStatement(PreparedStatement statement) throws SQLException;
}
