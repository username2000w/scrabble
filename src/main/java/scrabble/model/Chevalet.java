package scrabble.model;

import scrabble.model.utils.exception.SacVideException;

import java.util.ArrayList;

public class Chevalet {
    private ArrayList<LettreAlphabetFrancais> lettres;
    private static final int TAILLE = 7;

    public Chevalet() {
        this.lettres = new ArrayList<>();
    }

    public void piocher(Sac sac) {
        try {
            this.lettres.add(sac.piocher());
        } catch (SacVideException e) {
            System.out.println(e.getMessage());
        }
    }

    public void echanger(Sac sac, int tuileIndex) {
        LettreAlphabetFrancais lettre = this.lettres.remove(tuileIndex);
        sac.ajouter(lettre);
        this.piocher(sac);
    }

    public void retirerLettre(LettreAlphabetFrancais lettre) {
        if (lettres.contains(lettre)) {
            lettres.remove(lettre);
        } else {
            System.out.println("La lettre spécifiée n'est pas présente dans le chevalet.");
        }
    }

    public LettreAlphabetFrancais getLettreAvecIndex(int tuileIndex) {
        return this.lettres.get(tuileIndex);
    }

    public ArrayList<LettreAlphabetFrancais> getLettres() {
        return this.lettres;
    }

    public void remplirChevalet(Sac sac) {
        for (int tailleActuelle = this.getLettres().size(); tailleActuelle < TAILLE; tailleActuelle++) {
            this.piocher(sac);
        }
    }

    public LettreAlphabetFrancais getTuileAvecLettre(String lettreVoulue) {
        for (LettreAlphabetFrancais lettre : lettres) {
            if (lettreVoulue.equals(lettre.toString())) {
                return lettre;
            }
        }
        return null;
    }
}
