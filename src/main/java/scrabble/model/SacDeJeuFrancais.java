package scrabble.model;

import java.util.ArrayList;
import java.util.List;

public class SacDeJeuFrancais {
    private final ArrayList<Tuile> tuiles = new ArrayList<>();

    private void faireTuilesPour(int nombreDeTuile, LettreAlphabetFrancais lettre) {
        for (int i = 0; i < nombreDeTuile; i++) {
            this.tuiles.add(new Tuile(lettre));
        }
    }

    private void faireTuileJoker(int nombreDeTuile) {
        for (int i = 0; i < nombreDeTuile; i++) {
            this.tuiles.add(new Tuile());
        }
    }

    public SacDeJeuFrancais() { 
        this.faireTuilesPour(15, LettreAlphabetFrancais.E);
        this.faireTuilesPour(9, LettreAlphabetFrancais.A);
        this.faireTuilesPour(8, LettreAlphabetFrancais.I);
        this.faireTuilesPour(6, LettreAlphabetFrancais.N);
        this.faireTuilesPour(6, LettreAlphabetFrancais.O);
        this.faireTuilesPour(6, LettreAlphabetFrancais.R);
        this.faireTuilesPour(6, LettreAlphabetFrancais.S);
        this.faireTuilesPour(6, LettreAlphabetFrancais.T);
        this.faireTuilesPour(6, LettreAlphabetFrancais.U);
        this.faireTuilesPour(5, LettreAlphabetFrancais.L);
        this.faireTuilesPour(3, LettreAlphabetFrancais.D);
        this.faireTuilesPour(2, LettreAlphabetFrancais.G);
        this.faireTuilesPour(3, LettreAlphabetFrancais.M);
        this.faireTuilesPour(2, LettreAlphabetFrancais.B);
        this.faireTuilesPour(2, LettreAlphabetFrancais.C);
        this.faireTuilesPour(2, LettreAlphabetFrancais.P);
        this.faireTuilesPour(2, LettreAlphabetFrancais.F);
        this.faireTuilesPour(2, LettreAlphabetFrancais.H);
        this.faireTuilesPour(2, LettreAlphabetFrancais.V);
        this.faireTuileJoker(2);
        this.faireTuilesPour(1, LettreAlphabetFrancais.J);
        this.faireTuilesPour(1, LettreAlphabetFrancais.Q);
        this.faireTuilesPour(1, LettreAlphabetFrancais.K);
        this.faireTuilesPour(1, LettreAlphabetFrancais.W);
        this.faireTuilesPour(1, LettreAlphabetFrancais.X);
        this.faireTuilesPour(1, LettreAlphabetFrancais.Y);
        this.faireTuilesPour(1, LettreAlphabetFrancais.Z);
    }

    public List<Tuile> lettres() {
        return this.tuiles;
    }
}
