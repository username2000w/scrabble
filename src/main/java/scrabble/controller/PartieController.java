package scrabble.controller;

import scrabble.gui.utils.ChevaletVueUtilitaire;
import scrabble.model.*;
import scrabble.vues.PartieVue;

public class PartieController {
    private final PartieVue root;
    private final Joueur joueur;

    public PartieController (Plateau plateau, Joueur joueur, Sac sac, PartieVue root) {
        this.root = root;
        this.joueur = joueur;

        // On remplit le sac du joueur au début de la partie.
        joueur.remplirChevalet(sac);
        // On actualise le contenu dans l'IHM.
        ChevaletVueUtilitaire.rafraichirContenu(root, joueur);
    }
}
