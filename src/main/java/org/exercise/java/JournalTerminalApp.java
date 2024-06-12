package org.exercise.java;

import org.exercise.java.db.Database;
import org.exercise.java.repositories.JournalRepository;

import java.sql.*;
import java.util.Scanner;

public class JournalTerminalApp {
    private static final String URL = "jdbc:postgresql://localhost:5432/journal";
    private static final String USER = "root";
    private static final String PASSWORD = "postgresdci";

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            Database db = new Database(URL, USER, PASSWORD);
            JournalRepository journalRepository = new JournalRepository(db);

            while (true) {
                System.out.println("Press 1 for new journal entry");
                System.out.println("Press 2 to see a list of all entries");
                System.out.println("Press 3 to show the details of an entry by ID");
                System.out.println("Press 0 to exit");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addJournalEntry(scanner, journalRepository);
                        break;
                    case 2:
                        listJournalEntries(journalRepository);
                        break;
                    case 3:
                        showJournalEntryDetails(scanner, journalRepository);
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

    private static void addJournalEntry(Scanner scanner, JournalRepository repo) throws SQLException {
        System.out.println("Enter title:");
        String title = scanner.nextLine();
        System.out.println("Enter content:");
        String content = scanner.nextLine();

    }

    private static void listJournalEntries(JournalRepository repo) throws SQLException {
        System.out.printf("%-5s %-30s %-15s%n", "ID", "Title", "Character Count");
        // System.out.printf("%-5d %-30s %-15d%n", id, title, charCount);
    }

    private static void showJournalEntryDetails(Scanner scanner, JournalRepository repo) throws SQLException {
        System.out.println("Enter the ID of the journal entry:");
        int id = Integer.parseInt(scanner.nextLine());
        // System.out.println("Title: " + title);
        // System.out.println("Content: " + content);
    }
}