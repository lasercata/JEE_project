package org.attraction;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/attraction-management")
public class AttractionController {
	private AttractionDAO attractionDAO = new AttractionDAOImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/attractions")
	public String getAttractions() {
		 ArrayList<AttractionClass> attractions = attractionDAO.findByAll();
		
		 GsonBuilder builder = new GsonBuilder();
		 Gson gson = builder.create();
		 String json = gson.toJson(attractions);
		 return json;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/hello")
	public String hello() {
		return "Hello World!";
	}
}
