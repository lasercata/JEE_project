package restaurants;

import java.util.ArrayList;

import time.HourRange;
import time.Schedule;

public class Restaurant {
	private int id;
    private String name;
    private Cuisine cuisine;
    private Schedule schedule;
    private int nbOfSeats;

    public Restaurant(int id, String name, Cuisine cuisine, ArrayList<String> openingHours, ArrayList<String> closingHours, int nbOfSeats) {
        this.id = id;
    	this.name = name;
        this.cuisine = cuisine;
        this.schedule = new Schedule(openingHours, closingHours);
        this.nbOfSeats = nbOfSeats;
    }
    
    public Restaurant(int id, String name, Cuisine cuisine, Schedule schedule, int nbOfSeats) {
        this.id = id;
    	this.name = name;
        this.cuisine = cuisine;
        this.schedule = schedule;
        this.nbOfSeats = nbOfSeats;
    }

    public int getID() {
    	return this.id;
    }
    
    public String getName() {
        return this.name;
    }

    public Cuisine getCuisine() {
        return this.cuisine;
    }

    public HourRange[] getSchedule() {
		return schedule.getSchedule();
	}

    public int getNbOfSeats() {
        return this.nbOfSeats;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setNbOfSeats(int nbOfSeats) {
        this.nbOfSeats = nbOfSeats;
    }
    
    /**
     * Calculates a string containing the SQL fields.
     */
    public static String sqlFields() {
        return "id, name, cuisine, nbofseats, moOP, moCL, tuOP, tuCL, weOP, weCL, thOP, thCL, frOP, frCL, saOP, saCL, suOP, suCL";
    }

    /**
     * Returns the name of the associated sql table
     */
    public static String getTblName() {
        return "restaurants";
    }
 	
	/**
	 * Calculates a string containing all the attributes of the class, separated with commas.
	 * It's useful to add the class into the SQL database.
	 */
 	public String toSQL(){
 		return id + ",'" + name + "','" + cuisine + "'," + nbOfSeats + "," + schedule.toSQL();
 	}

    @Override
    public String toString() {
        return String.format("Le restaurant %s spécialisé en cuisine %s dispose de %d places assises et est ouvert : \n%s", this.getName(), this.getCuisine().toString(), this.getNbOfSeats(), this.getSchedule().toString());
    }
}
