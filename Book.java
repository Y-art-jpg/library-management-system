package com.library.system;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private String category;
    private boolean isAvailable;

    public Book(String bookId, String title, String author, String category, boolean isAvailable) {
        this.bookId      = bookId;
        this.title       = title;
        this.author      = author;
        this.category    = category;
        this.isAvailable = isAvailable;
    }

    public String  getBookId()    { return bookId; }
    public String  getTitle()     { return title; }
    public String  getAuthor()    { return author; }
    public String  getCategory()  { return category; }
    public boolean isAvailable()  { return isAvailable; }

    public void setTitle(String title)          { this.title      = title; }
    public void setAuthor(String author)        { this.author     = author; }
    public void setCategory(String category)    { this.category   = category; }
    public void setAvailable(boolean available) { this.isAvailable = available; }

    public String toCSV() {
        return bookId + "," + title + "," + author + "," + category + "," + isAvailable;
    }

    public static Book fromCSV(String line) {
        String[] p = line.split(",");
        return new Book(p[0], p[1], p[2], p[3], Boolean.parseBoolean(p[4].trim()));
    }

    public String toString() {
        String status = isAvailable ? "Available" : "Borrowed";
        return "  [" + bookId + "] " + title + " by " + author +
                " | Category: " + category + " | Status: " + status;
    }
}