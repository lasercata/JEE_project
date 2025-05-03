package characters;

import time.EventTime;
import time.HourRange;
import time.Hour;

import java.util.ArrayList;

public class Show {
    private String titre;
    private ArrayList<EventTime> horaires;
    private String lieu;
    private ArrayList<Character> personnages;

    public Show(String titre, String jour, ArrayList<EventTime> horaires, String lieu, ArrayList<Character> personnages) {
        this.titre = titre;
        this.horaires = horaires;
        this.lieu = lieu;
        this.personnages = personnages;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public ArrayList<EventTime> getHoraires() {
        return this.horaires;
    }

    public void setHoraires(ArrayList<EventTime> horaires) {
        this.horaires = horaires;
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

    @Override
    public String toString() {
        String str = "Le spectacle " + this.titre + " aura lieu : \n";
        for (EventTime eventTime : this.horaires) {
            str += "\t- " + eventTime.toString() + "\n";
        }
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