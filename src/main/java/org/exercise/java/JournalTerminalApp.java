package org.exercise.java;

import java.sql.*;
import java.util.Scanner;

public class JournalTerminalApp {
    private static final String URL = "jdbc:postgresql://localhost:5432/journal";
    private static final String USER = "root";
    private static final String PASSWORD = "postgresdci";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
             Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            while (true) {
                System.out.println("Press 1 for new journal entry");
                System.out.println("Press 2 to see a list of all entries");
                System.out.println("Press 3 to show the details of an entry by ID");
                System.out.println("Press 0 to exit");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addJournalEntry(scanner, conn);
                        break;
                    case 2:
                        listJournalEntries(conn);
                        break;
                    case 3:
                        showJournalEntryDetails(scanner, conn);
                        break;
                    case 0:
                        System.out.println("Exiting program. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addJournalEntry(Scanner scanner, Connection conn) throws SQLException {
        System.out.println("Enter title:");
        String title = scanner.nextLine();
        System.out.println("Enter content:");
        String content = scanner.nextLine();

        String sql = "INSERT INTO journal_entries (title, content) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new journal entry was inserted successfully!");
            }
        }
    }

    private static void listJournalEntries(Connection conn) throws SQLException {
        String sql = "SELECT id, title, LENGTH(content) AS char_count FROM journal_entries";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.printf("%-5s %-30s %-15s%n", "ID", "Title", "Character Count");
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int charCount = rs.getInt("char_count");
                System.out.printf("%-5d %-30s %-15d%n", id, title, charCount);
            }
        }
    }

    private static void showJournalEntryDetails(Scanner scanner, Connection conn) throws SQLException {
        System.out.println("Enter the ID of the journal entry:");
        int id = Integer.parseInt(scanner.nextLine());

        String sql = "SELECT id, title, content FROM journal_entries WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    System.out.println("Title: " + title);
                    System.out.println("Content: " + content);
                } else {
                    System.out.println("Journal entry with ID " + id + " not found.");
                }
            }
        }
    }
}