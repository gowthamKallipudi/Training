package org.example;

import java.sql.Connection;

/**
 * Todo: 6) Question : Why do we need this Interface?
 */
public interface ConnectionPool {

    Connection getConnection();
    boolean releaseConnection(Connection connection);

}
