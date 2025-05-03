package restaurants;

import java.util.ArrayList;

public interface RestaurantDAO {
    public ArrayList<Restaurant> findByAll();
    public ArrayList<Restaurant> findByAllFinal();
    public void addingRestaurant(Restaurant new_restaurant);
}
