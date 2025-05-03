package restaurants;

import java.util.ArrayList;
import java.util.List;

import time.Schedule;
import utils.DBRequests;

public class RestaurantManagement {
    private List<Restaurant> restaurants;

    public RestaurantManagement() {
        this.restaurants = new ArrayList<Restaurant>();
    }

    public List<Restaurant> getRestaurants() {
        return this.restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public Restaurant createRestaurant(String name, Cuisine cuisine, Schedule schedule, int nbOfSeats) {
        return new Restaurant(DBRequests.unused_id("restaurants"), name, cuisine, schedule, nbOfSeats);
    }

    public void addRestaurant(Restaurant r) {
        this.getRestaurants().add(r);
    }

    public void delRestaurant(int index) {
        this.getRestaurants().remove(index);
    }

    @Override
    public String toString() {
        String res = "";
        for(int i = 0; i < this.getRestaurants().size(); i++) {
            res += String.format("%d) %s", i+1, this.getRestaurants().get(i).toString());
        }
        return res;
    }
}