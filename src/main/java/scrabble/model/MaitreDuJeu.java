package scrabble.model;

import scrabble.model.utils.exception.SacVideException;

public class MaitreDuJeu {

    private Sac sac;
    private Plateau pleateau;
    private final Joueur joueur;

    public MaitreDuJeu() {
        this.sac = new Sac();
        this.pleateau = new Plateau();
        this.joueur = new Joueur(new Chevalet(), "Joueur 1", pleateau);
        // v3 => Compter plusieurs joueurs
    }
}
