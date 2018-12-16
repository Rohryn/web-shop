package com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.constructors;


import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.SQLUtil;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.syntax.PreparedQueryBuilder;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.syntax.SQLBuilder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.hrynyshyn.constants.Constants.Role.ROLE;
import static com.epam.hrynyshyn.constants.Constants.Role.ROLES;
import static com.epam.hrynyshyn.constants.Constants.Role.ROLE_ID;
import static com.epam.hrynyshyn.constants.Constants.SQL.EQUALITY_MARK;
import static com.epam.hrynyshyn.constants.Constants.SQL.VALUE_INPUT;
import static com.epam.hrynyshyn.constants.Constants.User.EMAIL;
import static com.epam.hrynyshyn.constants.Constants.User.FIRST_NAME;
import static com.epam.hrynyshyn.constants.Constants.User.ID;
import static com.epam.hrynyshyn.constants.Constants.User.LAST_NAME;
import static com.epam.hrynyshyn.constants.Constants.User.PASSWORD;
import static com.epam.hrynyshyn.constants.Constants.User.USERS;

public class GetUserConstructor implements QueryConstructor {
    private String email;
    private SQLBuilder builder;

    public GetUserConstructor(String email) {
        this.email = email;
    }

    @Override
    public String constructQuery() {
        builder = new PreparedQueryBuilder();
        builder = new PreparedQueryBuilder();
        builder.select()
                .columns(getSelectedColumns())
                .from(USERS)
                .join(ROLES)
                .on(SQLUtil.getTableColumn(USERS, ROLE_ID),
                        SQLUtil.getTableColumn(ROLES, ROLE_ID))
                .where(EMAIL, EQUALITY_MARK, VALUE_INPUT);
        return builder.getQuery();
    }

    @Override
    public void prepareStatement(PreparedStatement statement) throws SQLException {
        statement.setString(1, email);
    }

    protected List<String> getSelectedColumns() {
        List<String> columns = new ArrayList<>();
        columns.add(ID);
        columns.add(FIRST_NAME);
        columns.add(LAST_NAME);
        columns.add(EMAIL);
        columns.add(PASSWORD);
        columns.add(SQLUtil.getTableColumn(ROLES, ROLE));
        return columns;
    }
}
