package org.attraction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AttractionClass{
	private int id;
	private String name;
	private typeAttraction type; 
	private double sizeAlone;
	private double sizeWithAdult;
	
	protected static final SimpleDateFormat heureFormat = new SimpleDateFormat("hh:mm");
	
	private ArrayList<Date> openingHours;
	private ArrayList<Date> closingHours;
	
	public AttractionClass(int id, String name, typeAttraction type, double sizeAlone, double sizeWithAdult, ArrayList<String> openingHours, ArrayList<String> closingHours){
		this.id = id;
		this.name = name;
		this.type = type;
		this.sizeAlone = sizeAlone;
		this.sizeWithAdult = sizeWithAdult;
		try {
			for (String heureO : openingHours){
				this.openingHours.add(heureFormat.parse(heureO));
			}
			for (String heureF : closingHours) {
				this.closingHours.add(heureFormat.parse(heureF));
			}
		} catch (Exception e){
			  e.toString();
		}
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

	public ArrayList<Date> getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(ArrayList<Date> openingHours) {
		this.openingHours = openingHours;
	}

	public ArrayList<Date> getClosingHours() {
		return closingHours;
	}

	public void setClosingHours(ArrayList<Date> closingHours) {
		this.closingHours = closingHours;
	}

	public static SimpleDateFormat getHeureformat() {
		return heureFormat;
	}
	
	
}