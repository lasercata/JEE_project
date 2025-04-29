package org.attraction;

import java.util.ArrayList;

public interface AttractionDAO {
	public ArrayList<AttractionClass> findByAll();
	public ArrayList<AttractionClass> findByAllFinal();
	public void addingAttraction(AttractionClass new_attraction);
}
