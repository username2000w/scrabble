package scrabble.gui;

import scrabble.model.Bonus;
import scrabble.model.Chevalet;
import scrabble.model.Plateau;
import scrabble.model.Tuile;

import java.util.Scanner;

public class Console {
    public static void afficherBienvenue() {
        Console.message("-------------------------------------------------------");
        Console.message("-- Bienvenue dans notre magnifique jeu de scrabble ! --");
        Console.message("-- développé par Maxime, Mikkel, Léo et Alexis       --");
        Console.message("-------------------------------------------------------");
    }

    public static void afficherFinPartie() {
        Console.message("------------------------");
        Console.message("-- FIN DE LA PARTIE ! --");
        Console.message("------------------------");
        Console.message("Merci d'avoir joué !");
    }

    public static void message(String message) {
        System.out.println(message);
    }

    public static void afficherPlateau(Plateau plateau) {
        for (int ligne = 0; ligne < Plateau.TAILLE_PLATEAU_VERTICALE; ligne++) {
            for (int colonne = 0; colonne < Plateau.TAILLE_PLATEAU_HORIZONTALE; colonne++) {
                Tuile tuile = plateau.plateau()[ligne][colonne].tuile();
                Bonus bonus = plateau.plateau()[ligne][colonne].bonus();

                if (tuile == null) {
                    if (bonus == null) { // si la case est vide, on affiche un carré vide.
                        System.out.print("□ ");
                    } else {
                    	System.out.print(bonus);
                    }
                } else {
                	System.out.print(tuile + " ");
                }
            }
            // Retour à la ligne.
            Console.message("");
        }
    }

    public static void afficherChevalet(Chevalet chevalet) {
        for (int i = 0; i < chevalet.tuiles().size(); i++) {
            System.out.print(chevalet.tuileAvecIndex(i).toString() + " ");
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
