package scrabble.model;

public class Joker extends Tuile {
    public Joker() {
        super(null);
    }

    @Override
    public int points() {
    	return 0;
    }

    @Override
    public String toString() {
    	return lettre == null ? " " : lettre.afficherLettre();
    }
}