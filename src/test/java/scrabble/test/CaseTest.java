package scrabble.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scrabble.model.Bonus;
import scrabble.model.Case;
import scrabble.model.LettreAlphabetFrancais;
import scrabble.model.Tuile;

import static org.junit.jupiter.api.Assertions.*;

public class CaseTest {

    private final Case caseAvecBonus = new Case(Bonus.LETTRE_DOUBLE);;
    private final Case caseSansBonus = new Case();;
    private final Tuile tuile  = new Tuile(LettreAlphabetFrancais.A);

    @Test
    public void testCaseInitialementVide() {
        // Teste si une case est vide à l'initialisation
        assertTrue(caseAvecBonus.estVide());
        assertTrue(caseSansBonus.estVide());
    }

    @Test
    public void testChangerTuile() {
        // Teste le changement de tuile sur une case vide
        caseAvecBonus.changerTuile(tuile);

        assertFalse(caseAvecBonus.estVide());
        assertEquals(tuile, caseAvecBonus.tuile());
    }

    @Test
    public void testViderCase() {
        // Teste le vidage d'une case
        caseAvecBonus.changerTuile(tuile);
        assertFalse(caseAvecBonus.estVide());

        caseAvecBonus.viderCase();

        assertTrue(caseAvecBonus.estVide());
        assertNull(caseAvecBonus.tuile());
    }

    @Test
    public void testTuileSurCaseVide() {
        // Teste la récupération de la tuile sur une case vide
        assertNull(caseAvecBonus.tuile());
    }

    @Test
    public void testBonus() {
        // Teste le bonus sur une case
        assertEquals(Bonus.LETTRE_DOUBLE, caseAvecBonus.bonus());
        assertNull(caseSansBonus.bonus());
    }

    @Test
    public void testConstructeurSansParametre() {
        // Teste le constructeur sans paramètre
        Case nouvelleCase = new Case();
        assertNull(nouvelleCase.bonus());
        assertTrue(nouvelleCase.estVide());
    }
}