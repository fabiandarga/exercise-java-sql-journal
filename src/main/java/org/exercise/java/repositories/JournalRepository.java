package org.exercise.java.repositories;

import org.exercise.java.db.Database;

import java.sql.*;
import java.util.Scanner;

public class JournalRepository {
    Database db;

    public JournalRepository (Database db) {
        this.db = db;
    }

    public void insert(String title, String content) throws SQLException {
        String sql = "INSERT INTO journal_entries (title, content) VALUES (" + title +", " + content +")";
        try (ResultSet rs = this.db.executeQuery(sql)) {
            int size = rs.getFetchSize();
            System.out.println("test size " + size);
            if (size > 0) {
                System.out.println("A new journal entry was inserted successfully!");
            }
        }
    }

    public void getAll() throws SQLException {
        String sql = "SELECT id, title, LENGTH(content) AS char_count FROM journal_entries";
        try (ResultSet rs = this.db.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int charCount = rs.getInt("char_count");
            }
        }
    }

    public void getById(int id) throws SQLException {
        String sql = "SELECT id, title, content FROM journal_entries WHERE id = " + id;
        try (ResultSet rs = this.db.executeQuery(sql)) {
            if (rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");

            } else {
                System.out.println("Journal entry with ID " + id + " not found.");
            }
        }
    }
}
