package scrabble.model;

import scrabble.model.utils.exception.SacVideException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chevalet {
    private final ArrayList<Tuile> tuiles;
    public static final int TAILLE = 7;

    public Chevalet() {
        this.tuiles = new ArrayList<>();
    }

    public void piocher(Sac sac) throws SacVideException {
        this.tuiles.add(sac.piocher());
    }

    public Tuile echanger(Sac sac, int tuileIndex) throws SacVideException {
        Tuile tuile = this.tuiles.remove(tuileIndex);
        sac.ajouter(tuile);
        this.piocher(sac);
        return tuile;
    }

    public void ajouterLettre(Tuile lettre) {
        if (tuiles.size() < TAILLE) {
            tuiles.add(lettre);
        }
    }

    public void retirerLettre(Tuile lettre) {
        if (tuiles.contains(lettre)) {
            tuiles.remove(lettre);
        }
    }

    public Tuile tuileAvecIndex(int tuileIndex) {
        return this.tuiles.get(tuileIndex);
    }

    public List<Tuile> tuiles() {
        return this.tuiles;
    }

    public void remplirChevalet(Sac sac) throws SacVideException {
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
    
    public void melangerChevalet() {
        Collections.shuffle(tuiles);
    }
}
