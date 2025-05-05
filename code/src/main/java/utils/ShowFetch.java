package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import characters.Show;
import characters.Character;
import time.EventTime;
import time.*;

public class ShowFetch {
	
	public static void createTablesForShow() {
	    Connection connexion = DBManager.getInstance().getConnection();

	    try {
	        Statement statement = connexion.createStatement();

	        // Table principale des shows (un enregistrement par horaire d’un show)
	        statement.executeUpdate(
	            "CREATE TABLE IF NOT EXISTS shows (" +
	            "id INT, " +
	            "titre VARCHAR(100), " +
	            "day DATE, " +
	            "openingHour INT, " +
	            "openingMinute INT, " +
	            "closingHour INT, " +
	            "closingMinute INT, " +
	            "lieu VARCHAR(100), " +
	            "PRIMARY KEY (id, day, openingHour, openingMinute)" +
	            ");"
	        );

	        // Table associant un show à plusieurs personnages
	        statement.executeUpdate(
	            "CREATE TABLE IF NOT EXISTS show_characters (" +
	            "show_id INT, " +
	            "character_id INT, " +
	            "FOREIGN KEY (show_id) REFERENCES shows(id), " +
	            "FOREIGN KEY (character_id) REFERENCES characters(id)" +
	            ");"
	        );

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBManager.getInstance().cleanup(connexion, null, null);
	    }
	}

	public static Show getShowById(int id) {
	    Connection connexion = DBManager.getInstance().getConnection();

	    try {
	        Statement statement = connexion.createStatement();
	        ResultSet rs = statement.executeQuery("SELECT * FROM shows WHERE id = " + id + ";");

	        String titre = null;
	        String lieu = null;
	        ArrayList<EventTime> horaires = new ArrayList<>();

	        while (rs.next()) {
	            // Récupère les infos générales une seule fois
	            if (titre == null) {
	                titre = rs.getString("titre");
	                lieu = rs.getString("lieu");
	            }

	            // Crée l’objet horaire pour chaque ligne
	            String day = rs.getString("day");
	            int opH = rs.getInt("openingHour");
	            int opM = rs.getInt("openingMinute");
	            int clH = rs.getInt("closingHour");
	            int clM = rs.getInt("closingMinute");

	            EventTime horaire = new EventTime(day, new HourRange(new Hour(opH, opM), new Hour(clH, clM)));
	            horaires.add(horaire);
	        }

	        // Récupère les personnages associés
	        ResultSet rsChars = statement.executeQuery(
	            "SELECT DISTINCT c.id FROM show_characters sc " +
	            "JOIN characters c ON sc.character_id = c.id " +
	            "WHERE sc.show_id = " + id + ";"
	        );

	        ArrayList<Character> personnages = new ArrayList<>();
	        while (rsChars.next()) {
	            int charId = rsChars.getInt("id");
	            Character c = CharacterFetch.getCharacterById(charId);  // Appel à une méthode externe
	            if (c != null) {
	                personnages.add(c);
	            }
	        }

	        if (titre != null) {
	            return new Show(titre, "N/A", horaires, lieu, personnages);  // Le jour est ignoré ici
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBManager.getInstance().cleanup(connexion, null, null);
	    }

	    return null;
	}

	public static ArrayList<Show> findByAllShows() {
	    ArrayList<Show> shows = new ArrayList<>();
	    Connection connexion = DBManager.getInstance().getConnection();

	    try {
	        Statement statement = connexion.createStatement();

	        // Récupère tous les IDs de shows
	        ResultSet rsIds = statement.executeQuery("SELECT DISTINCT id FROM shows;");
	        while (rsIds.next()) {
	            int id = rsIds.getInt("id");
	            Show s = getShowById(id);  // Reconstitue chaque show via son ID
	            if (s != null) {
	                shows.add(s);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBManager.getInstance().cleanup(connexion, null, null);
	    }

	    return shows;
	}


}
