package scrabble.test;

import org.junit.jupiter.api.Test;

import scrabble.model.LettreAlphabetFrancais;
import scrabble.model.Tuile;

import static org.junit.jupiter.api.Assertions.*;

class TuileTest {

    @Test
    void testConstructorEtToString() {
        Tuile tuile = new Tuile(LettreAlphabetFrancais.A);
        assertEquals("A", tuile.toString());
    }

    @Test
    void testPoints() {
        Tuile tuileB = new Tuile(LettreAlphabetFrancais.B);
        assertEquals(3, tuileB.points());

        Tuile tuileA = new Tuile(LettreAlphabetFrancais.A);
        assertEquals(1, tuileA.points());
    }

    @Test
    void testChangerLettre() {
        Tuile tuile = new Tuile(LettreAlphabetFrancais.A);
        assertEquals("A", tuile.toString());
        
        tuile = new Tuile(LettreAlphabetFrancais.B);
        assertEquals("B", tuile.toString());
    }
}