package restaurants;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import time.Hour;
import time.HourRange;
import time.Schedule;
import utils.DBManager;

public class RestaurantDAOImpl implements RestaurantDAO {
    @Override
    public ArrayList<Restaurant> findByAll() {
        ArrayList<Restaurant> restaurantList = new ArrayList<>();

        Hour opening = new Hour(8, 0);
        Hour closing = new Hour(19, 0);
        HourRange hr = new HourRange(opening, closing);
        Schedule s = new Schedule(hr, hr, hr, hr, hr, hr, hr);

        Restaurant r1 = new Restaurant("Restaurant 1", Cuisine.FRENCH, s, 150);
        Restaurant r2 = new Restaurant("Restaurant 2", Cuisine.INDIAN, s, 200);

        System.out.println(r1.toString());
        System.out.println(r2.toString());

        restaurantList.add(r1);
        restaurantList.add(r2);

        return restaurantList;
    }


    //TODO: change the code of this method
    @Override
    public ArrayList<Restaurant> findByAllFinal() {
        ArrayList<Restaurant> restaurantsList = new ArrayList<>();

        Connection connexion = DBManager.getInstance().getConnection();
        try {
            Statement statement = connexion.createStatement();
            ResultSet rs = statement.executeQuery("select id,title,author from books;");
            while(rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");

                // Restaurant new_restaurant = new Restaurant(id,title,author);
                // restaurantsList.add(new_restaurant);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBManager.getInstance().cleanup(connexion, null, null);
        }

        return restaurantsList;
    }

    //TODO: change the code of this method
    @Override
    public void addingRestaurant(Restaurant new_restaurant){
        Connection connexion = DBManager.getInstance().getConnection();
        try {
            Statement statement = connexion.createStatement();
            ResultSet rs = statement.executeQuery("select id,title,author from books;");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBManager.getInstance().cleanup(connexion, null, null);
        }
    }
}
