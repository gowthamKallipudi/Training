package org.example;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Todo: 10) Question : Can we approach AutoCloable for this class {@link org.example.BaseConnectionPool}
 */
public class BaseConnectionPool implements ConnectionPool {
    /**
     * Todo: 7) Question : Why do we have these unused variables?
     */
    private String url;
    private String user;
    private String password;
    private static ArrayList<Connection> connectionPool;
    private ArrayList<Connection> usedConnection = new ArrayList<>();
    private static int pool_size = 10;

    /**
     * Todo: 8) Question :
     * We are exposing {@link org.example.BaseConnectionPool} through singleton. Then why is this constructor open to public.
     * Is there any future Idea for this constructor?
     */
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

    /**
     * Todo: 9) Question : By default why are we keeping {@link org.example.BaseConnectionPool#pool_size} number of connections alive?
     * Suggestion:
     * <ol>
     *     <li>Don't create connections by default</li>
     *     <li>
     *         Create connection when {@link BaseConnectionPool#getConnection()} is called. Then push the object reference into a List.
     *     </li>
     *     <li>
     *         In other words, keep a list of Connection(List<Connection>) which tells connections in use.
     *         May be name it inUseConnections
     *     </li>
     *     <li>
     *         Then when {@link BaseConnectionPool#releaseConnection(Connection)} is called move the connection to another List.
     *         May be name it cached. Whenever {@link BaseConnectionPool#releaseConnection(Connection)} is called, check the cached count if it exceed the {@link BaseConnectionPool#pool_size}.
     *         If yes, remove the old connection and close it. For this you can use Queue instead of List.
     *     </li>
     * </ol>
     *
     *
     */
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
