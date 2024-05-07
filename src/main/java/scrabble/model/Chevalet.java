package scrabble.model;

import scrabble.model.utils.exception.SacVideException;

import java.util.ArrayList;

public class Chevalet {
	private ArrayList<LettreAlphabet> lettres;
	private Sac sac;
	private static final int TAILLE = 7;
	
	public Chevalet(Sac sac) {
    this.lettres = new ArrayList<>();
		this.sac = sac;
	}
	
	public void piocher(int tuileIndex) {
		if (this.lettres.size() < TAILLE) {
			try {
				this.lettres.add(tuileIndex, this.sac.piocher());
			} catch (SacVideException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void echanger(int tuileIndex) {
		LettreAlphabet lettre = this.lettres.remove(tuileIndex);
		this.sac.ajouter(lettre);
	}
	
	public LettreAlphabet getLettreAvecIndex(int tuileIndex) {
		return this.lettres.get(tuileIndex);
	}

	public ArrayList<LettreAlphabet> getLettres() {
		return this.lettres;
	}

  public void afficherTuiles() {
    for (int i = 0; i < TAILLE; i++) {
      System.out.print(this.getLettreAvecIndex(i).toString() + " ");
    }
    System.out.print("\n");
  }
  
  public void completerChevalet() {
		for (int tuileIndex = this.getLettres().size(); tuileIndex < TAILLE; tuileIndex++) {
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
