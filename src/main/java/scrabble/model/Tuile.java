package scrabble.model;

public class Tuile {
    private final LettreAlphabetFrancais lettre;

    public Tuile(LettreAlphabetFrancais lettre) {
        this.lettre = lettre;
    }

    public LettreAlphabetFrancais getLettre() {
        return lettre;
    }

    public int getPoints() {
    	return lettre.getPoints();
    }

    public String afficherLettre() {
    	return lettre.afficherLettre();
    }

    public void setLettreJoker(String affichage) {
    	// Do nothing
    }

    public boolean estJoker() {
    	return false;
    }
}
