package org.attraction;

import java.util.ArrayList;
import java.util.List;

import org.tutorial.Book;

public class AttractionDAOImpl implements AttractionDAO {

	@Override
	public ArrayList<AttractionClass> findByAll() {
		ArrayList<AttractionClass> maListe = new ArrayList<>();
		AttractionClass b1 = new AttractionClass();
		AttractionClass b2 = new AttractionClass();
		
		maListe.add(b1);
		maListe.add(b2);
		
		return maListe;
	}

}
