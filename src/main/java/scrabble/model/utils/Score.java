package scrabble.model.utils;

import scrabble.model.Case;
import scrabble.model.Chevalet;
import scrabble.model.Mot;
import scrabble.model.Plateau;

public class Score {
    public static int calculerScoreMot(Mot mot, Plateau plateau) {
        Coordonee coordoneeDebutMot = mot.coordoneeDebut();
        Direction directionMot = mot.direction();
        Case[][] cases = plateau.plateau();

        int scoreMot = 0;

        // Calcul du score du mot de base avec multiplicateurs
        int multiplicateurMot = 1;

        for (Coordonee coordonee = coordoneeDebutMot; !cases[coordonee.ligne()][coordonee.colonne()].estVide(); coordonee = coordonee.avancer(directionMot)) {
            int multiplicateurLettre = 1;

            if (cases[coordonee.ligne()][coordonee.colonne()].bonus() != null) {
                multiplicateurLettre = cases[coordonee.ligne()][coordonee.colonne()].bonus().multiplicateurLettre();
                multiplicateurMot = cases[coordonee.ligne()][coordonee.colonne()].bonus().multiplicateurMot();
            }

            scoreMot += cases[coordonee.ligne()][coordonee.colonne()].tuile().points() * multiplicateurLettre;
        }
        scoreMot *= multiplicateurMot;

        // Calcul des mots formés par le mot de base avec multiplicateurs
        for (Coordonee coordonee = coordoneeDebutMot; !cases[coordonee.ligne()][coordonee.colonne()].estVide(); coordonee = coordonee.avancer(directionMot)) {
            Coordonee coordoneeCoteDroit = coordonee.avancer(directionMot.oppose());
            if (!plateau.casePlateau(coordoneeCoteDroit).estVide()) {
                for (Coordonee coordoneeMotCote = coordoneeCoteDroit; !plateau.casePlateau(coordoneeMotCote).estVide(); coordoneeMotCote = coordoneeMotCote.avancer(directionMot.oppose())) {
                    scoreMot += plateau.casePlateau(coordoneeMotCote).tuile().points();
                }
            }

            Coordonee coordoneeCoteGauche = coordonee.reculer(directionMot.oppose());
            if (!plateau.casePlateau(coordoneeCoteGauche).estVide()) {
                for (Coordonee coordoneeMotCote = coordoneeCoteGauche; !plateau.casePlateau(coordoneeMotCote).estVide(); coordoneeMotCote = coordoneeMotCote.reculer(directionMot.oppose())) {
                    scoreMot += plateau.casePlateau(coordoneeMotCote).tuile().points();
                }
            }
        }

        int scrabble = mot.nombreDeLettre() == Chevalet.TAILLE ? 50 : 0;
        return scoreMot + scrabble;
    }

}