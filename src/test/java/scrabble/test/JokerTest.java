package scrabble.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scrabble.model.Joker;
import scrabble.model.LettreAlphabetFrancais;

import static org.junit.jupiter.api.Assertions.*;

public class JokerTest {
    private Joker joker;

    @BeforeEach
    void init() {
        joker = new Joker(); // Initialise un nouveau Joker avant chaque test
    }

    @Test
    void testPoints() {
        // Vérifie que le Joker a bien 0 points
        assertEquals(0, joker.points());
    }

    @Test
    void testToStringInitial() {
        // Vérifie que le Joker est représenté par un espace à l'initialisation
        assertEquals(" ", joker.toString());
    }

    @Test
    void testToStringAfterSettingLetter() {
        // Vérifie que le Joker est représenté par la lettre 'A' après avoir changé sa lettre
        joker.changerLettre(LettreAlphabetFrancais.A);
        assertEquals("A", joker.toString());
    }

    @Test
    void testChangerLettre() {
        // Vérifie que le Joker est représenté par la lettre 'B' après avoir changé sa lettre
        joker.changerLettre(LettreAlphabetFrancais.B);
        assertEquals("B", joker.toString());
    }
}
