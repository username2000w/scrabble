package scrabble.model;

import scrabble.model.utils.exception.SacVideException;

public class Joueur {
    private final Chevalet chevalet;
    private final String nom;
    private int score = 0;

    public Joueur(Chevalet chevalet, String nom) {
        this.chevalet = chevalet;
        this.nom = nom;
    }

    public String nom() {
        return nom;
    }

    public void echanger(Sac sac, int tuileIndex) throws SacVideException {
        chevalet.echanger(sac, tuileIndex);
    }

    public void retirerLettreDuChevalet(Tuile tuile) {
        chevalet.retirerLettre(tuile);
    }

    public void ajouterLettreAuChevalet(Tuile tuile) {
        chevalet.ajouterLettre(tuile);
    }

    public Chevalet chevalet() {
        return chevalet;
    }

    public void remplirChevalet(Sac sac) throws SacVideException {
        chevalet.remplirChevalet(sac);
    }
    
    public void ajouterScore(int scoreAjouter) {
    	score += scoreAjouter;
    }
    
    public int score() {
    	return score;
    }

}
