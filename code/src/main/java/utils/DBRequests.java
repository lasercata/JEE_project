package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import attraction.AttractionClass;
import attraction.typeAttraction;
import restaurants.Cuisine;
import restaurants.Restaurant;

public class DBRequests {
	public DBRequests() {}
	
	public static int unused_id(String table){
		Connection connexion = DBManager.getInstance().getConnection();
		int newID = 1;
		try {
			Statement statement = connexion.createStatement();
			ResultSet rs = statement.executeQuery("select max(id) + 1 as next_id from "+table+";");
			if (rs.next()) {
				newID = rs.getInt("next_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.getInstance().cleanup(connexion, null, null);
		}
		return newID;
	}
	
	public static ArrayList<AttractionClass> getAllAttractions(){
		Connection connexion = DBManager.getInstance().getConnection();
		
		ArrayList<AttractionClass> maListe = new ArrayList<>();
		try {
			Statement statement = connexion.createStatement();
			ResultSet rs = statement.executeQuery("select "+ AttractionClass.sqlfields() + " from attractions;");
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				typeAttraction type = typeAttraction.valueOf(rs.getString("type"));
				double sizeAlone = rs.getDouble("sizealone");
				double sizeWithAdult = rs.getDouble("sizewithadult");
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
				
				
				AttractionClass new_attraction = new AttractionClass(id,name,type,sizeAlone,sizeWithAdult,openingHours,closingHours);
				maListe.add(new_attraction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.getInstance().cleanup(connexion, null, null);
		}
		return maListe;
	}
	
	public static AttractionClass getAttractioById(int id){
		Connection connexion = DBManager.getInstance().getConnection();

		try {
			Statement statement = connexion.createStatement();
			ResultSet rs = statement.executeQuery("select "+ AttractionClass.sqlfields() + " from attractions where id = "+id+";");
			while(rs.next()) {
				int id1 = rs.getInt("id");
				String name = rs.getString("name");
				typeAttraction type = typeAttraction.valueOf(rs.getString("type"));
				double sizeAlone = rs.getDouble("sizealone");
				double sizeWithAdult = rs.getDouble("sizewithadult");
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
				
				
				AttractionClass result = new AttractionClass(id1,name,type,sizeAlone,sizeWithAdult,openingHours,closingHours);
				return result;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.getInstance().cleanup(connexion, null, null);
		}
		return null;
	}
	
	public static void addingAttraction(AttractionClass new_attraction){
		Connection connexion = DBManager.getInstance().getConnection();
		try {
			Statement statement = connexion.createStatement();
			System.out.println("insert into attractions ("+AttractionClass.sqlfields()+") values ("+ new_attraction.toSQL()+");");
			statement.executeQuery("insert into attractions ("+AttractionClass.sqlfields()+") values ("+ new_attraction.toSQL()+");");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.getInstance().cleanup(connexion, null, null);
		}
	}
	
	public static void editAttraction(AttractionClass attraction){
		Connection connexion = DBManager.getInstance().getConnection();
		try {
			Statement statement = connexion.createStatement();
			statement.executeQuery("update attractions set name = '"+ attraction.getName() +"', type = '"+attraction.getType()+"', sizealone = "+attraction.getSizeAlone()+", \" \r\n"
					+ "    sizewithadult = " + attraction.getSizeWithAdult() + ", \" +\r\n"
					+ "    moOP = '" + String.format("%02d:%02d", attraction.getSchedule()[0].getOpening().getH(), attraction.getSchedule()[0].getOpening().getM()) + "', +\r\n"
					+ "    moCL = '" + String.format("%02d:%02d", attraction.getSchedule()[0].getEnding().getH(), attraction.getSchedule()[0].getEnding().getM()) + "', +\r\n"
					+ "    tuOP = '" + String.format("%02d:%02d", attraction.getSchedule()[1].getOpening().getH(), attraction.getSchedule()[1].getOpening().getM()) + "', +\r\n"
					+ "    tuCL = '" + String.format("%02d:%02d", attraction.getSchedule()[1].getEnding().getH(), attraction.getSchedule()[1].getEnding().getM()) + "', +\r\n"
					+ "    weOP = '" + String.format("%02d:%02d", attraction.getSchedule()[2].getOpening().getH(), attraction.getSchedule()[2].getOpening().getM()) + "', +\r\n"
					+ "    weCL = '" + String.format("%02d:%02d", attraction.getSchedule()[2].getEnding().getH(), attraction.getSchedule()[2].getEnding().getM()) + "', +\r\n"
					+ "    thOP = '" + String.format("%02d:%02d", attraction.getSchedule()[3].getOpening().getH(), attraction.getSchedule()[3].getOpening().getM()) + "', +\r\n"
					+ "    thCL = '" + String.format("%02d:%02d", attraction.getSchedule()[3].getEnding().getH(), attraction.getSchedule()[3].getEnding().getM()) + "', +\r\n"
					+ "    frOP = '" + String.format("%02d:%02d", attraction.getSchedule()[4].getOpening().getH(), attraction.getSchedule()[4].getOpening().getM()) + "', +\r\n"
					+ "    frCL = '" + String.format("%02d:%02d", attraction.getSchedule()[4].getEnding().getH(), attraction.getSchedule()[4].getEnding().getM()) + "', +\r\n"
					+ "    saOP = '" + String.format("%02d:%02d", attraction.getSchedule()[5].getOpening().getH(), attraction.getSchedule()[5].getOpening().getM()) + "', +\r\n"
					+ "    saCL = '" + String.format("%02d:%02d", attraction.getSchedule()[5].getEnding().getH(), attraction.getSchedule()[5].getEnding().getM()) + "', +\r\n"
					+ "    suOP = '" + String.format("%02d:%02d", attraction.getSchedule()[6].getOpening().getH(), attraction.getSchedule()[6].getOpening().getM()) + "', +\r\n"
					+ "    suCL = '" + String.format("%02d:%02d", attraction.getSchedule()[6].getEnding().getH(), attraction.getSchedule()[6].getEnding().getM()) + "' +\r\n"
					+ "    WHERE id = " + attraction.getID() + ";");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.getInstance().cleanup(connexion, null, null);
		}
	}
	
	public static ArrayList<Restaurant> getAllRestaurants(){
		Connection connexion = DBManager.getInstance().getConnection();
		
		ArrayList<Restaurant> maListe = new ArrayList<>();
		try {
			Statement statement = connexion.createStatement();
			ResultSet rs = statement.executeQuery("select "+ Restaurant.sqlfields() + " from restaurants;");
			while(rs.next()) {
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
				
				
				Restaurant new_restaurant = new Restaurant(id,name,cuisine,openingHours,closingHours,nbOfSeats);
				maListe.add(new_restaurant);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.getInstance().cleanup(connexion, null, null);
		}
		return maListe;
	}
	
	public static Restaurant getRestaurantsById(int id){
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			Statement statement = connexion.createStatement();
			ResultSet rs = statement.executeQuery("select "+ Restaurant.sqlfields() + " from restaurants where id = "+id+";");
			while(rs.next()) {
				int id1 = rs.getInt("id");
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
				
				
				Restaurant result = new Restaurant(id1,name,cuisine,openingHours,closingHours,nbOfSeats);
				return result;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.getInstance().cleanup(connexion, null, null);
		}
		return null;
	}
	
	public static void addingRestaurant(Restaurant new_restaurant){
		Connection connexion = DBManager.getInstance().getConnection();
		try {
			Statement statement = connexion.createStatement();
			statement.executeQuery("insert into restaurants ("+Restaurant.sqlfields()+") values ("+ new_restaurant.toSQL()+");");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.getInstance().cleanup(connexion, null, null);
		}
	}
	
	public static void editRestaurant(Restaurant restaurant){
		Connection connexion = DBManager.getInstance().getConnection();
		try {
			Statement statement = connexion.createStatement();
			statement.executeQuery("update attractions set name = '"+ restaurant.getName() +"', cuisine = '"+restaurant.getCuisine()+"', nbOfSeats = "+restaurant.getNbOfSeats()+", \" \r\n"
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.getInstance().cleanup(connexion, null, null);
		}
	}
	
	public static void delete(String table, int id) {
		Connection connexion = DBManager.getInstance().getConnection();
		try {
			Statement statement = connexion.createStatement();
			statement.executeQuery("delete from "+ table +" where id = "+ id+";");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.getInstance().cleanup(connexion, null, null);
		}
	}
}
