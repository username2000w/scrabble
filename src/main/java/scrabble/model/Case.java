package scrabble.model;

/**
 * Case du plateau de jeu.
 * Celle-ci peut contenir une tuile et un bonus.
 * Lors de l'instanciation, la case est vide (`null`).
 */
public class Case {
	private final Bonus bonus;
	private Tuile tuile;

	public Case(Bonus bonus) {
		this.bonus = bonus;
		this.tuile = null;
	}

	public Case() {
		this(null);
	}

  /**
   * Indique si la case est vide.
   * @return `true` si la case est vide, `false` sinon.
   */
	public Boolean estVide() {
		return this.tuile == null;
	}

	public Tuile tuile() {
		if (Boolean.TRUE.equals(this.estVide())) return null;
		return this.tuile;
	}

	public void setTuile(Tuile tuile) {
		this.tuile = tuile;
	}

	public Bonus bonus() {
		return bonus;
	}
	
	public void viderCase() {
        this.tuile = null; // Supprime la lettre de la case, rendant la case vide
    }
}





	
