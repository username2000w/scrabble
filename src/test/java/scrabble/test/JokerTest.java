package scrabble.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import scrabble.model.*;

import static org.junit.jupiter.api.Assertions.*;

class JokerTest {
    private Tuile estUnJokerVide;
    private Tuile estUnJokerJ;
    private Tuile estUnJokerK;
    private Tuile nEstPasUnJoker;

    @BeforeEach
    void debut() {
        // Initialisation des variables
        estUnJokerVide = new Tuile();

        estUnJokerJ = new Tuile();
        estUnJokerJ.remplacerLettre("J");

        estUnJokerK = new Tuile();
        estUnJokerK.remplacerLettre("K");

        nEstPasUnJoker = new Tuile(LettreAlphabetFrancais.B);
    }

    @Test
    void testEstJoker() {
        // Teste si la tuile est un joker
        assertTrue(estUnJokerVide.estJoker());
        assertTrue(estUnJokerJ.estJoker());
        assertTrue(estUnJokerK.estJoker());
    }

    @Test
    void testNestPasUnJoker() {
        // Teste si la tuile n'est pas un joker
        assertFalse(nEstPasUnJoker.estJoker());
    }

    @Test
    void testAffichageJoker() {
        // Teste l'affichage d'un joker
        assertEquals(" ", estUnJokerVide.lettre());
        assertEquals("J", estUnJokerJ.lettre());
        assertEquals("K", estUnJokerK.lettre());
    }

    @Test
    void testAffichageLettre() {
        // Teste l'affichage d'une lettre
        assertEquals("B", nEstPasUnJoker.lettre());
    }
}
