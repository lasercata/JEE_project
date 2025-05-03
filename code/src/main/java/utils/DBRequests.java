package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import attraction.AttractionClass;
import attraction.typeAttraction;

public class DBRequests {
	public DBRequests() {}
	
	public void instantiate_tables() {
		Connection connexion = DBManager.getInstance().getConnection();
		try {
			Statement statement = connexion.createStatement();
			statement.executeQuery("CREATE TABLE attractions (id INT PRIMARY KEY, name VARCHAR(100), type VARCHAR(50), sizealone FLOAT, sizewithadult FLOAT, moOP TIME, moCL TIME, tuOP TIME, tuCL TIME, weOP TIME, weCL TIME, thOP TIME, thCL TIME, frOP TIME, frCL TIME, saOP TIME, saCL TIME, suOP TIME, suCL TIME);");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.getInstance().cleanup(connexion, null, null);
		}
	}
	
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
	
	public ArrayList<AttractionClass> getAllAttractions(){
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
	
	public static void addingAttraction(AttractionClass new_attraction){
		Connection connexion = DBManager.getInstance().getConnection();
		try {
			Statement statement = connexion.createStatement();
			statement.executeQuery("insert into attractions ("+AttractionClass.sqlfields()+") values ("+ new_attraction.toSQL()+");");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.getInstance().cleanup(connexion, null, null);
		}
	}
}
