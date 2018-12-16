package com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.constructors;


import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.syntax.PreparedQueryBuilder;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.syntax.SQLBuilder;

import static com.epam.hrynyshyn.constants.Constants.User.USERS;

public class GetUsersCountConstructor implements NotPreparableQueryConstructor {
    private SQLBuilder builder;

    @Override
    public String constructQuery() {
        builder = new PreparedQueryBuilder();
        builder.select()
                .count()
                .from(USERS);
        return builder.getQuery();
    }
}
