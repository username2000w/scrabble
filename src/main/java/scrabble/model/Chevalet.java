package scrabble.model;

import scrabble.model.utils.exception.SacVideException;

import java.util.ArrayList;

public class Chevalet {
	private ArrayList<LettreAlphabetFrancais> lettres;
	private Sac sac;
	private static final int TAILLE = 7;
	
	public Chevalet(Sac sac) {
    this.lettres = new ArrayList<>();
		this.sac = sac;
	}
	
	public void piocher() {
		if (this.lettres.size() < TAILLE) {
			try {
				this.lettres.add(this.sac.piocher());
			} catch (SacVideException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void echanger(int tuileIndex) {
		LettreAlphabetFrancais lettre = this.lettres.remove(tuileIndex);
		this.sac.ajouter(lettre);
	}
	
	public LettreAlphabetFrancais getLettreAvecIndex(int tuileIndex) {
		return this.lettres.get(tuileIndex);
	}

	public ArrayList<LettreAlphabetFrancais> getLettres() {
		return this.lettres;
	}

  public void afficherTuiles() {
    for (int i = 0; i < TAILLE; i++) {
      System.out.print(this.getLettreAvecIndex(i).toString() + " ");
    }
    System.out.print("\n");
  }
  
  public void remplirChevalet() {
		for (int tailleActuelle = this.getLettres().size(); tailleActuelle < TAILLE; tailleActuelle++) {
			this.piocher();
		}
	}
  
  public LettreAlphabetFrancais getTuileAvecLettre(String lettre_voulu) {
	  for (LettreAlphabetFrancais lettre : lettres) {
	      if (lettre_voulu.equals(lettre.toString())) {
	    	  return lettre;
	      }
	    }
	  return null;
  }
}
