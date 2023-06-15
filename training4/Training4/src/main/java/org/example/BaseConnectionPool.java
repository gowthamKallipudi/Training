package org.example;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class BaseConnectionPool implements ConnectionPool {
    private String url;
    private String user;
    private String password;
    private static ArrayList<Connection> connectionPool;
    private ArrayList<Connection> usedConnection = new ArrayList<>();
    private static int pool_size = 10;

    public BaseConnectionPool(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public Connection getConnection() {
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnection.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnection.remove(connection);
    }

    public static BaseConnectionPool create(String url, String user, String password) throws SQLException {

        ArrayList<Connection> pool = new ArrayList<>(pool_size);
        for (int i = 0; i < pool_size; i++) {
            pool.add(createConnection(url, user, password));
        }
        connectionPool = pool;
        return new BaseConnectionPool(url, user, password);
    }

    private static Connection createConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void shutdown() throws SQLException {
        for(Connection con : usedConnection) {
            releaseConnection(con);
        }
        for(Connection con : connectionPool) {
            con.close();
        }
    }

}
