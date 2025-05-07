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
import utils.GeneralDAO;

public class RestaurantDAOImpl implements GeneralDAO<Restaurant> {
    private Restaurant readQueryResult(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        Cuisine cuisine = Cuisine.valueOf(rs.getString("cuisine"));
        int nbOfSeats = rs.getInt("nbofseats");
        String[] openingHoursFields = {"moOP","tuOP","weOP","thOP","frOP","saOP","suOP"};
        String[] closingHoursFields = {"moCL","tuCL","weCL","thCL","frCL","saCL","suCL"};
        ArrayList<String> openingHours = new ArrayList<String>();
        ArrayList<String> closingHours = new ArrayList<String>();

        for (String hour : openingHoursFields) {
            openingHours.add(rs.getString(hour));
        }

        for (String hour : closingHoursFields) {
            closingHours.add(rs.getString(hour));
        }

        Restaurant result = new Restaurant(id, name, cuisine, openingHours, closingHours, nbOfSeats);
        return result;
    }

    @Override
    public ArrayList<Restaurant> getAll() {
        Connection connexion = DBManager.getInstance().getConnection();

        ArrayList<Restaurant> allList = new ArrayList<>();
        try {
            Statement statement = connexion.createStatement();
            ResultSet rs = statement.executeQuery("select " + Restaurant.sqlFields() + " from " + Restaurant.getTblName() + ";");

            while(rs.next()) {
                Restaurant new_restaurant = this.readQueryResult(rs);
                allList.add(new_restaurant);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBManager.getInstance().cleanup(connexion, null, null);
        }
        return allList;
    }

    @Override
    public Restaurant getById(int id) {
        Connection connexion = DBManager.getInstance().getConnection();

        try {
            Statement statement = connexion.createStatement();
            ResultSet rs = statement.executeQuery("select "+ Restaurant.sqlFields() + " from " + Restaurant.getTblName() + " where id = " + id + ";");

            while(rs.next()) {
                return this.readQueryResult(rs);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBManager.getInstance().cleanup(connexion, null, null);
        }
        return null;
    }

    @Override
    public void add(Restaurant new_restaurant) {
        Connection connexion = DBManager.getInstance().getConnection();

        try {
            Statement statement = connexion.createStatement();
            statement.executeUpdate("insert into attractions (" + Restaurant.sqlFields() + ") values (" +  new_restaurant.toSQL() + ");");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBManager.getInstance().cleanup(connexion, null, null);
        }
    }

    @Override
    public void edit(Restaurant restaurant) {
        Connection connexion = DBManager.getInstance().getConnection();
        try {
            Statement statement = connexion.createStatement();
            statement.executeUpdate("update attractions set name = '"+ restaurant.getName() +"', cuisine = '"+restaurant.getCuisine()+"', nbOfSeats = "+restaurant.getNbOfSeats()+", \" \r\n"
                    + "    moOP = '" + String.format("%02d:%02d", restaurant.getSchedule()[0].getOpening().getH(), restaurant.getSchedule()[0].getOpening().getM()) + "', +\r\n"
                    + "    moCL = '" + String.format("%02d:%02d", restaurant.getSchedule()[0].getEnding().getH(), restaurant.getSchedule()[0].getEnding().getM()) + "', +\r\n"
                    + "    tuOP = '" + String.format("%02d:%02d", restaurant.getSchedule()[1].getOpening().getH(), restaurant.getSchedule()[1].getOpening().getM()) + "', +\r\n"
                    + "    tuCL = '" + String.format("%02d:%02d", restaurant.getSchedule()[1].getEnding().getH(), restaurant.getSchedule()[1].getEnding().getM()) + "', +\r\n"
                    + "    weOP = '" + String.format("%02d:%02d", restaurant.getSchedule()[2].getOpening().getH(), restaurant.getSchedule()[2].getOpening().getM()) + "', +\r\n"
                    + "    weCL = '" + String.format("%02d:%02d", restaurant.getSchedule()[2].getEnding().getH(), restaurant.getSchedule()[2].getEnding().getM()) + "', +\r\n"
                    + "    thOP = '" + String.format("%02d:%02d", restaurant.getSchedule()[3].getOpening().getH(), restaurant.getSchedule()[3].getOpening().getM()) + "', +\r\n"
                    + "    thCL = '" + String.format("%02d:%02d", restaurant.getSchedule()[3].getEnding().getH(), restaurant.getSchedule()[3].getEnding().getM()) + "', +\r\n"
                    + "    frOP = '" + String.format("%02d:%02d", restaurant.getSchedule()[4].getOpening().getH(), restaurant.getSchedule()[4].getOpening().getM()) + "', +\r\n"
                    + "    frCL = '" + String.format("%02d:%02d", restaurant.getSchedule()[4].getEnding().getH(), restaurant.getSchedule()[4].getEnding().getM()) + "', +\r\n"
                    + "    saOP = '" + String.format("%02d:%02d", restaurant.getSchedule()[5].getOpening().getH(), restaurant.getSchedule()[5].getOpening().getM()) + "', +\r\n"
                    + "    saCL = '" + String.format("%02d:%02d", restaurant.getSchedule()[5].getEnding().getH(), restaurant.getSchedule()[5].getEnding().getM()) + "', +\r\n"
                    + "    suOP = '" + String.format("%02d:%02d", restaurant.getSchedule()[6].getOpening().getH(), restaurant.getSchedule()[6].getOpening().getM()) + "', +\r\n"
                    + "    suCL = '" + String.format("%02d:%02d", restaurant.getSchedule()[6].getEnding().getH(), restaurant.getSchedule()[6].getEnding().getM()) + "' +\r\n"
                    + "    WHERE id = " + restaurant.getID() + ";");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBManager.getInstance().cleanup(connexion, null, null);
        }
    }
}
