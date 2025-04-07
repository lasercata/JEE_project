package org.tutorial;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

import org.tutorial.BookDAO;
import org.tutorial.Book;
import org.tutorial.DBManager;


public class BookDAOImpl implements BookDAO {
    @Override
    public List<Book> findByAll() {
        List<Book> ret = new ArrayList<Book>();

        Connection c = DBManager.getInstance().getConnection();
        Statement s = null;
        ResultSet rs = null;

        try {
            s = c.createStatement();
            rs = s.executeQuery("select * from books");

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");

                ret.add(new Book(id, title, author));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            DBManager.getInstance().cleanup(c, s, rs);
        }


        return ret;
    }
}

