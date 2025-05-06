package restaurants;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import time.Schedule;
import utils.GeneralDAO;

@Path("/restaurants")
public class RestaurantController {
    private GeneralDAO<Restaurant> restaurantDAO = new RestaurantDAOImpl();

    /**
     * Gets all the restaurants
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRestaurants() {
        ArrayList<Restaurant> restaurants = restaurantDAO.getAll();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(restaurants);
        return json;
    }

    /**
     * Gets the restaurant of if `id`
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRestaurantsById(@PathParam("id") int id) {
        Restaurant restaurant = restaurantDAO.getById(id);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(restaurant);
        return json;
    }

    /**
     * Creates a new restaurant
     */
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public void createRestaurant(
        @FormParam("restaurant_name") String restaurantName,
        @FormParam("cuisine_type") String cuisineType,
        @FormParam("nb_seats") String nbSeatsString,
        @FormParam("opening_hours") List<String> openingHours,
        @FormParam("closing_hours") List<String> closingHours
    ) {
        // Make Schedule
        int nbSeats = Integer.parseInt(nbSeatsString);
        Schedule s = new Schedule((ArrayList<String>) openingHours, (ArrayList<String>) closingHours);

        Restaurant new_restaurant = new Restaurant(
            GeneralDAO.unused_id(Restaurant.getTblName()),
            restaurantName,
            Cuisine.valueOf(cuisineType),
            s,
            nbSeats
        );

        restaurantDAO.add(new_restaurant);
        System.out.println("New restaurant added !");
    }

    /**
     * Edits an existing restaurant
     */
    @PUT
    @Consumes("application/x-www-form-urlencoded")
    public void editRestaurant(
        @FormParam("restaurant_name") String restaurantName,
        @FormParam("cuisine_type") String cuisineType,
        @FormParam("nb_seats") String nbSeatsString,
        @FormParam("opening_hours") List<String> openingHours,
        @FormParam("closing_hours") List<String> closingHours
    ) {
        // Make Schedule
        int nbSeats = Integer.parseInt(nbSeatsString);
        Schedule s = new Schedule((ArrayList<String>) openingHours, (ArrayList<String>) closingHours);

        Restaurant new_restaurant = new Restaurant(
            GeneralDAO.unused_id(Restaurant.getTblName()),
            restaurantName,
            Cuisine.valueOf(cuisineType),
            s,
            nbSeats
        );

        restaurantDAO.edit(new_restaurant);
        System.out.println("Restaurant edited !");
    }

    /**
     * Deletes an existing restaurant
     */
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id) {
        GeneralDAO.delete(Restaurant.getTblName(), id);
    }
}
