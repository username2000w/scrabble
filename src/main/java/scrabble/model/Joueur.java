package scrabble.model;

public class Joueur {
	private Chevalet chevalet;
	private String nom;
	
	public Joueur(Chevalet chevalet, String nom) {
		this.chevalet = chevalet;
		this.nom = nom;
		chevalet.completerChevalet();
	}

	public String getNom() {
		return nom;
	}
	
	public void echanger(int tuileIndex) {
		this.chevalet.echanger(tuileIndex);
		this.chevalet.piocher(tuileIndex);
	}

  public void afficherChevalet() {
    this.chevalet.afficherTuiles();
  }
  
  	
}
