package scrabble.test;

import org.junit.jupiter.api.Test;
import scrabble.controller.MaitreDuJeu;
import scrabble.model.LettreAlphabetFrancais;
import scrabble.model.Mot;
import scrabble.model.Plateau;
import scrabble.model.Tuile;
import scrabble.model.utils.Coordonee;
import scrabble.model.utils.Direction;
import scrabble.model.utils.Score;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreTest {

    @Test
    void testMotSelEtPate() {
        new MaitreDuJeu();
        MaitreDuJeu maitreDuJeu = new MaitreDuJeu();
        Plateau plateau = maitreDuJeu.plateau();

        Mot motBonjour = new Mot(new Coordonee(4,7), Direction.VERTICAL);
        motBonjour.ajouterLettre(new Tuile(LettreAlphabetFrancais.P));
        motBonjour.ajouterLettre(new Tuile(LettreAlphabetFrancais.A));
        motBonjour.ajouterLettre(new Tuile(LettreAlphabetFrancais.T));
        motBonjour.ajouterLettre(new Tuile(LettreAlphabetFrancais.E));
        maitreDuJeu.placerMotSurPlateau(motBonjour, plateau);

        Mot motSel = new Mot(new Coordonee(8,7), Direction.HORIZONTAL);
        motSel.ajouterLettre(new Tuile(LettreAlphabetFrancais.S));
        motSel.ajouterLettre(new Tuile(LettreAlphabetFrancais.E));
        motSel.ajouterLettre(new Tuile(LettreAlphabetFrancais.L));
        maitreDuJeu.placerMotSurPlateau(motSel, plateau);

        assertEquals(10, Score.calculerScoreMot(motSel, plateau));
    }

    @Test
    void testMotPate() {
        new MaitreDuJeu();
        MaitreDuJeu maitreDuJeu = new MaitreDuJeu();
        Plateau plateau = maitreDuJeu.plateau();

        Mot motPate = new Mot(new Coordonee(4,7), Direction.VERTICAL);
        motPate.ajouterLettre(new Tuile(LettreAlphabetFrancais.P));
        motPate.ajouterLettre(new Tuile(LettreAlphabetFrancais.A));
        motPate.ajouterLettre(new Tuile(LettreAlphabetFrancais.T));
        motPate.ajouterLettre(new Tuile(LettreAlphabetFrancais.E));
        maitreDuJeu.placerMotSurPlateau(motPate, plateau);

        assertEquals(6 * 2, Score.calculerScoreMot(motPate, plateau));
    }

    @Test
    void testMotSel() {
        new MaitreDuJeu();
        MaitreDuJeu maitreDuJeu = new MaitreDuJeu();
        Plateau plateau = maitreDuJeu.plateau();

        Mot motSel = new Mot(new Coordonee(8,7), Direction.HORIZONTAL);
        motSel.ajouterLettre(new Tuile(LettreAlphabetFrancais.S));
        motSel.ajouterLettre(new Tuile(LettreAlphabetFrancais.E));
        motSel.ajouterLettre(new Tuile(LettreAlphabetFrancais.L));
        maitreDuJeu.placerMotSurPlateau(motSel, plateau);

        assertEquals(4, Score.calculerScoreMot(motSel, plateau));
    }
}
