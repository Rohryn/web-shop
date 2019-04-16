package com.epam.hrynyshyn.repository;

import com.epam.hrynyshyn.exceptions.TransactionFailureException;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static com.epam.hrynyshyn.constants.Constants.ErrorMessages.TRANSACTION_FAILURE;
import static com.epam.hrynyshyn.constants.Constants.ErrorMessages.TRANSACTION_ROLLBACK;

public class TransactionManager {
    private static Logger logger = Logger.getLogger(TransactionManager.class);
    private DataSource dataSource;

    public TransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> T executeTransaction(TransactionOperation<T> operation) {
        T result;
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try {
                result = operation.doInTransaction(connection);
            } catch (SQLException ex) {
                logger.error(TRANSACTION_ROLLBACK, ex);
                connection.rollback();
                connection.setAutoCommit(true);
                throw ex;
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error(TRANSACTION_FAILURE, e);
            throw new TransactionFailureException(TRANSACTION_FAILURE, e);
        }
        return result;
    }
}
