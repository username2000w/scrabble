package scrabble.application;

import scrabble.controller.MaitreDuJeu;
import scrabble.gui.Console;
import scrabble.model.*;
import scrabble.model.utils.Coordonee;
import scrabble.model.utils.Direction;
import scrabble.model.utils.Score;
import scrabble.model.utils.exception.SacVideException;

public class ScrabbleJeuxEssais {
    public static void main(String[] args) {
        MaitreDuJeu maitreDuJeu = new MaitreDuJeu();
        Joueur joueur = maitreDuJeu.joueur();
        Sac sac = maitreDuJeu.sac();
        Plateau plateau = maitreDuJeu.plateau();

        System.out.println("On affiche les tuiles du joueur au début.");
        afficherTuiles(joueur);

        System.out.println("On échange la première tuile et on affiche.");

        try {
            joueur.echanger(sac,0);
        } catch (SacVideException e) {
            System.out.println("Le sac est vide.");
        }
        afficherTuiles(joueur);

        System.out.println("On échange la troisième tuile et on affiche.");
        try {
            joueur.echanger(sac,2);
        } catch (SacVideException e) {
            System.out.println("Le sac est vide.");
        }
        afficherTuiles(joueur);

        System.out.println("On place un mot sur le plateau.");
        Mot motBonjour = new Mot(new Coordonee(4,7), Direction.VERTICAL);
        motBonjour.ajouterLettre(new Tuile(LettreAlphabetFrancais.P));
        motBonjour.ajouterLettre(new Tuile(LettreAlphabetFrancais.A));
        motBonjour.ajouterLettre(new Tuile(LettreAlphabetFrancais.T));
        motBonjour.ajouterLettre(new Tuile(LettreAlphabetFrancais.E));
        maitreDuJeu.placerMotSurPlateau(motBonjour, plateau);
        Console.afficherPlateau(plateau);

        System.out.println("On affiche le score du mot.");
        System.out.println(Score.calculerScoreMot(motBonjour, plateau));

        System.out.println("On place le mot 'sel' sur le plateau en dessous de 'pate'.");
        Mot motSel = new Mot(new Coordonee(8,7), Direction.HORIZONTAL);
        motSel.ajouterLettre(new Tuile(LettreAlphabetFrancais.S));
        motSel.ajouterLettre(new Tuile(LettreAlphabetFrancais.E));
        motSel.ajouterLettre(new Tuile(LettreAlphabetFrancais.L));
        maitreDuJeu.placerMotSurPlateau(motSel, plateau);
        Console.afficherPlateau(plateau);

        System.out.println("On affiche le score du mot.");
        System.out.println(Score.calculerScoreMot(motSel, plateau));
        System.out.println("On a bien le score du mot 'sel' qui est 4 mais vu qu'on a completé le mot 'pate' " +
                "on récupere ses points aussi qui sont de 6, ce qui fais bien 4 + 6 = 10.");
    }

    private static void afficherTuiles(Joueur joueur) {
        System.out.println(joueur.nom() + " a les tuiles suivantes :");
        Console.afficherChevalet(joueur.chevalet());
    }
}
