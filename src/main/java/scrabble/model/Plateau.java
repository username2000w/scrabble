package scrabble.model;

import javafx.scene.layout.CornerRadii;
import scrabble.model.utils.Coordonee;
import scrabble.model.utils.exception.HorsPlateauException;

import java.util.ArrayList;

public class Plateau {
	public static final int TAILLE_PLATEAU_HORIZONTALE = 15;
	public static final int TAILLE_PLATEAU_VERTICALE = 15;
	private Case[][] plateau = new Case[TAILLE_PLATEAU_VERTICALE][TAILLE_PLATEAU_HORIZONTALE];

	public static final Coordonee[] COORDONEES_ETOILE = {
			new Coordonee(TAILLE_PLATEAU_VERTICALE/2, TAILLE_PLATEAU_VERTICALE/2)
	};
	public static final Coordonee[] COORDONEES_LETTRE_DOUBLE_SYMETRIQUE = {
			new Coordonee(0, 3),
			new Coordonee(0, 11),
			new Coordonee(3, 7),
			new Coordonee(3, 14),
			new Coordonee(11, 7),
			new Coordonee(11, 14),
			new Coordonee(2, 6),
			new Coordonee(2, 8),
			new Coordonee(6, 6),
			new Coordonee(6, 8),
			new Coordonee(6, 12),
			new Coordonee(8, 8),
			new Coordonee(8, 12),
	};
	public static final Coordonee[] COORDONEES_LETTRE_TRIPLE_SYMETRIQUE = {
			new Coordonee(1, 5),
			new Coordonee(1, 9),
			new Coordonee(5, 5),
			new Coordonee(5, 9),
			new Coordonee(5, 13),
			new Coordonee(9, 9),
			new Coordonee(9, 13),
	};
	public static final Coordonee[] COORDONEES_MOT_DOUBLE_SYMETRIQUE = {
			new Coordonee(1, 1),
			new Coordonee(2, 2),
			new Coordonee(3, 3),
			new Coordonee(4, 4),
			new Coordonee(1, 13),
			new Coordonee(2, 12),
			new Coordonee(3, 11),
			new Coordonee(4, 10),
			new Coordonee(10, 10),
			new Coordonee(11, 11),
			new Coordonee(12, 12),
			new Coordonee(13, 13),
	};
	public static final Coordonee[] COORDONEES_MOT_TRIPLE_SYMETRIQUE = {
			new Coordonee(0, 0),
			new Coordonee(0, 7),
			new Coordonee(0, 14),
			new Coordonee(7, 14),
			new Coordonee(14, 14),
	};

	public Plateau() {
		for (int ligne = 0; ligne < TAILLE_PLATEAU_VERTICALE; ligne++) {
			for (int colonne = 0; colonne < TAILLE_PLATEAU_HORIZONTALE; colonne++) {
				plateau[ligne][colonne] = new Case();
			}
		}
		for (Coordonee coordonee : COORDONEES_ETOILE) {
			plateau[coordonee.ligne()][coordonee.colonne()] = new Case(Bonus.ETOILE);
		}
		for (Coordonee coordonee : COORDONEES_LETTRE_DOUBLE_SYMETRIQUE) {
			plateau[coordonee.ligne()][coordonee.colonne()] = new Case(Bonus.LETTRE_DOUBLE);
			plateau[coordonee.colonne()][coordonee.ligne()] = new Case(Bonus.LETTRE_DOUBLE);
		}
		for (Coordonee coordonee : COORDONEES_LETTRE_TRIPLE_SYMETRIQUE) {
			plateau[coordonee.ligne()][coordonee.colonne()] = new Case(Bonus.LETTRE_TRIPLE);
			plateau[coordonee.colonne()][coordonee.ligne()] = new Case(Bonus.LETTRE_TRIPLE);
		}
		for (Coordonee coordonee : COORDONEES_MOT_DOUBLE_SYMETRIQUE) {
			plateau[coordonee.ligne()][coordonee.colonne()] = new Case(Bonus.MOT_DOUBLE);
			plateau[coordonee.colonne()][coordonee.ligne()] = new Case(Bonus.MOT_DOUBLE);
		}
		for (Coordonee coordonee : COORDONEES_MOT_TRIPLE_SYMETRIQUE) {
			plateau[coordonee.ligne()][coordonee.colonne()] = new Case(Bonus.MOT_TRIPLE);
			plateau[coordonee.colonne()][coordonee.ligne()] = new Case(Bonus.MOT_TRIPLE);
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
