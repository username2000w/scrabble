package scrabble.gui;

import scrabble.model.Bonus;
import scrabble.model.Chevalet;
import scrabble.model.Plateau;
import scrabble.model.Tuile;

import java.util.Scanner;

public class Console {
    public static void afficherBienvenue() {
        System.out.println("-------------------------------------------------------");
        System.out.println("-- Bienvenue dans notre magnifique jeu de scrabble ! --");
        System.out.println("-- développé par Maxime, Mikkel, Léo et Alexis       --");
        System.out.println("-------------------------------------------------------");
    }

    public static void afficherFinPartie() {
        System.out.println("------------------------");
        System.out.println("-- FIN DE LA PARTIE ! --");
        System.out.println("------------------------");
        System.out.println("Merci d'avoir joué !");
    }

    public static void message(String message) {
        System.out.println(message);
    }

    public static void afficherPlateau(Plateau plateau) {
        int compteurJoker = 0;

        for (int i = 0; i < Plateau.TAILLE_PLATEAU_VERTICALE; i++) {
            for (int j = 0; j < Plateau.TAILLE_PLATEAU_HORIZONTALE; j++) {
                Tuile tuile = plateau.getPlateau()[i][j].getTuile();
                Bonus bonus = plateau.getPlateau()[i][j].getBonus();

                if (tuile == null) {
                    if (bonus == null) { // si la case est vide, on affiche un carré vide.
                        System.out.print("□ ");
                    } else {
                    	System.out.print(bonus);                    	
                    }
                } else {
                	System.out.print(tuile.afficherLettre() + " ");
                }
            }
            // Retour à la ligne.
            System.out.println();
        }
    }

    public static void afficherChevalet(Chevalet chevalet) {
        for (int i = 0; i < chevalet.getTuiles().size(); i++) {
            System.out.print(chevalet.getTuileAvecIndex(i).afficherLettre() + " ");
        }
        System.out.print("\n");
    }

    public static String inputStringScanner() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        return input;
    }

    public static int inputIntScanner() {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        return input;
    }
}
