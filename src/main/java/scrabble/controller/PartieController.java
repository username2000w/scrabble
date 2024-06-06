package scrabble.controller;

import scrabble.model.*;
import scrabble.vues.ChevaletVue;
import scrabble.vues.PartieVue;

public class PartieController {
    public PartieController (Plateau plateau, Joueur joueur, Sac sac, PartieVue vue) {
        joueur.remplirChevalet(sac);
        Chevalet chevalet = joueur.chevalet();
        ChevaletVue chevaletVue = vue.chevalet();

        for (Tuile tuile : chevalet.tuiles()) {
            chevaletVue.ajouterLettre(tuile);
        }
    }
}
