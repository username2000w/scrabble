package scrabble.model;

/**
 * Case du plateau de jeu.
 * 
 * Celle-ci peut contenir une tuile et un bonus.
 * Lors de l'instanciation, la case est vide (`null`).
 */
public class Case {
	private Bonus bonus;
	private LettreAlphabet lettre;
	
	public Case() {
		this.bonus = null;
		this.lettre = null;
	}

  /**
   * Indique si la case est vide.
   * @return `true` si la case est vide, `false` sinon.
   */
	public Boolean estVide() {
		return this.lettre == null;
	}

	public LettreAlphabet getLettre() {
		if (Boolean.TRUE.equals(this.estVide())) return null;
		return this.lettre;
	}

	public void setLettre(LettreAlphabet lettre) {
		this.lettre = lettre;
	}
	
	public Bonus getBonus() {
		return bonus;
	}

	public void setBonus(Bonus bonus) {
		this.bonus = bonus;
	}
}

