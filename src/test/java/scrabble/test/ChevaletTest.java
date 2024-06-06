package scrabble.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scrabble.model.Chevalet;
import scrabble.model.LettreAlphabetFrancais;
import scrabble.model.Sac;
import scrabble.model.Tuile;

import static org.junit.jupiter.api.Assertions.*;

public class ChevaletTest {
    private final Chevalet chevalet = new Chevalet();
    private final Sac sac = new Sac();
    private Tuile tuileA = new Tuile(LettreAlphabetFrancais.A);
    private Tuile tuileB = new Tuile(LettreAlphabetFrancais.B);

    @Test
    void testTailleInitiale() {
        // Test avec un chevalet vide
        int nombreDeTuiles = chevalet.tuiles().size();
        boolean estVide = chevalet.tuiles().isEmpty();

        assertEquals(0, nombreDeTuiles);
        assertTrue(estVide);
    }

    @Test
    void testPiocher() {
        // Test avec une tuile A
        sac.viderSac();
        sac.ajouter(tuileA);
        chevalet.piocher(sac);
        assertTrue(chevalet.tuiles().contains(tuileA));

        sac.viderSac();
        chevalet.piocher(sac);
        assertEquals(1, chevalet.tuiles().size());
    }

    @Test
    void testEchanger() {
        // Test avec une tuile A et une tuile B
        sac.viderSac();
        chevalet.tuiles().clear();

        sac.ajouter(tuileA);
        chevalet.piocher(sac);

        assertTrue(chevalet.tuiles().contains(tuileA));

        sac.ajouter(tuileB);

        Tuile echange = chevalet.echanger(sac, 0);

        assertEquals(tuileA, echange);
        assertTrue(chevalet.tuiles().contains(tuileB));
        assertFalse(chevalet.tuiles().contains(tuileA));
    }

    @Test
    void testTuileAvecIndex() {
        // Test avec une tuile A
        chevalet.tuiles().add(tuileA);
        assertEquals(tuileA, chevalet.tuileAvecIndex(0));
    }

    @Test
    void testRemplirChevalet() {
        // Remplir le chevalet avec des tuiles du
        sac.viderSac();
        for (int i = 0; i < Chevalet.TAILLE; i++) {
            sac.ajouter(new Tuile(LettreAlphabetFrancais.A));
        }
        chevalet.remplirChevalet(sac);
        assertEquals(Chevalet.TAILLE, chevalet.tuiles().size());
    }

    @Test
    void testTuileAvecLettre() {
        // Test avec une tuile A
        chevalet.tuiles().add(tuileA);

        assertEquals(tuileA, chevalet.tuileAvecLettre("A"));
        assertNull(chevalet.tuileAvecLettre("B"));
    }

    @Test
    void testRetirerLettre() {
        // Test avec une tuile A
        chevalet.tuiles().clear();
        chevalet.tuiles().add(tuileA);

        assertTrue(chevalet.tuiles().contains(tuileA));

        chevalet.retirerLettre(tuileA);

        assertFalse(chevalet.tuiles().contains(tuileA));

        chevalet.retirerLettre(new Tuile(LettreAlphabetFrancais.B));
    }
}
