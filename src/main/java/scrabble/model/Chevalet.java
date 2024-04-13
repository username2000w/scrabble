package scrabble.model;

import java.util.ArrayList;

public class Chevalet {
	private ArrayList<Tuile> tuiles;
	private Sac sac;
	
	public Chevalet(Sac sac) {
    this.tuiles = new ArrayList<>();
		this.sac = sac;
	}
	
	public void piocher(int tuileIndex) {
		if (this.tuiles.size() < 7) {
			this.tuiles.add(tuileIndex, this.sac.piocher());	
		}			
	}
	
	public void echanger(int tuileIndex) {
		Tuile tuile = this.tuiles.remove(tuileIndex);
		this.sac.ajouter(tuile);
	}
	
	public Tuile getTuileAvecIndex(int tuileIndex) {
		return this.tuiles.get(tuileIndex);
	}

	public ArrayList<Tuile> getTuiles() {
		return this.tuiles;
	}

  public void afficherTuiles() {
    for (int i = 0; i < 7; i++) {
      System.out.print(this.getTuileAvecIndex(i).getLettre() + " ");
    }
    System.out.print("\n");
  }
}
