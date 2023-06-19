package org.example;

import org.ini4j.Wini;

import java.io.IOException;
import java.io.InputStream;

public class DBProperties {
    static String url;
    static String uname;
    static String password;

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("db.ini")) {
            Wini ini = new Wini(inputStream);
            String url = ini.get("database", "dbURL", String.class);
            String uname = ini.get("database", "uname", String.class);
            String password = ini.get("database", "password", String.class);
            DBProperties.url = url;
            DBProperties.uname = uname;
            DBProperties.password = password;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}