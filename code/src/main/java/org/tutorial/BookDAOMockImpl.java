package org.tutorial;

import java.util.List;
// import java.util.ArrayList;

import org.tutorial.BookDAO;
import org.tutorial.Book;

public class BookDAOMockImpl implements BookDAO {
    @Override
    public List<Book> findByAll() {
        // List<Book> ret = new ArrayList<Book>();
        //
        // ret.add(new Book("title1", "author1"));
        // ret.add(new Book("title2", "author2"));
        // ret.add(new Book("title3", "author3"));

        List<Book> ret = List.of(
            new Book("title1", "author1"),
            new Book("title2", "author2"),
            new Book("title3", "author3")
        );

        return ret;
    }
}
