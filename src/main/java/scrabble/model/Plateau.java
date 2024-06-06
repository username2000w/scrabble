package scrabble.model;

import scrabble.model.utils.Coordonee;
import scrabble.model.utils.exception.HorsPlateauException;

public class Plateau {
	public static final int TAILLE_PLATEAU_HORIZONTALE = 15;
	public static final int TAILLE_PLATEAU_VERTICALE = 15;

	private Case[][] plateau = new Case[TAILLE_PLATEAU_VERTICALE][TAILLE_PLATEAU_HORIZONTALE];

	public Plateau() {
		for (int ligne = 0; ligne < TAILLE_PLATEAU_VERTICALE; ligne++) {
			for (int colonne = 0; colonne < TAILLE_PLATEAU_HORIZONTALE; colonne++) {
				if (colonne == TAILLE_PLATEAU_VERTICALE/2 && ligne == TAILLE_PLATEAU_HORIZONTALE/2) {
					plateau[ligne][colonne] = new Case(Bonus.ETOILE);
				}
				else if (colonne == 3 && ligne == 0 || colonne == 11 && ligne == 0 || colonne == 0 && ligne == 3 || colonne == 7 && ligne == 3 || colonne == 14 && ligne == 3 || colonne == 0 && ligne == 11 || colonne == 7 && ligne == 11 || colonne == 14 && ligne == 11 || colonne == 3 && ligne == 14 || colonne == 11 && ligne == 14 || colonne == 6 && ligne == 2 || colonne == 8 && ligne == 2 || colonne == 2 && ligne == 6 || colonne == 6 && ligne == 6 || colonne == 8 && ligne == 6 || colonne == 12 && ligne == 6 || colonne == 2 && ligne == 8 || colonne == 6 && ligne == 8 || colonne == 8 && ligne == 8 || colonne == 12 && ligne == 8 || colonne == 6 && ligne == 12 || colonne == 8 && ligne == 12 || colonne == 3 && ligne == 7 || colonne == 11 && ligne == 7 ) {
					plateau[ligne][colonne] = new Case(Bonus.LETTRE_DOUBLE);
				}
				else if (colonne == 0 && ligne == 0 || colonne == 7 && ligne == 0 || colonne == 14 && ligne == 0 || colonne == 0 && ligne == 7 || colonne == 14 && ligne == 7 || colonne == 0 && ligne == 14 || colonne == 7 && ligne == 14 || colonne == 14 && ligne == 14) {
					plateau[ligne][colonne] = new Case(Bonus.MOT_TRIPLE);
				}
				else if (colonne == 5 && ligne == 1 || colonne == 9 && ligne == 1 || colonne == 1 && ligne == 5 || colonne == 5 && ligne == 5 || colonne == 9 && ligne == 5 || colonne == 13 && ligne == 5 || colonne == 1 && ligne == 9 || colonne == 5 && ligne == 9 || colonne == 9 && ligne == 9 || colonne == 13 && ligne == 9 || colonne == 5 && ligne == 13 || colonne == 9 && ligne == 13) {
					plateau[ligne][colonne] = new Case(Bonus.LETTRE_TRIPLE);
				}
				else if (colonne == 1 && ligne == 1 || colonne == 13 && ligne == 1 || colonne == 2 && ligne == 2 || colonne == 12 && ligne == 2 || colonne == 3 && ligne == 3 || colonne == 11 && ligne == 3 || colonne == 4 && ligne == 4 || colonne == 10 && ligne == 4 || colonne == 7 && ligne == 7 || colonne == 1 && ligne == 13 || colonne == 13 && ligne == 13 || colonne == 2 && ligne == 12 || colonne == 12 && ligne == 12 || colonne == 3 && ligne == 11 || colonne == 11 && ligne == 11 || colonne == 4 && ligne == 10 || colonne == 10 && ligne == 10) {
					plateau[ligne][colonne] = new Case(Bonus.MOT_DOUBLE);
				}
				else {
					plateau[ligne][colonne] = new Case();
				}
			}
		}
	}

	public void placerTuile(Tuile tuile, Coordonee coordonee) throws HorsPlateauException {
		this.plateau[coordonee.ligne()][coordonee.colonne()].changerTuile(tuile);
	}

	public Case[][] plateau() {
		return plateau;
	}

	public Case casePlateau(Coordonee coordonee) {
		return plateau[coordonee.ligne()][coordonee.colonne()];
	}
	
	public void supprimerToutesTuiles() {
        for (Case[] cases : plateau) {
            for (Case aCase : cases) {
                aCase.viderCase();
            }
        }
    }
}
