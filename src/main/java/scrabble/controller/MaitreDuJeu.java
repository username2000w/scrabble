package scrabble.controller;

import scrabble.gui.Console;
import scrabble.model.Chevalet;
import scrabble.model.Joueur;
import scrabble.model.Plateau;
import scrabble.model.Sac;

import java.util.ArrayList;

public class MaitreDuJeu {

    private Sac sac;
    private Plateau plateau;
    private Joueur joueur;

    public MaitreDuJeu() {
        this.sac = new Sac();
        this.plateau = new Plateau();
        this.joueur = new Joueur(new Chevalet(), "Joueur 1", plateau, sac);
        // v3 => Compter plusieurs joueurs
    }

    public void jouerTour() {
        System.out.println(joueur.getNom() + " a les tuiles suivantes :");
        Console.afficherChevalet(joueur.getChevalet());


        ArrayList<Integer> coordonnees = joueur.jouerMot();
        System.out.println(joueur.calculerScoreMot(coordonnees.get(0), coordonnees.get(1)));
        Console.afficherPlateau(plateau);
    }
}
