package scrabble.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scrabble.model.*;

import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {
    private Joueur joueur;
    private Sac sac;
    private Tuile tuileA;
    private Tuile tuileB;

    @BeforeEach
    void demarrage() {
        Chevalet chevalet = new Chevalet();
        joueur = new Joueur(chevalet, "Test");
        sac = new Sac();
        tuileA = new Tuile(LettreAlphabetFrancais.A);
        tuileB = new Tuile(LettreAlphabetFrancais.B);
    }

    @Test
    void testNom() {
        assertEquals("Test", joueur.nom());
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
    void testChevalet() {
        assertNotNull(joueur.chevalet());
    }

    @Test
    void testRemplirChevalet() {
        for (int i = 0; i < Chevalet.TAILLE; i++) {
            sac.ajouter(new Tuile(LettreAlphabetFrancais.A));
        }
        joueur.remplirChevalet(sac);
        assertEquals(Chevalet.TAILLE, joueur.chevalet().tuiles().size());
    }
}