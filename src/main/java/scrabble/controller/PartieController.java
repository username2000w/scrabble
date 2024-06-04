package scrabble.controller;

import scrabble.model.*;
import scrabble.vues.ChevaletVue;
import scrabble.vues.PartieVue;

public class PartieController {
    public PartieController (Plateau plateau, Joueur joueur, Sac sac, PartieVue vue) {
        joueur.remplirChevalet(sac);
        Chevalet chevalet = joueur.getChevalet();
        ChevaletVue chevaletVue = vue.chevalet();

        for (LettreAlphabetFrancais lettre : chevalet.getLettres()) {
            chevaletVue.ajouterLettre(lettre.toString(), lettre.getPoints());
        }
    }
}
