package com.epam.hrynyshyn.model.persistense.dataaccess.repositories.user;

import com.epam.hrynyshyn.exceptions.TransactionFailureException;
import com.epam.hrynyshyn.model.persistense.dataaccess.TransactionManager;
import com.epam.hrynyshyn.model.persistense.dataaccess.TransactionOperation;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.constructors.GetUserConstructor;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.constructors.GetUsersCountConstructor;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.constructors.NotPreparableQueryConstructor;
import com.epam.hrynyshyn.model.persistense.dataaccess.querybuilder.constructors.QueryConstructor;
import com.epam.hrynyshyn.model.persistense.dataaccess.repositories.product.DefaultProductRepository;
import com.epam.hrynyshyn.model.persistense.entity.User;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.epam.hrynyshyn.constants.Constants.Queries.ADD_USER;

/**
 * Repository for user. Contains necessary logic for database connectivity.
 */
public class DefaultUserRepository implements UserRepository {
    Logger logger = Logger.getLogger(DefaultProductRepository.class);
    private TransactionManager manager;

    public DefaultUserRepository(TransactionManager manager) {
        this.manager = manager;
    }

    @Override
    public void addUser(User user) throws TransactionFailureException {
        manager.executeTransaction((TransactionOperation<Void>) connection -> {
            PreparedStatement statement = connection.prepareStatement(ADD_USER);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.executeUpdate();
            return null;
        });
    }

    @Override
    public User getUser(String email) throws TransactionFailureException {
        QueryConstructor constructor=new GetUserConstructor(email);
        return manager.executeTransaction(connection -> {
            PreparedStatement statement = connection.prepareStatement(constructor.constructQuery());
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setRole(resultSet.getString(6));
            }
            return user;
        });
    }

    @Override
    public int getUsersCount() {
        NotPreparableQueryConstructor constructor = new GetUsersCountConstructor();
        return manager.executeTransaction(connection -> {
            PreparedStatement statement = connection.prepareStatement(constructor.constructQuery());
            ResultSet resultSet = statement.executeQuery();
            int count = 0;
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        });
    }
}
