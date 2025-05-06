package attraction;

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
import attraction.AttractionDAOImpl;

@Path("/attractions")
public class AttractionController {
    private GeneralDAO<AttractionClass> attractionDAO = new AttractionDAOImpl();

    /**
     * Gets all the attractions
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAttractions() {
        ArrayList<AttractionClass> attractions = attractionDAO.getAll();

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
        AttractionClass attraction = attractionDAO.getById(id);

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
        AttractionClass new_attraction = new AttractionClass(
                GeneralDAO.unused_id(AttractionClass.getTblName()),
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
        // AttractionClass new_attraction = new AttractionClass(
        //         GeneralDAO.unused_id("attractions"), 
        //         attractionName, 
        //         typeAttraction.valueOf(attractionType),
        //         Double.parseDouble(sizeAlone.replace(',', '.')), // on enlève les "," française au cas où et on parse en Double
        //         Double.parseDouble(sizeWithAdult.replace(',', '.')),
        //         new ArrayList<String>(openingHours),
        //         new ArrayList<String>(closingHours));
        //
        // attractionDAO.add(new_attraction);
        // System.out.println("Nouvelle attraction rajoutée !");
        //
        //TODO: this
    }

    /**
     * Deletes an existing attraction
     */
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id) {
        GeneralDAO.delete(AttractionClass.getTblName(), id);
    }
}
