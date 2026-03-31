package com.library.system;

import java.io.*;
import java.util.*;

public class FileManager {

    private static final String BOOKS_FILE      = "books.csv";
    private static final String MEMBERS_FILE    = "members.csv";
    private static final String BORROWINGS_FILE = "borrowings.csv";

    public static void saveBooks(List<Book> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKS_FILE))) {
            for (Book b : books) {
                writer.write(b.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        File file = new File(BOOKS_FILE);
        if (!file.exists()) return books;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) books.add(Book.fromCSV(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
        return books;
    }

    public static void saveMembers(List<Member> members) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MEMBERS_FILE))) {
            for (Member m : members) {
                writer.write(m.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving members: " + e.getMessage());
        }
    }

    public static List<Member> loadMembers() {
        List<Member> members = new ArrayList<>();
        File file = new File(MEMBERS_FILE);
        if (!file.exists()) return members;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] p = line.split(",");
                    if (p.length == 4 && p[0].equals("MEMBER")) {
                        members.add(new Member(p[1], p[2], p[3]));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading members: " + e.getMessage());
        }
        return members;
    }

    public static void saveBorrowings(List<Member> members) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BORROWINGS_FILE))) {
            for (Member m : members) {
                for (String book : m.getBorrowedBooks()) {
                    writer.write(m.getId() + "," + book);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving borrowings: " + e.getMessage());
        }
    }

    public static void loadBorrowings(List<Member> members) {
        File file = new File(BORROWINGS_FILE);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] p = line.split(",", 2);
                    if (p.length == 2) {
                        for (Member m : members) {
                            if (m.getId().equals(p[0])) {
                                m.getBorrowedBooks().add(p[1]);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading borrowings: " + e.getMessage());
        }
    }
}