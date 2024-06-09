package scrabble.model;

import java.util.Scanner;

import scrabble.model.utils.exception.SacVideException;

public class Joueur {
    private final Chevalet chevalet;
    private String nom;
    private int score = 0;

    public Joueur(Chevalet chevalet, String nom) {
        this.chevalet = chevalet;
        this.nom = nom;
    }

    public String nom() {
        return nom;
    }

    public void echanger(Sac sac, int tuileIndex) throws SacVideException {
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

    public void remplirChevalet(Sac sac) throws SacVideException {
        chevalet.remplirChevalet(sac);
    }
    
    public void ajouterScore(int scoreAjouter) {
    	score += scoreAjouter;
    }
    
    public int score() {
    	return score;
    }
    
    public void changerNom() {
    	System.out.println("Nom du joueur: " );
        Scanner scanner = new Scanner(System.in);
        String nouveauNom = scanner.nextLine();
        this.nom = nouveauNom;
    }

}
