package com.library.system;

import java.util.List;

public class LibraryManager {

    private List<Book>   books;
    private List<Member> members;

    public LibraryManager() {
        books   = FileManager.loadBooks();
        members = FileManager.loadMembers();
        FileManager.loadBorrowings(members);
    }

    public void addBook(Book book) {
        for (Book b : books) {
            if (b.getBookId().equalsIgnoreCase(book.getBookId())) {
                System.out.println("A book with that ID already exists.");
                return;
            }
        }
        books.add(book);
        FileManager.saveBooks(books);
        System.out.println("Book added successfully.");
    }

    public void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("\n===== ALL BOOKS =====");
            for (Book b : books) System.out.println(b);
        }
    }

    public void viewAvailableBooks() {
        System.out.println("\n===== AVAILABLE BOOKS =====");
        boolean found = false;
        for (Book b : books) {
            if (b.isAvailable()) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) System.out.println("No books are currently available.");
    }

    public boolean updateBook(String id, String newTitle, String newAuthor, String newCategory) {
        for (Book b : books) {
            if (b.getBookId().equalsIgnoreCase(id)) {
                b.setTitle(newTitle);
                b.setAuthor(newAuthor);
                b.setCategory(newCategory);
                FileManager.saveBooks(books);
                System.out.println("Book updated successfully.");
                return true;
            }
        }
        System.out.println("Book not found.");
        return false;
    }

    public boolean deleteBook(String id) {
        Book toRemove = null;
        for (Book b : books) {
            if (b.getBookId().equalsIgnoreCase(id)) {
                toRemove = b;
                break;
            }
        }
        if (toRemove != null) {
            books.remove(toRemove);
            FileManager.saveBooks(books);
            System.out.println("Book deleted successfully.");
            return true;
        }
        System.out.println("Book not found.");
        return false;
    }

    public Book findBook(String input) {
        for (Book b : books) {
            if (b.getBookId().equalsIgnoreCase(input) ||
                    b.getTitle().equalsIgnoreCase(input)) {
                return b;
            }
        }
        return null;
    }

    public boolean registerMember(String id, String name, String password) {
        for (Member m : members) {
            if (m.getId().equalsIgnoreCase(id)) {
                System.out.println("Member ID already exists.");
                return false;
            }
        }
        members.add(new Member(id, name, password));
        FileManager.saveMembers(members);
        System.out.println("Registration successful!");
        return true;
    }

    public Member loginMember(String id, String password) {
        for (Member m : members) {
            if (m.login(id, password)) return m;
        }
        return null;
    }

    public void viewAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
        } else {
            System.out.println("\n===== ALL MEMBERS =====");
            for (Member m : members) {
                System.out.println("  [" + m.getId() + "] " + m.getName());
                List<String> borrowed = m.getBorrowedBooks();
                if (borrowed.isEmpty()) {
                    System.out.println("    Borrowed: None");
                } else {
                    System.out.println("    Borrowed:");
                    for (String b : borrowed) System.out.println("      - " + b);
                }
            }
        }
    }

    public void borrowBook(Member member, String input) {
        Book book = findBook(input);
        if (book == null) {
            System.out.println("Book not found.");
        } else if (!book.isAvailable()) {
            System.out.println("Sorry, this book is currently borrowed.");
        } else {
            book.setAvailable(false);
            member.borrowBook(book.getTitle());
            FileManager.saveBooks(books);
            FileManager.saveBorrowings(members);
        }
    }

    public void returnBook(Member member, String input) {
        Book book = findBook(input);
        if (book == null) {
            System.out.println("Book not found.");
        } else {
            boolean returned = member.returnBook(book.getTitle());
            if (returned) {
                book.setAvailable(true);
                FileManager.saveBooks(books);
                FileManager.saveBorrowings(members);
            }
        }
    }

    public void saveBorrowings() { FileManager.saveBorrowings(members); }
    public List<Book>   getBooks()   { return books; }
    public List<Member> getMembers() { return members; }
}