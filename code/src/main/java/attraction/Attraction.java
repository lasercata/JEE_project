package attraction;

import java.util.ArrayList;

import time.HourRange;
import time.Schedule;

public class Attraction {
	private int id;
	private String name;
	private typeAttraction type; 
	private double sizeAlone;
	private double sizeWithAdult;
	private Schedule schedule;
	
	//TODO: remove id from the constructor
	public Attraction(int id, String name, typeAttraction type, double sizeAlone, double sizeWithAdult, ArrayList<String> openingHours, ArrayList<String> closingHours){
		this.id = id;
		this.name = name;
		this.type = type;
		this.sizeAlone = sizeAlone;
		this.sizeWithAdult = sizeWithAdult;
		this.schedule = new Schedule(openingHours, closingHours);
	}

	public int getID(){
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public typeAttraction getType() {
		return type;
	}

	public void setType(typeAttraction type) {
		this.type = type;
	}

	public double getSizeAlone() {
		return sizeAlone;
	}

	public void setSizeAlone(double sizeAlone) {
		this.sizeAlone = sizeAlone;
	}

	public double getSizeWithAdult() {
		return sizeWithAdult;
	}

	public void setSizeWithAdult(double sizeWithAdult) {
		this.sizeWithAdult = sizeWithAdult;
	}

	public void setSchedule(HourRange[] schedule) {
		this.schedule.setSchedule(schedule);
	}
	
	public HourRange[] getSchedule() {
		return schedule.getSchedule();
	}
	
	/**
	 * Calculates a string containing the SQL fields.
	 */
	public static String sqlFields() {
		return "id, name, type, sizealone, sizewithadult, moOP, moCL, tuOP, tuCL, weOP, weCL, thOP, thCL, frOP, frCL, saOP, saCL, suOP, suCL";
	}

    /**
     * Returns the name of the associated sql table
     */
    public static String getTblName() {
        return "attractions";
    }
	
	/**
	 * Calculates a string containing all the attributes of the class, separated with commas.
	 * It's useful to add the class into the SQL database.
	 */
	public String toSQL() {
		return id + ", '" + name + "', '" + type + "', " + sizeAlone + ", " + sizeWithAdult + ", " + schedule.toSQL();
	}

	@Override
	public String toString() {
		return "Attraction [id=" + id + ", name=" + name + ", type=" + type + ", sizeAlone=" + sizeAlone
				+ ", sizeWithAdult=" + sizeWithAdult + schedule.toString()+ "]";
	}
	
	
	
}
