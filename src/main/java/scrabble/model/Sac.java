package scrabble.model;

import scrabble.model.utils.exception.SacVideException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Lors de l'instanciation, le sac est rempli de tuiles de scrabble
 * qui sont ensuite mélangées.
 */
public class Sac {
    private ArrayList<Tuile> tuiles;

    private void melanger() {
        Collections.shuffle(tuiles);
    }

    public void viderSac() {
        this.tuiles.clear();
    }

    public Sac() {
        this.tuiles = new ArrayList<>();

        // On ajout tous les jetons dans le sac.
        SacDeJeuFrancais sacDeJeuFrancais = new SacDeJeuFrancais();
        this.tuiles = new ArrayList<>(sacDeJeuFrancais.Lettres());

        // On mélange le contenu du sac.
        this.melanger();
    }

    /**
     * Piocher la première tuile actuellement dans le sac.
     */
    public Tuile piocher() throws SacVideException {
        if (this.tuiles.isEmpty()) {
            throw new SacVideException("Le sac est vide.");
        }

        Tuile tuile = this.tuiles.remove(0);
        melanger();
        return tuile;
    }

    /**
     * Ajouter une tuile à la fin du sac.
     */
    public void ajouter(Tuile tuile) {
        tuiles.add(tuile);
        melanger();
    }

    public int nombreDeTuiles() {
        return this.tuiles.size();
    }
}
