package scrabble.model;

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
					plateau[i][j] = new Case(null);
				}
			}
		}
	}

	public void placerlettre(LettreAlphabetFrancais lettre, int x, int y) throws HorsPlateauException {
		this.plateau[y][x].setLettre(lettre);
	}

	public void placerlettreJoker(int x, int y, String affichage) throws HorsPlateauException {
		this.plateau[y][x].setLettreJoker(affichage);
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
