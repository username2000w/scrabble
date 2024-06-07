package scrabble.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scrabble.model.Mot;
import scrabble.model.Tuile;
import scrabble.model.LettreAlphabetFrancais;
import scrabble.model.utils.Coordonee;
import scrabble.model.utils.Direction;

import static org.junit.jupiter.api.Assertions.*;

class MotTest {
    private Mot mot;
    private Tuile tuileA;
    private Tuile tuileB;

    @BeforeEach
    void setUp() {
        mot = new Mot(new Coordonee(0, 0), Direction.HORIZONTAL);
        tuileA = new Tuile(LettreAlphabetFrancais.A);
        tuileB = new Tuile(LettreAlphabetFrancais.B);
    }

    @Test
    void testAjouterLettre() {
        mot.ajouterLettre(tuileA);
        assertEquals(1, mot.nombreDeLettre());
        assertTrue(mot.getMot().contains(tuileA));
    }

    @Test
    void testCoordoneeDebut() {
        Coordonee coordonee = new Coordonee(1, 1);
        mot.changerCoordoneeDebut(coordonee);
        assertEquals(coordonee, mot.coordoneeDebut());
    }

    @Test
    void testDirection() {
        mot.changerDirection(Direction.VERTICAL);
        assertEquals(Direction.VERTICAL, mot.direction());
    }

    @Test
    void testNombreDeLettre() {
        mot.ajouterLettre(tuileA);
        mot.ajouterLettre(tuileB);
        assertEquals(2, mot.nombreDeLettre());
    }
}