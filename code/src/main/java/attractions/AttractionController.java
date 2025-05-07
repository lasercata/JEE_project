package attractions;

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
import attractions.AttractionDAOImpl;

@Path("/attractions")
public class AttractionController {
    private GeneralDAO<Attraction> attractionDAO = new AttractionDAOImpl();

    /**
     * Gets all the attractions
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAttractions() {
        ArrayList<Attraction> attractions = attractionDAO.getAll();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(attractions);
        return json;
    }

    /**
     * Get the attraction of id `id`
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAttractionById(@PathParam("id") int id) {
        Attraction attraction = attractionDAO.getById(id);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(attraction);
        return json;
    }

    /**
     * Creates a new attraction
     */
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public void createAttraction(
        @FormParam("attraction_name") String attractionName,
        @FormParam("attraction_type") String attractionType,
        @FormParam("size_alone") String sizeAlone,
        @FormParam("size_with_adult") String sizeWithAdult,
        @FormParam("opening_hours") List<String> openingHours,
        @FormParam("closing_hours") List<String> closingHours
    ) {
        Attraction new_attraction = new Attraction(
                GeneralDAO.unused_id(Attraction.getTblName()),
                attractionName, 
                typeAttraction.valueOf(attractionType),
                Double.parseDouble(sizeAlone.replace(',', '.')), // on enlève les "," française au cas où et on parse en Double
                Double.parseDouble(sizeWithAdult.replace(',', '.')),
                new ArrayList<String>(openingHours),
                new ArrayList<String>(closingHours));

        attractionDAO.add(new_attraction);
        System.out.println("Nouvelle attraction rajoutée !");
    }

    /**
     * Edits an existing attraction
     */
    @PUT
    @Path("/{id}")
    @Consumes("application/x-www-form-urlencoded")
    public void editAttraction(
        @PathParam("id") int id,
        @FormParam("attraction_name") String attractionName,
        @FormParam("attraction_type") String attractionType,
        @FormParam("size_alone") String sizeAlone,
        @FormParam("size_with_adult") String sizeWithAdult,
        @FormParam("opening_hours") List<String> openingHours,
        @FormParam("closing_hours") List<String> closingHours
    ) {
        Attraction new_attraction = new Attraction(
                GeneralDAO.unused_id(Attraction.getTblName()),
                attractionName, 
                typeAttraction.valueOf(attractionType),
                Double.parseDouble(sizeAlone.replace(',', '.')), // on enlève les "," française au cas où et on parse en Double
                Double.parseDouble(sizeWithAdult.replace(',', '.')),
                new ArrayList<String>(openingHours),
                new ArrayList<String>(closingHours));

        attractionDAO.edit(new_attraction);
        System.out.println("Attraction edited !");
    }

    /**
     * Deletes an existing attraction
     */
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id) {
        GeneralDAO.delete(Attraction.getTblName(), id);
    }
}
