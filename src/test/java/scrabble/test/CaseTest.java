package scrabble.test;

import org.junit.jupiter.api.Test;
import scrabble.model.Bonus;
import scrabble.model.Case;
import scrabble.model.LettreAlphabetFrancais;
import scrabble.model.Tuile;

import static org.junit.jupiter.api.Assertions.*;

class CaseTest {

    private final Case caseAvecBonus = new Case(Bonus.LETTRE_DOUBLE);;
    private final Case caseSansBonus = new Case();;
    private final Tuile tuile  = new Tuile(LettreAlphabetFrancais.A);

    @Test
    void testCaseInitialementVide() {
        // Teste si une case est vide à l'initialisation
        assertTrue(caseAvecBonus.estVide());
        assertTrue(caseSansBonus.estVide());
    }

    @Test
    void testChangerTuile() {
        // Teste le changement de tuile sur une case vide
        caseAvecBonus.changerTuile(tuile);

        assertFalse(caseAvecBonus.estVide());
        assertEquals(tuile, caseAvecBonus.tuile());
    }

    @Test
    void testViderCase() {
        // Teste le vidage d'une case
        caseAvecBonus.changerTuile(tuile);
        assertFalse(caseAvecBonus.estVide());

        caseAvecBonus.viderCase();

        assertTrue(caseAvecBonus.estVide());
        assertNull(caseAvecBonus.tuile());
    }

    @Test
    void testTuileSurCaseVide() {
        // Teste la récupération de la tuile sur une case vide
        assertNull(caseAvecBonus.tuile());
    }

    @Test
    void testBonus() {
        // Teste le bonus sur une case
        assertEquals(Bonus.LETTRE_DOUBLE, caseAvecBonus.bonus());
        assertNull(caseSansBonus.bonus());
    }

    @Test
    void testConstructeurSansParametre() {
        // Teste le constructeur sans paramètre
        Case nouvelleCase = new Case();
        assertNull(nouvelleCase.bonus());
        assertTrue(nouvelleCase.estVide());
    }
}