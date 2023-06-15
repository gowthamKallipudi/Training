package org.example;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Todo: 10) Question : Can we approach AutoCloable for this class {@link org.example.BaseConnectionPool}
 */
public class BaseConnectionPool{
    /**
     * Todo: 7) Question : Why do we have these unused variables?
     */

    private int pool_size = 10;

    private Queue<Connection> connectionQueue = new LinkedList<>();

    private static BaseConnectionPool baseConnectionPool;

    private BaseConnectionPool () {

    }

    public static BaseConnectionPool getInstance() {
        if(baseConnectionPool == null) {
            baseConnectionPool = new BaseConnectionPool();
        }
        return baseConnectionPool;
    }

    /**
     * Todo: 8) Question :
     * We are exposing {@link org.example.BaseConnectionPool} through singleton. Then why is this constructor open to public.
     * Is there any future Idea for this constructor?
     */

    public Connection getConnection(String url, String  uname, String password) {
        if(connectionQueue.size() > 0)
            return connectionQueue.remove();
        else {
            try {
                return createConnection(url, uname, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void releaseConnection(Connection connection) {
        if(connectionQueue.size() == pool_size) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            connectionQueue.add(connection);
        }
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

    private Connection createConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void shutdown() throws SQLException {
        for(Connection con : connectionQueue) {
            con.close();
        }
    }

}
