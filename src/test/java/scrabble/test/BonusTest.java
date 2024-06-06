package scrabble.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import scrabble.model.Bonus;

public class BonusTest {

    @Test
    public void testSymbole() {
        // Teste les symboles des bonus
        assertEquals("3 ", Bonus.MOT_TRIPLE.toString());
        assertEquals("2 ", Bonus.MOT_DOUBLE.toString());
        assertEquals("D ", Bonus.LETTRE_DOUBLE.toString());
        assertEquals("T ", Bonus.LETTRE_TRIPLE.toString());
        assertEquals("★ ", Bonus.ETOILE.toString());
    }

    @Test
    public void testMultiplicateurLettre() {
        // Teste les multiplicateurs de lettre
        assertEquals(1, Bonus.MOT_TRIPLE.multiplicateurLettre());
        assertEquals(1, Bonus.MOT_DOUBLE.multiplicateurLettre());
        assertEquals(2, Bonus.LETTRE_DOUBLE.multiplicateurLettre());
        assertEquals(3, Bonus.LETTRE_TRIPLE.multiplicateurLettre());
        assertEquals(1, Bonus.ETOILE.multiplicateurLettre());
    }

    @Test
    public void testMultiplicateurMot() {
        // Teste les multiplicateurs de mot
        assertEquals(3, Bonus.MOT_TRIPLE.multiplicateurMot());
        assertEquals(2, Bonus.MOT_DOUBLE.multiplicateurMot());
        assertEquals(1, Bonus.LETTRE_DOUBLE.multiplicateurMot());
        assertEquals(1, Bonus.LETTRE_TRIPLE.multiplicateurMot());
        assertEquals(2, Bonus.ETOILE.multiplicateurMot());
    }
}
