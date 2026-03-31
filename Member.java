package com.library.system;

import java.util.ArrayList;
import java.util.List;

public class Member extends com.library.system.Person {

    private List<String> borrowedBooks = new ArrayList<>();

    public Member(String id, String name, String password) {
        super(id, name, password, "MEMBER");
    }

    public void borrowBook(String bookTitle) {
        borrowedBooks.add(bookTitle);
        System.out.println("You have borrowed " + bookTitle + " successfully.");
    }

    public boolean returnBook(String bookTitle) {
        for (String b : borrowedBooks) {
            if (b.equalsIgnoreCase(bookTitle)) {
                borrowedBooks.remove(b);
                System.out.println("You have returned " + bookTitle + " successfully.");
                return true;
            }
        }
        System.out.println("This book is not in your borrowed list.");
        return false;
    }

    public void viewBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("You have not borrowed any books.");
        } else {
            System.out.println("Your Borrowed Books:");
            for (int i = 0; i < borrowedBooks.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + borrowedBooks.get(i));
            }
        }
    }

    public List<String> getBorrowedBooks() { return borrowedBooks; }
    public void setBorrowedBooks(List<String> books) { this.borrowedBooks = books; }
}