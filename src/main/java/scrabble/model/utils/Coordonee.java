package scrabble.model.utils;

public class Coordonee {
    private final int ligne;
    private final int colonne;

    public Coordonee(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }
}
