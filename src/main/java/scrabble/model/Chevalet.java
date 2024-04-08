package scrabble.model;

import java.util.ArrayList;


public class Chevalet {
	private ArrayList<Tuile> tuiles;
	private Sac sac;
	
	public Chevalet(ArrayList<Tuile> tuiles, Sac sac) {
		this.tuiles = tuiles;
		this.sac = sac;
	}
	
	public Tuile piocher() {
		return this.sac.piocher();
	}
}
