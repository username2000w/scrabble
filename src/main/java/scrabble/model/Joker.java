package scrabble.model;

public class Joker extends Tuile {
    private String affichage = "JOKER";

    public Joker() {
        super(null);
    }

    @Override
    public int getPoints() {
    	return 0;
    }

    @Override
    public String afficherLettre() {
    	return affichage;
    }

    public void setLettreJoker(String affichage) {
    	this.affichage = affichage;
    }

    @Override
    public boolean estJoker() {
    	return true;
    }
}
