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
		this.lettre = null;
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

	public void setLettreJoker(String affichage, int comptJoker) {
		this.lettre = LettreAlphabetFrancais.JOKER;
		if (comptJoker == 0) {
			this.lettre.setJoker1(affichage);
		} else {
			this.lettre.setJoker2(affichage);
		}
	}

	public Bonus getBonus() {
		return bonus;
	}
	
	public void viderCase() {
        this.lettre = null; // Supprime la lettre de la case, rendant la case vide
    }
}





	
