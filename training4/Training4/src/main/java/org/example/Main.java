package org.example;

import org.ini4j.Wini;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        BaseConnectionPool connection = null;
        InputStream inputStream = null;
        try {

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            inputStream = classLoader.getResourceAsStream("db.ini");
            Wini ini = new Wini(inputStream);
            String url = ini.get("database", "dbURL", String.class);
            String uname = ini.get("database", "uname", String.class);
            String password = ini.get("database", "password", String.class);

            connection = BaseConnectionPool.create(url, uname, password);
            View view = new View();
            new Controller(view, connection);
            view.start();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.shutdown();
                if (inputStream != null)
                    inputStream.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}