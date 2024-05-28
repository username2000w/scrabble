package scrabble.model;

import java.util.ArrayList;
import java.util.Scanner;

import scrabble.gui.Console;
import scrabble.model.utils.Coordonee;
import scrabble.model.utils.exception.HorsPlateauException;

public class Joueur {
    private Chevalet chevalet;
    private final String nom;
    private String joker = "";
    private int compteurJoker = 0;
    

    public Joueur(Chevalet chevalet, String nom) {
        this.chevalet = chevalet;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void echanger(Sac sac, int tuileIndex) {
        this.chevalet.echanger(sac, tuileIndex);
    }

    public void retirerLettreDuChevalet(LettreAlphabetFrancais lettre) {
        this.chevalet.retirerLettre(lettre);
    }

    public void placerEtRetirerLettre(LettreAlphabetFrancais lettre, Plateau plateau, Coordonee coordonee, String affichage) {
    	try {
            if (joker.equals("JOKER")) {
                if (compteurJoker == 0) {
                    plateau.placerlettreJoker(coordonee, affichage, compteurJoker);
                } else if (compteurJoker == 1) {
                    plateau.placerlettreJoker(coordonee, affichage, compteurJoker);
                } else {
                    System.out.println("Vous ne pouvez pas placer plus de 2 jokers");
                }
                retirerLettreDuChevalet(LettreAlphabetFrancais.JOKER);
                compteurJoker++;
                joker = "";
            } else {
                plateau.placerlettre(lettre, coordonee);
                chevalet.retirerLettre(lettre);
            }

        } catch (NumberFormatException | HorsPlateauException e) {
            e.printStackTrace();
        }
    }

    public Chevalet getChevalet() {
        return chevalet;
    }

    public void remplirChevalet(Sac sac) {
        this.chevalet.remplirChevalet(sac);
    }
}
