package scrabble.model;

import java.util.ArrayList;


public class Chevalet {
	private ArrayList<Tuile> tuiles;
	private Sac sac;
	
	public Chevalet(Sac sac) {
		this.sac = sac;
	}
	
	public void piocher() {
		if (this.tuiles.size() < 7)
			this.tuiles.add(this.sac.piocher());
	}
	
	public void echanger(Tuile tuile) {
		this.tuiles.remove(tuile);
		this.sac.ajouter(tuile);
	}
	
	public Tuile getTuileAvecIndex(int index) {
		return this.tuiles.get(index);
	}
}
