package scrabble.application;

import scrabble.model.*;
import scrabble.model.utils.exception.SacVideException;

public class ScrabbleConsoleApplication {

	public static void main(String[] args) {
		System.out.println("-------------------------------------------------------");
		System.out.println("-- Bienvenue dans notre magnifique jeu de scrabble ! --");
		System.out.println("-- développé par Maxime, Mikkel, Léo et Alexis       --");
		System.out.println("-------------------------------------------------------");

        Plateau plateau = new Plateau();
        plateau.afficher();

        Sac sac = new Sac();
        Chevalet chevalet = new Chevalet(sac);
        Joueur joueur = new Joueur(chevalet, "Joueur 1", plateau);

        System.out.println(joueur.getNom() + " a les tuiles suivantes :");
        joueur.afficherChevalet();

        joueur.jouerlettre();
	}
}
