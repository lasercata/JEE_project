package org.tutorial;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.tutorial.BookDAOImpl;

@Path("/book-management")
public class BookController {
    // private BookDAO bookDAO = new BookDAOMockImpl();
    private BookDAO bookDAO = new BookDAOImpl();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hello")
    public String hello() {
        return "Hello word!";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/books")
    public String getBooks() {
        List<Book> books = bookDAO.findByAll();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(books);

        return json;
    }
}
