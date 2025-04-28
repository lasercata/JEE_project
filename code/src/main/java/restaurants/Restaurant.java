package restaurants;

import time.Schedule;

public class Restaurant {
    private String name;
    private Cuisine cuisine;
    private Schedule schedule;
    private int nbOfSeats;

    public Restaurant(String name, Cuisine cuisine, Schedule schedule, int nbOfSeats) {
        this.name = name;
        this.cuisine = cuisine;
        this.schedule = schedule;
        this.nbOfSeats = nbOfSeats;
    }

    public String getName() {
        return this.name;
    }

    public Cuisine getCuisine() {
        return this.cuisine;
    }

    public Schedule getSchedule() {
        return this.schedule;
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

    @Override
    public String toString() {
        return String.format("Le réstaurant %s spécialisé en cuisine %s dispose de %d places assises et est ouvert : \n%s", this.getName(), this.getCuisine().toString(), this.getNbOfSeats(), this.getSchedule().toString());
    }
}