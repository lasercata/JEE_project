package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import time.EventTime;
import time.Hour;
import time.HourRange;
import characters.Character;


public class CharacterFetch {
	
	public static void instantiate_characters_table() {
		Connection connexion = DBManager.getInstance().getConnection();
		try {
			Statement statement = connexion.createStatement();
			statement.executeUpdate(
				    "CREATE TABLE IF NOT EXISTS characters (" +
				    "id INT, " +
				    "name VARCHAR(100), " +
				    "day DATE, " +
				    "openingHour INT, " +
				    "openingMinute INT, " +
				    "closingHour INT, " +
				    "closingMinute INT, " +
				    "PRIMARY KEY (id, day, openingHour, openingMinute)" +
				    ");"
				);
			System.out.println("Tables created !");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.getInstance().cleanup(connexion, null, null);
		}
	}

	public ArrayList<Character> findByAllFinal() {
	    ArrayList<Character> characters = new ArrayList<>();
	    Connection connexion = DBManager.getInstance().getConnection();
	    Map<String, ArrayList<EventTime>> scheduleMap = new HashMap<>();

	    try {
	        Statement statement = connexion.createStatement();
	        ResultSet rs = statement.executeQuery("SELECT name, day, openingHour, openingMinute, closingHour, closingMinute FROM characters;");

	        while (rs.next()) {
	            String name = rs.getString("name");
	            String day = rs.getString("day");
	            int openingHour = rs.getInt("openingHour");
	            int openingMinute = rs.getInt("openingMinute");
	            int closingHour = rs.getInt("closingHour");
	            int closingMinute = rs.getInt("closingMinute");

	            Hour opening = new Hour(openingHour, openingMinute);
	            Hour closing = new Hour(closingHour, closingMinute);
	            HourRange range = new HourRange(opening, closing);
	            EventTime event = new EventTime(day, range);

	            scheduleMap.computeIfAbsent(name, k -> new ArrayList<>()).add(event);
	        }

	        for (Map.Entry<String, ArrayList<EventTime>> entry : scheduleMap.entrySet()) {
	            characters.add(new Character(entry.getKey(), entry.getValue()));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBManager.getInstance().cleanup(connexion, null, null);
	    }

	    return characters;
	

	}
	
	public static Character getCharacterById(int id) {
	    Connection connexion = DBManager.getInstance().getConnection();

	    try {
	        Statement statement = connexion.createStatement();
	        ResultSet rs = statement.executeQuery("SELECT * FROM characters WHERE id = " + id + ";");

	        String name = null;
	        ArrayList<EventTime> schedule = new ArrayList<>();

	        while (rs.next()) {
	            if (name == null) {
	                name = rs.getString("name");
	            }

	            String day = rs.getString("day");
	            int openingHour = rs.getInt("openingHour");
	            int openingMinute = rs.getInt("openingMinute");
	            int closingHour = rs.getInt("closingHour");
	            int closingMinute = rs.getInt("closingMinute");

	            Hour opening = new Hour(openingHour, openingMinute);
	            Hour closing = new Hour(closingHour, closingMinute);
	            HourRange timeRange = new HourRange(opening, closing);
	            EventTime event = new EventTime(day, timeRange);

	            schedule.add(event);
	        }

	        if (name != null) {
	            return new Character(name, schedule);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBManager.getInstance().cleanup(connexion, null, null);
	    }

	    return null;
	}

	public static void addingCharacter(int id, Character new_character) {
	    Connection connexion = DBManager.getInstance().getConnection();

	    try {
	        Statement statement = connexion.createStatement();
	        ArrayList<EventTime> schedule = new_character.getSchedule();

	        for (EventTime event : schedule) { // car chaque personnage peut avoir plusieurs dates d'evenements
	            String day = event.getDay();
	            HourRange range = event.getTime();
	            Hour opening = range.getOpening();
	            Hour closing = range.getEnding();

	            String query = String.format(
	                "INSERT INTO characters (id, name, day, openingHour, openingMinute, closingHour, closingMinute) " +
	                "VALUES (%d, '%s', '%s', %d, %d, %d, %d);",
	                id,
	                new_character.getName().replace("'", "''"), // Escape single quotes
	                day,
	                opening.getH(),
	                opening.getM(),
	                closing.getH(),
	                closing.getM()
	            );

	            System.out.println(query);
	            statement.executeUpdate(query);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBManager.getInstance().cleanup(connexion, null, null);
	    }
	}


	
}
