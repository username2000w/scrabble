package scrabble.controller;

import scrabble.gui.Console;
import scrabble.model.*;

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
                ArrayList<Integer> coordonnees = joueur.jouerMot(plateau);
                System.out.println("Vous avez marqué " + this.calculerScoreMot(coordonnees.get(0), coordonnees.get(1)) + " points.");
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

    public int calculerScoreMot(int xDebut, int yDebut) {
        int score = 0;
        Case[][] plateau = this.plateau.getPlateau();

        // score de la case initiale
        score += plateau[yDebut][xDebut].getLettre().getPoints();

        for (int y = yDebut; !plateau[y + 1][xDebut].estVide(); y++) {
            score += plateau[yDebut + 1][xDebut].getLettre().getPoints();

            if (!plateau[y + 1][xDebut - 1].estVide()) {
                for (int x = xDebut; !plateau[y + 1][x - 1].estVide(); x--) {
                    score += plateau[y + 1][x - 1].getLettre().getPoints();
                }
            }

            if (!plateau[y + 1][xDebut + 1].estVide()) {
                for (int x = xDebut; !plateau[y + 1][x + 1].estVide(); x++) {
                    score += plateau[y + 1][x + 1].getLettre().getPoints();
                }
            }
        }

        for (int x = xDebut; !plateau[yDebut][x + 1].estVide(); x++) {
            score += plateau[yDebut][xDebut + 1].getLettre().getPoints();

            if (!plateau[yDebut - 1][x + 1].estVide()) {
                for (int y = yDebut; !plateau[y - 1][x + 1].estVide(); y--) {
                    score += plateau[y - 1][x + 1].getLettre().getPoints();
                }
            }

            if (!plateau[yDebut + 1][x + 1].estVide()) {
                for (int y = yDebut; !plateau[y + 1][x + 1].estVide(); y++) {
                    score += plateau[y + 1][x + 1].getLettre().getPoints();
                }
            }
        }

        return score;
    }
}
