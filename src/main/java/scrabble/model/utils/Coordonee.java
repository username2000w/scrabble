package scrabble.model.utils;

import java.util.Objects;

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

    @Override
    public String toString() {
        return ligne + "," + colonne;
    }

    @Override
    public int hashCode() {
        return Objects.hash(colonne, ligne);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coordonee other = (Coordonee) obj;
        return Objects.equals(colonne, other.colonne) && Objects.equals(ligne, other.ligne);
    }
}
