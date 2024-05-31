package scrabble.model;

public class Tuile {
    protected LettreAlphabetFrancais lettre;

    public Tuile(LettreAlphabetFrancais lettre) {
        this.lettre = lettre;
    }

    public int points() {
    	return lettre.points();
    }

    @Override
    public String toString() {
    	return lettre.afficherLettre();
    }

    public void setLettre(LettreAlphabetFrancais lettre) {
        this.lettre = lettre;
    }

    public boolean estJoker() {
    	return this instanceof Joker;
    }
}
