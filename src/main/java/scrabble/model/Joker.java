package scrabble.model;

import scrabble.gui.Console;

public class Joker extends Tuile {
    private LettreAlphabetFrancais lettre;

    public Joker() {
        super(null);
    }

    @Override
    public int getPoints() {
    	return 0;
    }

    @Override
    public String toString() {
    	return lettre == null ? "JOKER" : lettre.afficherLettre();
    }

    @Override
    public void setLettre(LettreAlphabetFrancais lettre) {
        this.lettre = lettre;
    }

    @Override
    public boolean estJoker() {
    	return true;
    }
}
