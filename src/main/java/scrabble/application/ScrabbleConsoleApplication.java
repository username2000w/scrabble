package scrabble.application;

import scrabble.gui.Console;
import scrabble.model.*;

public class ScrabbleConsoleApplication {

	public static void main(String[] args) {
		System.out.println("-------------------------------------------------------");
		System.out.println("-- Bienvenue dans notre magnifique jeu de scrabble ! --");
		System.out.println("-- développé par Maxime, Mikkel, Léo et Alexis       --");
		System.out.println("-------------------------------------------------------");

        Plateau plateau = new Plateau();
        Console.afficherPlateau(plateau);

        Sac sac = new Sac();
        Chevalet chevalet = new Chevalet();
        Joueur joueur = new Joueur(chevalet, "Joueur 1", plateau, sac);

        System.out.println(joueur.getNom() + " a les tuiles suivantes :");
        Console.afficherChevalet(joueur.getChevalet());

        joueur.jouerMot();

        Console.afficherPlateau(plateau);
	}
}
