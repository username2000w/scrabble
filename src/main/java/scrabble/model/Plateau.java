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
				if (colonne == 7 && ligne == 7) {
					plateau[ligne][colonne] = new Case(Bonus.ETOILE);
				}
				else {
					plateau[ligne][colonne] = new Case();
				}
			}
		}
	}

	public void placerTuile(Tuile tuile, Coordonee coordonee) throws HorsPlateauException {
		this.plateau[coordonee.ligne()][coordonee.colonne()].setTuile(tuile);
	}

	public Case[][] plateau() {
		return plateau;
	}
	
	public void supprimerToutesTuiles() {
        for (Case[] cases : plateau) {
            for (Case aCase : cases) {
                aCase.viderCase();
            }
        }
    }
}
