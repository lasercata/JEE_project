package restaurants;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import time.Schedule;
import utils.GeneralDAO;

@Path("/restaurant-management")
public class RestaurantController {
    private GeneralDAO<Restaurant> restaurantDAO = new RestaurantDAOImpl();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/restaurants")
    public String getRestaurants() {
        ArrayList<Restaurant> restaurants = restaurantDAO.getAll();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(restaurants);
        return json;
    }

    @POST
    @Path("/create-restaurants")
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
            0, //TODO
            restaurantName,
            Cuisine.valueOf(cuisineType),
            s,
            nbSeats
        );

        restaurantDAO.add(new_restaurant);
        System.out.println("New restaurant added !");
    }
}
