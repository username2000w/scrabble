package scrabble.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scrabble.model.*;
import scrabble.model.utils.exception.SacVideException;

import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {
    private Joueur joueur;
    private Sac sac;
    private Tuile tuileA;
    private Tuile tuileB;

    @BeforeEach
    void demarrage() {
        Chevalet chevalet = new Chevalet();
        joueur = new Joueur(chevalet, "Joueur1");
        sac = new Sac();
        tuileA = new Tuile(LettreAlphabetFrancais.A);
        tuileB = new Tuile(LettreAlphabetFrancais.B);
    }

    @Test
    void testNom() {
        assertEquals("Joueur1", joueur.nom());
    }

    // TODO : faire fonctionner ce test
    /*@Test
    void testEchanger() {
        sac.ajouter(tuileA);
        sac.ajouter(tuileB);
        joueur.chevalet().piocher(sac);
        joueur.echanger(sac, 0);
        assertTrue(joueur.chevalet().tuiles().contains(tuileB));
    }*/

    @Test
    void testRetirerLettreDuChevalet() {
        joueur.chevalet().tuiles().add(tuileA);
        joueur.retirerLettreDuChevalet(tuileA);
        assertFalse(joueur.chevalet().tuiles().contains(tuileA));
    }

    @Test
    void testAjouterLettreAuChevalet() {
        joueur.ajouterLettreAuChevalet(tuileA);
        assertTrue(joueur.chevalet().tuiles().contains(tuileA));
    }

    @Test
    void testChevalet() {
        assertNotNull(joueur.chevalet());
    }

    @Test
    void testRemplirChevalet() {
        for (int i = 0; i < Chevalet.TAILLE; i++) {
            sac.ajouter(new Tuile(LettreAlphabetFrancais.A));
        }
        try {
            joueur.remplirChevalet(sac);
        } catch (SacVideException e) {
            throw new RuntimeException(e);
        }
        assertEquals(Chevalet.TAILLE, joueur.chevalet().tuiles().size());
    }

    @Test
    void testAjouterScore() {
        assertEquals(0, joueur.score());

        joueur.ajouterScore(10);
        assertEquals(10, joueur.score());

        joueur.ajouterScore(5);
        assertEquals(15, joueur.score());
    }
}