package scrabble.controller;

import scrabble.model.*;
import scrabble.vues.PartieVue;

public class PartieController {
    private final PartieVue root;
    private final Joueur joueur;

    public PartieController (Plateau plateau, Joueur joueur, Sac sac, PartieVue root) {
        this.root = root;
        this.joueur = joueur;
    }
}
