package org.attraction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.tutorial.Book;
import org.tutorial.DBManager;

public class AttractionDAOImpl implements AttractionDAO {

	@Override
	public ArrayList<AttractionClass> findByAll() {
		ArrayList<AttractionClass> maListe = new ArrayList<>();
		ArrayList<String> openingHours =  new ArrayList<String>(); //{"08:00","08:00","08:00","08:00","08:00","08:00","08:00"};
		ArrayList<String> closingHours = new ArrayList<String>(); // {"18:00","18:00","18:00","18:00","18:00","18:00","18:00"};
		for (int i = 0; i < 8; i++) {
			openingHours.add("08:00");
			closingHours.add("19:00");
		}
		AttractionClass b1 = new AttractionClass(1,"Space Mountain", typeAttraction.rollercoaster, 1.60, 1.50, openingHours, closingHours);
		AttractionClass b2 = new AttractionClass(2,"Cinema", typeAttraction.cinema4d, 1.60, 1.50, openingHours, closingHours);
		
		System.out.println(b1.toString());
		System.out.println(b2.toString());
		
		maListe.add(b1);
		maListe.add(b2);
		
		return maListe;
	}

	
	@Override
	public ArrayList<AttractionClass> findByAllFinal(){
		ArrayList<AttractionClass> maListe = new ArrayList<>();
		
		Connection connexion = DBManager.getInstance().getConnection();
		try {
			Statement statement = connexion.createStatement();
			ResultSet rs = statement.executeQuery("select id,title,author from books;");
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				
				//AttractionClass new_attraction = new AttractionClass(id,title,author);
				//maListe.add(new_attraction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.getInstance().cleanup(connexion, null, null);
		}
		return maListe;
	}
	
	@Override
	public void addingAttraction(AttractionClass new_attraction){
		Connection connexion = DBManager.getInstance().getConnection();
		try {
			Statement statement = connexion.createStatement();
			ResultSet rs = statement.executeQuery("select id,title,author from books;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.getInstance().cleanup(connexion, null, null);
		}
	}
}
