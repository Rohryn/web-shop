package com.epam.hrynyshyn.repository;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interface for transaction logic.
 * Implementation should contain connection statement preparing and result set parsing logic.
 */
public interface TransactionOperation<T> {

    T doInTransaction(Connection connection) throws SQLException;

}
