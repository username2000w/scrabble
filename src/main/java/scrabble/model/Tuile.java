package scrabble.model;

public class Tuile {
    private String lettre;
    private final int points;

    public Tuile(String lettre, int points) {
        this.lettre = lettre;
        this.points = points;
    }

    public Tuile(LettreAlphabetFrancais lettre) {
    	this(lettre.afficherLettre(), lettre.points());
    }

    public Tuile() {
    	this(" ", 0);
    }

    public int points() {
    	return points;
    }

    public String lettre() {
    	return lettre;
    }

    public void remplacerLettre(String lettre) {
        this.lettre = lettre;
    }

    public boolean estJoker() {
    	return lettre.equals(" ") && points == 0;
    }
}
