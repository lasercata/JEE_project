package org.tutorial;

public class Book {
    private int id;
    private String title;
    private String author;

    private static int counter = 0;

    Book() {
        this.id = counter;
        counter++;
    }

    Book(String title, String author) {
        this();

        this.title = title;
        this.author = author;
    }

    Book(int id, String title, String author) {
        this();

        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
}

