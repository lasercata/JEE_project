package attraction;

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
import utils.DBRequests;

@Path("/attraction-management")
public class AttractionController {
	private AttractionDAO attractionDAO = new AttractionDAOImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/attractions")
	public String getAttractions() {
		ArrayList<AttractionClass> attractions = DBRequests.getAllAttractions();
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(attractions);
		return json;
	}
	
	@POST
	@Path("/createattraction")
	@Consumes("application/x-www-form-urlencoded")
	public void createAttraction(@FormParam("attraction_name") String attractionName,
			@FormParam("attraction_type") String attractionType,
			@FormParam("size_alone") String sizeAlone,
			@FormParam("size_with_adult") String sizeWithAdult,
			@FormParam("opening_hours") List<String> openingHours,
			@FormParam("closing_hours") List<String> closingHours){
		AttractionClass new_attraction = new AttractionClass(DBRequests.unused_id("attractions"), 
				attractionName, 
				typeAttraction.valueOf(attractionType),
				Double.parseDouble(sizeAlone.replace(',', '.')), // on enlève les "," française au cas où et on parse en Double
				Double.parseDouble(sizeWithAdult.replace(',', '.')),
				new ArrayList<String>(openingHours),
				new ArrayList<String>(closingHours));
		
		System.out.println("TA MERE");
		DBRequests.addingAttraction(new_attraction);
		System.out.println("Nouvelle attraction rajoutée !");
	}
}
