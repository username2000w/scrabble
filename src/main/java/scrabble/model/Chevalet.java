package scrabble.model;

import java.util.ArrayList;

public class Chevalet {
	private ArrayList<Tuile> tuiles;
	private Sac sac;
	private final int TAILLE = 7;
	
	public Chevalet(Sac sac) {
    this.tuiles = new ArrayList<>();
		this.sac = sac;
	}
	
	public void piocher(int tuileIndex) {
		if (this.tuiles.size() < TAILLE) {
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
    for (int i = 0; i < TAILLE; i++) {
      System.out.print(this.getTuileAvecIndex(i).getLettre() + " ");
    }
    System.out.print("\n");
  }
  
  public void completerChevalet() {
		for (int tuileIndex = this.getTuiles().size(); tuileIndex < TAILLE; tuileIndex++) {
			this.piocher(tuileIndex);
		}
	}
  
  public Tuile getTuileAvecLettre(String lettre) {
	  for (Tuile tuile : tuiles) {
	      if (lettre.equals(tuile.getLettre().toString())) {
	    	  return tuile;
	      }
	    }
	  return null;
  }
}
