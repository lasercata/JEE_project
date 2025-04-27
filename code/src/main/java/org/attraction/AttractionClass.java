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
	
	private ArrayList<Date> heuresOuverture;
	private ArrayList<Date> heuresFermeture;
	
	public AttractionClass(int id, String name, typeAttraction type, double sizeAlone, double sizeWithAdult, ArrayList<String> heuresOuverture, ArrayList<String> heuresFermeture){
		this.id = id;
		this.name = name;
		this.type = type;
		this.sizeAlone = sizeAlone;
		this.sizeWithAdult = sizeWithAdult;
		try {
			for (String heureO : heuresOuverture){
				this.heuresOuverture.add(heureFormat.parse(heureO));
			}
			for (String heureF : heuresFermeture) {
				this.heuresFermeture.add(heureFormat.parse(heureF));
			}
		} catch (Exception e){
			  e.toString();
		}
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

	public ArrayList<Date> getHeuresOuverture() {
		return heuresOuverture;
	}

	public void setHeuresOuverture(ArrayList<Date> heuresOuverture) {
		this.heuresOuverture = heuresOuverture;
	}

	public ArrayList<Date> getHeuresFermeture() {
		return heuresFermeture;
	}

	public void setHeuresFermeture(ArrayList<Date> heuresFermeture) {
		this.heuresFermeture = heuresFermeture;
	}

	public static SimpleDateFormat getHeureformat() {
		return heureFormat;
	}
	
	
}