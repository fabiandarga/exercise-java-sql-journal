package org.exercise.java.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    Connection connection;
    public Database(String url, String user, String password) throws java.sql.SQLException {
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public ResultSet executeQuery(String query) throws java.sql.SQLException {
        Statement stmt = this.connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }
}
