package scrabble.model;

import scrabble.model.utils.exception.SacVideException;

import java.util.ArrayList;

/**
 * Lors de l'instanciation, le sac est rempli de tuiles de scrabble
 * qui sont ensuite mélangées.
 */
public class Sac {
    private ArrayList<Tuile> tuiles;

    private void melanger() {
        for (int i = 0; i < this.tuiles.size(); i++) {
            int index = (int) (Math.random() * this.tuiles.size());
            Tuile temp = this.tuiles.get(i);
            this.tuiles.set(i, this.tuiles.get(index));
            this.tuiles.set(index, temp);
        }
    }

    public void viderSac() {
        this.tuiles.clear();
    }

    public Sac() {
        this.tuiles = new ArrayList<>();

        // On ajout tous les jetons dans le sac.
        SacDeJeuFrancais sacDeJeuFrancais = new SacDeJeuFrancais();
        this.tuiles = new ArrayList<>(sacDeJeuFrancais.getLettres());

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

        return this.tuiles.remove(0);
    }

    /**
     * Ajouter une tuile à la fin du sac.
     */
    public void ajouter(Tuile tuile) {
        tuiles.add(tuile);
    }

    public int nombreDeTuiles() {
        return this.tuiles.size();
    }
}
