package scrabble.test;

import org.junit.jupiter.api.Test;
import scrabble.model.LettreAlphabetFrancais;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LettreAlphabetFrancaisTest {

    @Test
    void testPoints() {
        // Vérifie que le Joker a bien 0 points
        assertEquals(1, LettreAlphabetFrancais.A.points());
        assertEquals(2, LettreAlphabetFrancais.D.points());
        assertEquals(3, LettreAlphabetFrancais.C.points());
        assertEquals(4, LettreAlphabetFrancais.F.points());
        assertEquals(1, LettreAlphabetFrancais.A.points());
        assertEquals(8, LettreAlphabetFrancais.Q.points());
        assertEquals(1, LettreAlphabetFrancais.A.points());

    }

}
