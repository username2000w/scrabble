package scrabble.gui;

import scrabble.model.Bonus;
import scrabble.model.Chevalet;
import scrabble.model.LettreAlphabetFrancais;
import scrabble.model.Plateau;

import java.util.Scanner;

public class Console {

    public static void afficherPlateau(Plateau plateau) {
        for (int i = 0; i < Plateau.TAILLE_PLATEAU_VERTICALE; i++) {
            for (int j = 0; j < Plateau.TAILLE_PLATEAU_HORIZONTALE; j++) {
                LettreAlphabetFrancais lettre = plateau.getPlateau()[i][j].getLettre();
                Bonus bonus = plateau.getPlateau()[i][j].getBonus();

                if (lettre == null) {
                    if (bonus == null) { // si la case est vide, on affiche un carré vide.
                        System.out.print("□ ");
                    } else {
                    	System.out.print(bonus);                    	
                    }
                } else {
                	System.out.print(lettre.afficherLettre() + " ");
                }
            }
            // Retour à la ligne.
            System.out.println();
        }
    }

    public static void afficherChevalet(Chevalet chevalet) {
        for (int i = 0; i < chevalet.getLettres().size(); i++) {
            System.out.print(chevalet.getLettreAvecIndex(i).toString() + " ");
        }
        System.out.print("\n");
    }

    public static String inputStringScanner() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        return input;
    }

    public static int inputIntScanner() {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        scanner.close();

        return input;
    }
}
