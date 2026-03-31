package com.library.system;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static Scanner        scanner   = new Scanner(System.in);
    static LibraryManager manager   = new LibraryManager();
    static Librarian      librarian = new Librarian("LIB001", "Head Librarian", "lib123");

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("      LIBRARY MANAGEMENT SYSTEM");
        System.out.println("==========================================");

        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Librarian Login");
            System.out.println("2. Member Login");
            System.out.println("3. Member Registration");
            System.out.println("4. Exit");
            System.out.print("Choose: ");

            int choice = readInt();
            if (choice == 1) librarianLogin();
            else if (choice == 2) memberLogin();
            else if (choice == 3) memberRegistration();
            else if (choice == 4) { System.out.println("Goodbye!"); System.exit(0); }
            else System.out.println("Invalid option. Try again.");
        }
    }

    static void librarianLogin() {
        System.out.println("\n--- LIBRARIAN LOGIN ---");
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Password: ");
        String pass = scanner.nextLine();
        if (librarian.login(id, pass)) {
            System.out.println("Welcome, " + librarian.getName() + "!");
            librarianMenu();
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    static void librarianMenu() {
        while (true) {
            System.out.println("\n--- LIBRARIAN MENU ---");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. View All Members and Borrowings");
            System.out.println("6. Logout");
            System.out.print("Choose: ");

            int choice = readInt();
            if (choice == 1) addBook();
            else if (choice == 2) manager.viewAllBooks();
            else if (choice == 3) updateBook();
            else if (choice == 4) deleteBook();
            else if (choice == 5) manager.viewAllMembers();
            else if (choice == 6) { System.out.println("Logged out."); return; }
            else System.out.println("Invalid option.");
        }
    }

    static void addBook() {
        System.out.println("\n--- ADD BOOK ---");
        System.out.print("Book ID: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) { System.out.println("Book ID cannot be empty."); return; }

        System.out.print("Title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) { System.out.println("Title cannot be empty."); return; }

        System.out.print("Author: ");
        String author = scanner.nextLine().trim();
        if (author.isEmpty()) { System.out.println("Author cannot be empty."); return; }

        System.out.print("Category: ");
        String category = scanner.nextLine().trim();
        if (category.isEmpty()) { System.out.println("Category cannot be empty."); return; }

        manager.addBook(new Book(id, title, author, category, true));
    }

    static void updateBook() {
        System.out.println("\n--- UPDATE BOOK ---");
        manager.viewAllBooks();
        System.out.print("Enter Book ID to update: ");
        String id = scanner.nextLine().trim();
        System.out.print("New Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("New Author: ");
        String author = scanner.nextLine().trim();
        System.out.print("New Category: ");
        String category = scanner.nextLine().trim();
        if (title.isEmpty() || author.isEmpty() || category.isEmpty()) {
            System.out.println("Fields cannot be empty. Update cancelled.");
            return;
        }
        manager.updateBook(id, title, author, category);
    }

    static void deleteBook() {
        System.out.println("\n--- DELETE BOOK ---");
        manager.viewAllBooks();
        System.out.print("Enter Book ID to delete: ");
        String id = scanner.nextLine().trim();
        System.out.print("Are you sure? (yes/no): ");
        String confirm = scanner.nextLine().trim();
        if (confirm.equalsIgnoreCase("yes")) {
            manager.deleteBook(id);
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    static void memberRegistration() {
        System.out.println("\n--- MEMBER REGISTRATION ---");
        System.out.print("Choose a Member ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Your Full Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Choose a Password: ");
        String pass = scanner.nextLine().trim();
        if (id.isEmpty() || name.isEmpty() || pass.isEmpty()) {
            System.out.println("All fields are required.");
            return;
        }
        if (pass.length() < 4) {
            System.out.println("Password must be at least 4 characters.");
            return;
        }
        manager.registerMember(id, name, pass);
    }

    static void memberLogin() {
        System.out.println("\n--- MEMBER LOGIN ---");
        System.out.print("Member ID: ");
        String id = scanner.nextLine();
        System.out.print("Password: ");
        String pass = scanner.nextLine();
        Member member = manager.loginMember(id, pass);
        if (member != null) {
            System.out.println("Welcome, " + member.getName() + "!");
            memberMenu(member);
        } else {
            System.out.println("Invalid credentials. Please register first.");
        }
    }

    static void memberMenu(Member member) {
        while (true) {
            System.out.println("\n--- MEMBER MENU ---");
            System.out.println("1. View Available Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. View My Borrowed Books");
            System.out.println("5. Logout");
            System.out.print("Choose: ");

            int choice = readInt();
            if (choice == 1) {
                manager.viewAvailableBooks();
            } else if (choice == 2) {
                manager.viewAvailableBooks();
                System.out.print("Enter Book ID or Title to borrow: ");
                String input = scanner.nextLine().trim();
                manager.borrowBook(member, input);
            } else if (choice == 3) {
                member.viewBorrowedBooks();
                System.out.print("Enter Book Title to return: ");
                String input = scanner.nextLine().trim();
                manager.returnBook(member, input);
            } else if (choice == 4) {
                member.viewBorrowedBooks();
            } else if (choice == 5) {
                manager.saveBorrowings();
                System.out.println("Logged out.");
                return;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    static int readInt() {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}