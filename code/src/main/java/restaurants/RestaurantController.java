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

@Path("/restaurant-management")
public class RestaurantController {
    private RestaurantDAO restaurantDAO = new RestaurantDAOImpl();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/restaurants")
    public String getAttractions() {
        ArrayList<Restaurant> restaurants = restaurantDAO.findByAll();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(restaurants);
        return json;
    }

    @POST
    @Path("/create-restaurants")
    @Consumes("application/x-www-form-urlencoded")
    public void createAttraction(
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
            restaurantName,
            Cuisine.valueOf(cuisineType),
            s,
            nbSeats
        );

        // TODO: call the part that saves in the DB.

        System.out.println("New restaurant added !");
    }
}
