package shows;

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

import utils.GeneralDAO;
import shows.ShowDAOImpl;
import time.EventTime;
import time.Hour;
import time.HourRange;

@Path("/shows")
public class ShowController {
    private GeneralDAO<Show> showDAO = new ShowDAOImpl();

    /**
     * Gets all the shows
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getShows() {
        ArrayList<Show> shows = showDAO.getAll();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(shows);
        return json;
    }

    /**
     * Get the show of id `id`
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getShowById(@PathParam("id") int id) {
        Show show = showDAO.getById(id);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(show);
        return json;
    }

    /**
     * Creates a new show
     */
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public void createShow(
        @FormParam("title") String title,
        @FormParam("day") String day,
        @FormParam("beg_time") String begTime,
        @FormParam("end_time") String endTime,
        @FormParam("location") String location,
        @FormParam("characters") List<String> characters
    ) {
        System.out.println("=======================================");
        System.out.println(title + " " + day + " " + " " + begTime);
        System.out.println("=======================================");
        // Create the list of characters
        ArrayList<Character> charactersList = new ArrayList<>();
        for (String charact : characters) {
            charactersList.add(new Character(
                GeneralDAO.unused_id(Character.getTblName()),
                charact
            ));
        }

        // Create schedule
        HourRange hr = new HourRange(new Hour(begTime), new Hour(endTime));
        EventTime schedule = new EventTime(day, hr);

        // Create the show
        Show new_show = new Show(
            GeneralDAO.unused_id(Show.getTblName()),
            title,
            schedule,
            location,
            charactersList
        );

        showDAO.add(new_show);
        System.out.println("New show added !");
    }

    /**
     * Edits an existing show
     */
    @PUT
    @Path("/{id}")
    @Consumes("application/x-www-form-urlencoded")
    public void editShow(
        @FormParam("title") String title,
        @FormParam("day") String day,
        @FormParam("beg_time") String begTime,
        @FormParam("end_time") String endTime,
        @FormParam("location") String location,
        @FormParam("characters") List<String> characters
    ) {
        // Create the list of characters
        ArrayList<Character> charactersList = new ArrayList<>();
        for (String charact : characters) {
            charactersList.add(new Character(
                GeneralDAO.unused_id(Character.getTblName()),
                charact
            ));
        }

        // Create schedule
        HourRange hr = new HourRange(new Hour(begTime), new Hour(endTime));
        EventTime schedule = new EventTime(day, hr);

        // Create the show
        Show new_show = new Show(
            GeneralDAO.unused_id(Show.getTblName()),
            title,
            schedule,
            location,
            charactersList
        );

        showDAO.edit(new_show);
        System.out.println("Show edited !");
    }

    /**
     * Deletes an existing show
     */
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id) {
        GeneralDAO.delete(Show.getTblName(), id);
    }
}
