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
    public void testEstJoker() {
        // Teste si la tuile est un joker
        assert estUnJokerVide.estJoker();
        assert estUnJokerJ.estJoker();
        assert estUnJokerK.estJoker();
    }

    @Test
    public void testNestPasUnJoker() {
        // Teste si la tuile n'est pas un joker
        assert !nEstPasUnJoker.estJoker();
    }

    @Test
    public void testAffichageUnJoker() {
        // Teste l'affichage d'un joker
        assertEquals(" ", estUnJokerVide.toString());
        assertEquals("J", estUnJokerJ.toString());
        assertEquals("K", estUnJokerK.toString());
    }
}
