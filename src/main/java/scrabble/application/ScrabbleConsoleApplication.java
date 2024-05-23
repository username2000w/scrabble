package scrabble.application;

import java.util.ArrayList;

import scrabble.controller.MaitreDuJeu;
import scrabble.gui.Console;
import scrabble.model.*;

public class ScrabbleConsoleApplication {

	public static void main(String[] args) {
		System.out.println("-------------------------------------------------------");
		System.out.println("-- Bienvenue dans notre magnifique jeu de scrabble ! --");
		System.out.println("-- développé par Maxime, Mikkel, Léo et Alexis       --");
		System.out.println("-------------------------------------------------------");

        MaitreDuJeu maitreDuJeu = new MaitreDuJeu();
        for (int tour = 0; maitreDuJeu.jouerTour(); tour++) { // GAME LOOP
            System.out.println("TOUR " + tour);
        }
        System.out.println("------------------------");
        System.out.println("-- FIN DE LA PARTIE ! --");
        System.out.println("------------------------");
        System.out.println("Merci d'avoir joué !");
	}
}
