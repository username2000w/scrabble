package scrabble.controller;

import scrabble.gui.Console;
import scrabble.model.*;
import scrabble.model.utils.Coordonee;

import java.util.ArrayList;

public class MaitreDuJeu {

    private Sac sac;
    private Plateau plateau;
    private Joueur joueur;

    public MaitreDuJeu() {
        this.sac = new Sac();
        this.plateau = new Plateau();
        this.joueur = new Joueur(new Chevalet(), "Joueur 1");
        this.joueur.remplirChevalet(sac);
        // v3 => Compter plusieurs joueurs
    }

    public boolean jouerTour() {
        Console.afficherPlateau(plateau);
        System.out.println(joueur.getNom() + " a les tuiles suivantes :");
        Console.afficherChevalet(joueur.getChevalet());
        System.out.println();

        System.out.println("Que voulez-vous faire ?");
        System.out.println(" - 1. Jouer un mot");
        System.out.println(" - 2. Échanger une tuile");
        System.out.println(" - 3. Quitter");

        int choix = Console.inputIntScanner();
        switch (choix) {
            case 1:
                Coordonee coordonnees = joueur.jouerMot(plateau);
                System.out.println("Vous avez marqué " + this.calculerScoreMot(coordonnees) + " points.");
                joueur.remplirChevalet(sac);
                break;
            case 2:
                System.out.println("Quelle tuile voulez-vous échanger ? (Numéro de l'emplacement)");
                int input = Console.inputIntScanner();
                joueur.echanger(sac, input-1);
                break;
            case 3:
                return false;
            default:
                System.out.println("Choix invalide.");
        }
        return true;
    }

    public int calculerScoreMot(Coordonee coordoneeDebutMot) {
        int ligneDebutMot = coordoneeDebutMot.getLigne();
        int colonneDebutMot = coordoneeDebutMot.getColonne();
        int scoreMot = 0;
        Case[][] plateau = this.plateau.getPlateau();

        // score de la case initiale
        scoreMot += plateau[ligneDebutMot][colonneDebutMot].getLettre().getPoints();

        for (int ligne = ligneDebutMot; !plateau[ligne + 1][colonneDebutMot].estVide(); ligne++) {
            scoreMot += plateau[ligneDebutMot + 1][colonneDebutMot].getLettre().getPoints();

            if (!plateau[ligne + 1][colonneDebutMot - 1].estVide()) {
                for (int colonne = colonneDebutMot; !plateau[ligne + 1][colonne - 1].estVide(); colonne--) {
                    scoreMot += plateau[ligne + 1][colonne - 1].getLettre().getPoints();
                }
            }

            if (!plateau[ligne + 1][colonneDebutMot + 1].estVide()) {
                for (int colonne = colonneDebutMot; !plateau[ligne + 1][colonne + 1].estVide(); colonne++) {
                    scoreMot += plateau[ligne + 1][colonne + 1].getLettre().getPoints();
                }
            }
        }

        for (int colonne = colonneDebutMot; !plateau[ligneDebutMot][colonne + 1].estVide(); colonne++) {
            scoreMot += plateau[ligneDebutMot][colonneDebutMot + 1].getLettre().getPoints();

            if (!plateau[ligneDebutMot - 1][colonne + 1].estVide()) {
                for (int ligne = ligneDebutMot; !plateau[ligne - 1][colonne + 1].estVide(); ligne--) {
                    scoreMot += plateau[ligne - 1][colonne + 1].getLettre().getPoints();
                }
            }

            if (!plateau[ligneDebutMot + 1][colonne + 1].estVide()) {
                for (int ligne = ligneDebutMot; !plateau[ligne + 1][colonne + 1].estVide(); ligne++) {
                    scoreMot += plateau[ligne + 1][colonne + 1].getLettre().getPoints();
                }
            }
        }

        return scoreMot;
    }
}
