package scrabble.application;

import scrabble.gui.Console;
import scrabble.model.Chevalet;
import scrabble.model.Joueur;
import scrabble.model.Plateau;
import scrabble.model.Sac;

public class ScrabbleJeuxEssais {
    public static void main(String[] args) {
        Sac sac = new Sac();
        Chevalet chevalet = new Chevalet();
        Joueur joueur = new Joueur(chevalet, "Joueur 1");
        joueur.remplirChevalet(sac);

        System.out.println("On affiche les tuiles du joueur au début.");
        afficherTuiles(joueur);

        System.out.println("On échange la première tuile et on affiche.");
        joueur.echanger(sac,0);
        afficherTuiles(joueur);

        System.out.println("On échange la troisième tuile et on affiche.");
        joueur.echanger(sac,2);
        afficherTuiles(joueur);
    }

    private static void afficherTuiles(Joueur joueur) {
        System.out.println(joueur.getNom() + " a les tuiles suivantes :");
        Console.afficherChevalet(joueur.getChevalet());
    }
}
