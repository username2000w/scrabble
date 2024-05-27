package scrabble.model;

import scrabble.model.utils.Coordonee;
import scrabble.model.utils.exception.HorsPlateauException;

public class Plateau {
	public static final int TAILLE_PLATEAU_HORIZONTALE = 15;
	public static final int TAILLE_PLATEAU_VERTICALE = 15;

	private Case[][] plateau = new Case[TAILLE_PLATEAU_VERTICALE][TAILLE_PLATEAU_HORIZONTALE];

	public Plateau() {
		for (int i = 0; i < TAILLE_PLATEAU_VERTICALE; i++) {
			for (int j = 0; j < TAILLE_PLATEAU_HORIZONTALE; j++) {
				if (j == 7 && i == 7) {
					plateau[i][j] = new Case(Bonus.ETOILE);
				}
				else {
					plateau[i][j] = new Case();
				}
			}
		}
	}

	public void placerlettre(LettreAlphabetFrancais lettre, Coordonee coordonee) throws HorsPlateauException {
		this.plateau[coordonee.getLigne()][coordonee.getColonne()].setLettre(lettre);
	}

	public void placerlettreJoker(Coordonee coordonee, String affichage, int comptJoker) throws HorsPlateauException {
		this.plateau[coordonee.getLigne()][coordonee.getColonne()].setLettreJoker(affichage, comptJoker);
	}

	public Case[][] getPlateau() {
		return plateau;
	}
	
	public void setCase(int x, int y, Case nouvelleCase) {
	    plateau[x][y] = nouvelleCase;
	}
	
	public void supprimerToutesLettres() {
        for (int y = 0; y < plateau.length; y++) {
            for (int x = 0; x < plateau[y].length; x++) {
                plateau[y][x].viderCase();
            }
        }
    }
}
