package scrabble.test;

import org.junit.jupiter.api.Test;

import scrabble.model.Joker;
import scrabble.model.LettreAlphabetFrancais;
import scrabble.model.Tuile;

import static org.junit.jupiter.api.Assertions.*;

public class TuileTest {

    @Test
    public void testConstructorEtToString() {
        Tuile tuile = new Tuile(LettreAlphabetFrancais.A);
        assertEquals("A", tuile.toString());
    }

    @Test
    public void testPoints() {
        Tuile tuile = new Tuile(LettreAlphabetFrancais.B);
        assertEquals(3, tuile.points());
    }

    @Test
    public void testChangerLettre() {
        Tuile tuile = new Tuile(LettreAlphabetFrancais.A);
        assertEquals("A", tuile.toString());
        
        tuile.changerLettre(LettreAlphabetFrancais.B);
        assertEquals("B", tuile.toString());
    }

    @Test
    public void testEstJoker() {
        Tuile tuile = new Tuile(LettreAlphabetFrancais.A);
        assertFalse(tuile.estJoker());
        
        Joker joker = new Joker();
        assertTrue(joker.estJoker());
    }
}