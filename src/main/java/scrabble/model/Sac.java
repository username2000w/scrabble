package scrabble.model;

import scrabble.model.utils.exception.SacVideException;

import java.util.ArrayList;

/**
 * Lors de l'instanciation, le sac est rempli de tuiles de scrabble
 * qui sont ensuite mélangées.
 */
public class Sac {
    private ArrayList<LettreAlphabetFrancais> lettres;

    private void melanger() {
        for (int i = 0; i < this.lettres.size(); i++) {
            int index = (int) (Math.random() * this.lettres.size());
            LettreAlphabetFrancais temp = this.lettres.get(i);
            this.lettres.set(i, this.lettres.get(index));
            this.lettres.set(index, temp);
        }
    }

    public void viderSac() {
        this.lettres.clear();
    }

    public Sac() {
        this.lettres = new ArrayList<>();

        // On ajout tous les jetons dans le sac.
        SacDeJeuFrancais sacDeJeuFrancais = new SacDeJeuFrancais();
        this.lettres = new ArrayList<>(sacDeJeuFrancais.getLettres());

        // On mélange le contenu du sac.
        this.melanger();
    }

    /**
     * Piocher la première tuile actuellement dans le sac.
     */
    public LettreAlphabetFrancais piocher() throws SacVideException {
        if (this.lettres.isEmpty()) {
            throw new SacVideException("Le sac est vide.");
        }

        return this.lettres.remove(0);
    }

    /**
     * Ajouter une tuile à la fin du sac.
     */
    public void ajouter(LettreAlphabetFrancais tuile) {
        lettres.add(tuile);
    }

    public int getNombreDeTuiles() {
        return this.lettres.size();
    }
}
