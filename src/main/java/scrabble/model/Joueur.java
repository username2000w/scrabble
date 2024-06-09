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
        chevalet.echanger(sac, tuileIndex);
    }

    public void retirerLettreDuChevalet(Tuile tuile) {
        chevalet.retirerLettre(tuile);
    }

    public void ajouterLettreAuChevalet(Tuile tuile) {
        chevalet.ajouterLettre(tuile);
    }

    public Chevalet chevalet() {
        return chevalet;
    }

    public void remplirChevalet(Sac sac) {
        chevalet.remplirChevalet(sac);
    }
}
