package scrabble.model;

public enum Bonus {
	MOT_TRIPLE("3 ", 1, 3),
	MOT_DOUBLE("2 ", 1, 2),
	LETTRE_DOUBLE("d ", 2, 1),
	LETTRE_TRIPLE("t ", 3, 1),
	ETOILE("★ ", 1, 2),;
	
	private final String symbole;
	private final int multiplicateurLettre;
	private final int multiplicateurMot;
	
	Bonus(String symbole, int multiplicateurLettre, int multiplicateurMot) {
		this.symbole = symbole;
		this.multiplicateurLettre = multiplicateurLettre;
		this.multiplicateurMot = multiplicateurMot;
	}
	
	@Override
	public String toString() {
		return symbole;
	}

	public int multiplicateurLettre() {
		return multiplicateurLettre;
	}

	public int multiplicateurMot() {
		return multiplicateurMot;
	}
}
