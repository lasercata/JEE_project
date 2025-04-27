package org.attraction;

import java.util.ArrayList;
import java.util.List;

import org.tutorial.Book;

public class AttractionDAOImpl implements AttractionDAO {

	@Override
	public ArrayList<AttractionClass> findByAll() {
		ArrayList<AttractionClass> maListe = new ArrayList<>();
		ArrayList<String> heuresO =  new ArrayList<>(); //{"08:00","08:00","08:00","08:00","08:00","08:00","08:00"};
		ArrayList<String> heuresF = new ArrayList<>(); // {"18:00","18:00","18:00","18:00","18:00","18:00","18:00"};
		for (int i = 0; i < 8; i++) {
			heuresO.add("08:00");
			heuresF.add("19:00");
		}
		AttractionClass b1 = new AttractionClass(1,"Space Mountain", typeAttraction.rollercoaster, 1.60, 1.50, heuresO, heuresF);
		AttractionClass b2 = new AttractionClass(2,"Cinema", typeAttraction.cinema4d, 1.60, 1.50, heuresO, heuresF);
		
		maListe.add(b1);
		maListe.add(b2);
		
		return maListe;
	}

}
