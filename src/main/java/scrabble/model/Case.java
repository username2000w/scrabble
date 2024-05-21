package scrabble.model;

import scrabble.model.utils.exception.HorsPlateauException;

/**
 * Case du plateau de jeu.
 *
 * Celle-ci peut contenir une tuile et un bonus.
 * Lors de l'instanciation, la case est vide (`null`).
 */
public class Case {
	private final Bonus bonus;
	private LettreAlphabetFrancais lettre;

	public Case(Bonus bonus) {
		this.bonus = bonus;
	}

	public Case() {
		this(null);
	}

  /**
   * Indique si la case est vide.
   * @return `true` si la case est vide, `false` sinon.
   */
	public Boolean estVide() {
		return this.lettre == null;
	}

	public LettreAlphabetFrancais getLettre() {
		if (Boolean.TRUE.equals(this.estVide())) return null;
		return this.lettre;
	}

	public void setLettre(LettreAlphabetFrancais lettre) {
		this.lettre = lettre;
	}

	public void setLettreJoker(String lettre) {
		this.lettre = LettreAlphabetFrancais.JOKER;
		this.lettre.setAffichage(lettre);
	}

	public Bonus getBonus() {
		return bonus;
	}
}





	
