package scrabble.application;

import scrabble.model.Chevalet;
import scrabble.model.Joueur;
import scrabble.model.Sac;

public class ScrabbleJeuxEssais {
  public static void main(String[] args) {
    Sac sac = new Sac();
    Chevalet chevalet = new Chevalet(sac);
    Joueur joueur = new Joueur(chevalet, "Joueur 1");

    // On affiche les tuiles du joueur au début.
    afficherTuiles(joueur);

    // On échange la première tuile et on affiche.
    joueur.echanger(0);
    afficherTuiles(joueur);

    // On échange la troisième tuile et on affiche.
    joueur.echanger(2);
    afficherTuiles(joueur);
  }

  private static void afficherTuiles(Joueur joueur) {
    System.out.println(joueur.getNom() + " a les tuiles suivantes :");
    joueur.afficherChevalet();
  }
}