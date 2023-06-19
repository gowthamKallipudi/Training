package org.example;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BasicConnectionPool {

    private int pool_size = 10;

    private Queue<Connection> connectionQueue = new LinkedList<>();

    private ArrayList<Connection> inUseConnection = new ArrayList<>();

    private static BasicConnectionPool basicConnectionPool;

    private BasicConnectionPool() {

    }

    public static BasicConnectionPool getInstance() {
        if (basicConnectionPool == null) {
            basicConnectionPool = new BasicConnectionPool();
        }
        return basicConnectionPool;
    }

    public Connection getConnection() {
        Connection con;
        if (connectionQueue.size() > 0) {
            con = connectionQueue.remove();
            inUseConnection.add(con);
            return con;
        } else {
            try {
                con = createConnection();
                inUseConnection.add(con);
                return con;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void releaseConnection(Connection connection) {
        inUseConnection.remove(connection);
        if (connectionQueue.size() == pool_size) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            connectionQueue.add(connection);
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DBProperties.url, DBProperties.uname, DBProperties.password);
    }

    public void shutdown() throws SQLException {
        for (Connection con : inUseConnection) {
            con.close();
        }
        for (Connection con : connectionQueue) {
            con.close();
        }
    }
}