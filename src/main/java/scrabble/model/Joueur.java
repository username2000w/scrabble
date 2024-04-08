package scrabble.model;

public class Joueur {
	private Chevalet chevalet;
	private String nom;
	
	public Joueur(Chevalet chevalet, String nom) {
		this.chevalet = chevalet;
		this.nom = nom;

		for (int i = 0; i<7 ; i++) {
			this.chevalet.piocher();
		}
	}

	public String getNom() {
		return nom;
	}
	
	public void echanger(Tuile tuile) {
		this.chevalet.echanger(tuile);
		this.chevalet.piocher();
	}
}
