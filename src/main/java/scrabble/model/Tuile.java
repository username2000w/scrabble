package scrabble.model;

public class Tuile {
    protected LettreAlphabetFrancais lettre;

    public Tuile(LettreAlphabetFrancais lettre) {
        this.lettre = lettre;
    }

    public int getPoints() {
    	return lettre.getPoints();
    }

    @Override
    public String toString() {
    	return lettre.afficherLettre();
    }

    public void setLettre(LettreAlphabetFrancais lettre) {
        this.lettre = lettre;
    }

    public LettreAlphabetFrancais lettre() {
        return lettre;
    }

    public boolean estJoker() {
    	return this instanceof Joker;
    }
}
