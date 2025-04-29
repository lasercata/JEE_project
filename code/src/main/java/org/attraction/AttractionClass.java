package org.attraction;
import java.util.ArrayList;

import time.HourRange;
import time.Schedule;

public class AttractionClass{
	private int id;
	private String name;
	private typeAttraction type; 
	private double sizeAlone;
	private double sizeWithAdult;
	private Schedule schedule;
	
	public AttractionClass(int id, String name, typeAttraction type, double sizeAlone, double sizeWithAdult, ArrayList<String> openingHours, ArrayList<String> closingHours){
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
	
	// This method will send a String containing the attributes separated with ','
	// It's useful for SQL adding method
	public String toSQL(){
		return id+",'"+name+"','"+type+"',"+sizeAlone+","+sizeWithAdult+","+schedule.toSQL();
	}

	@Override
	public String toString() {
		return "AttractionClass [id=" + id + ", name=" + name + ", type=" + type + ", sizeAlone=" + sizeAlone
				+ ", sizeWithAdult=" + sizeWithAdult + schedule.toString()+ "]";
	}
	
	
	
}