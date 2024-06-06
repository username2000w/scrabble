package scrabble.model;

import scrabble.model.utils.exception.SacVideException;

import java.util.ArrayList;

public class Chevalet {
    private final ArrayList<Tuile> tuiles;
    public static final int TAILLE = 7;

    public Chevalet() {
        this.tuiles = new ArrayList<>();
    }

    public void piocher(Sac sac) {
        try {
            this.tuiles.add(sac.piocher());
        } catch (SacVideException e) {
            System.out.println(e.getMessage());
        }
    }

    public Tuile echanger(Sac sac, int tuileIndex) {
        Tuile tuile = this.tuiles.remove(tuileIndex);
        sac.ajouter(tuile);
        this.piocher(sac);
        return tuile;
    }

    public void retirerLettre(Tuile lettre) {
        if (tuiles.contains(lettre)) {
            tuiles.remove(lettre);
        } else {
            System.out.println("La lettre spécifiée n'est pas présente dans le chevalet.");
        }
    }

    public Tuile tuileAvecIndex(int tuileIndex) {
        return this.tuiles.get(tuileIndex);
    }

    public ArrayList<Tuile> tuiles() {
        return this.tuiles;
    }

    public void remplirChevalet(Sac sac) {
        for (int tailleActuelle = this.tuiles().size(); tailleActuelle < TAILLE; tailleActuelle++) {
            this.piocher(sac);
        }
    }

    public Tuile tuileAvecLettre(String tuileVoulue) {
        for (Tuile tuile : tuiles) {
            if (tuileVoulue.equals(tuile.toString())) {
                return tuile;
            }
        }
        return null;
        
    }
}
