package scrabble.application;

import scrabble.controller.MaitreDuJeu;
import scrabble.gui.Console;

public class ScrabbleConsoleApplication {

	public static void main(String[] args) {
		Console.afficherBienvenue();

        MaitreDuJeu maitreDuJeu = new MaitreDuJeu();
        boolean jeuEnCours = true;

        while (jeuEnCours) {
            jeuEnCours = maitreDuJeu.jouerTour();
        }

        Console.afficherFinPartie();
	}
}
