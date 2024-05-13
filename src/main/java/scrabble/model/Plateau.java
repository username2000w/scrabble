package scrabble.model;

import scrabble.model.utils.exception.HorsPlateauException;

public class Plateau {
	private static final int TAILLE_PLATEAU_HORIZONTALE = 15;
	private static final int TAILLE_PLATEAU_VERTICALE = 15;

	private Case[][] plateau = new Case[TAILLE_PLATEAU_VERTICALE][TAILLE_PLATEAU_HORIZONTALE];

	public Plateau() {
		for (int i = 0; i < TAILLE_PLATEAU_VERTICALE; i++) {
			for (int j = 0; j < TAILLE_PLATEAU_HORIZONTALE; j++) {
				if (j == 7 && i == 7) {
					plateau[i][j] = new Case(Bonus.ETOILE);
				}
				else {
					plateau[i][j] = new Case(null);
				}
			}
		}
	}

	public void afficher() {
		for (int i = 0; i < TAILLE_PLATEAU_VERTICALE; i++) {
			for (int j = 0; j < TAILLE_PLATEAU_HORIZONTALE; j++) {
				LettreAlphabetFrancais lettre = plateau[i][j].getLettre();
				Bonus bonus = plateau[i][j].getBonus();

				if (bonus == null) {
					if (lettre == null) { // si la case est vide, on affiche un carré vide.
						System.out.print("□ ");
					} else {
						System.out.print(lettre + " ");
					}
				} else {
					System.out.print(bonus);
				}
			}
		}

		// Retour à la ligne.
		System.out.println();
	}

	public void placerlettre(LettreAlphabetFrancais lettre,int x, int y) throws HorsPlateauException {
		this.plateau[y][x].setLettre(lettre);
	}
}

