package scrabble.model;

public class Joueur {
    private final Chevalet chevalet;
    private final String nom;

    public Joueur(Chevalet chevalet, String nom) {
        this.chevalet = chevalet;
        this.nom = nom;
    }

    public String nom() {
        return nom;
    }

    public void echanger(Sac sac, int tuileIndex) {
        this.chevalet.echanger(sac, tuileIndex);
    }

    public void retirerLettreDuChevalet(Tuile tuile) {
        this.chevalet.retirerLettre(tuile);
    }

    public Chevalet chevalet() {
        return chevalet;
    }

    public void remplirChevalet(Sac sac) {
        this.chevalet.remplirChevalet(sac);
    }
}
