package shows;

import time.EventTime;
import time.HourRange;
import time.Hour;

import java.util.ArrayList;

public class Show {
    private int id;
    private String titre;
    private EventTime schedule; 
    private int duree; // in minutes
    private String lieu;
    private ArrayList<Character> personnages;

    public Show(int id, String titre, EventTime schedule, String lieu, ArrayList<Character> personnages) {
        this.id = id;
        this.titre = titre;
        this.schedule = schedule;
        this.lieu = lieu;
        this.personnages = personnages;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public EventTime getSchedule() {
        return this.schedule;
    }

    public void setSchedule(EventTime schedule) {
        this.schedule = schedule;
    }

    public String getLieu() {
        return this.lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public ArrayList<Character> getPersonnages() {
        return this.personnages;
    }

    public void setPersonnages(ArrayList<Character> personnages) {
        this.personnages = personnages;
    }

    /**
     * Calculates a string containing the SQL fields.
     */
    public static String sqlFields() {
        return "id, titre, jour, heureDebut, heureFin, lieu";
    }

    /**
     * Returns the name of the associated sql table
     */
    public static String getTblName() {
        return "shows";
    }

    /**
     * Calculates a string containing all the attributes of the class, separated with commas.
     * It's useful to add the class into the SQL database.
     */
    public String toSQL() {
        return id + ", '" + titre + "', '" + this.schedule.getDay() + "', " + this.schedule.getTime().getOpening() + ", " + this.schedule.getTime().getEnding() + ", '" + lieu + "'";
    }

    @Override
    public String toString() {
        String str = "Le spectacle " + this.titre + " aura lieu : \n";
        str += "Le " + this.schedule.getDay() + " de " + this.schedule.getTime().getOpening() + " à " + this.schedule.getTime().getEnding() + "\n";
        str += "Il se tiendra à " + this.lieu + "\n";
        str += "Les personnages sont ";

        for (Character personnage : this.personnages) {
            str += personnage.getName() + ", ";
        }

        return str;
    }

    // =================== Other methods ====================

    public void addCharacter(Character personnage) {
        this.personnages.add(personnage);
    }
}
