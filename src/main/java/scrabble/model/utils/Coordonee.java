package scrabble.model.utils;

public class Coordonee {
    private final int ligne;
    private final int colonne;

    public Coordonee(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public int ligne() {
        return ligne;
    }

    public int colonne() {
        return colonne;
    }
}
