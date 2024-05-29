package scrabble.model;

public class Tuile {
    private LettreAlphabetFrancais lettre;

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

    public boolean estJoker() {
    	return false;
    }
}
