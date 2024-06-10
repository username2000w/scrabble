package scrabble.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.Bonus;
import scrabble.model.LettreAlphabetFrancais;
import scrabble.model.Plateau;
import scrabble.model.Tuile;
import scrabble.model.utils.Coordonee;
import scrabble.model.utils.exception.HorsPlateauException;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {

    private Plateau plateau;

    @BeforeEach
    public void setUp() {
        plateau = new Plateau();
    }

    @Test
    void testInitialisationPlateau() {
        assertEquals(15, Plateau.TAILLE_PLATEAU_HORIZONTALE);
        assertEquals(15, Plateau.TAILLE_PLATEAU_VERTICALE);

        for (int ligne = 0; ligne < Plateau.TAILLE_PLATEAU_VERTICALE; ligne++) {
            for (int colonne = 0; colonne < Plateau.TAILLE_PLATEAU_HORIZONTALE; colonne++) {
                assertNotNull(plateau.plateau()[ligne][colonne]);
            }
        }
    }

    @Test
    void testCoordoneesEtoile() {
        for (Coordonee coordonee : Plateau.COORDONEES_ETOILE) {
            assertEquals(Bonus.ETOILE, plateau.casePlateau(coordonee).bonus());
        }
    }

    @Test
    void testCoordoneesLettreDouble() {
        for (Coordonee coordonee : Plateau.COORDONEES_LETTRE_DOUBLE_SYMETRIQUE) {
            assertEquals(Bonus.LETTRE_DOUBLE, plateau.casePlateau(coordonee).bonus());
            assertEquals(Bonus.LETTRE_DOUBLE, plateau.casePlateau(new Coordonee(coordonee.colonne(), coordonee.ligne())).bonus());
        }
    }

    @Test
    void testCoordoneesLettreTriple() {
        for (Coordonee coordonee : Plateau.COORDONEES_LETTRE_TRIPLE_SYMETRIQUE) {
            assertEquals(Bonus.LETTRE_TRIPLE, plateau.casePlateau(coordonee).bonus());
            assertEquals(Bonus.LETTRE_TRIPLE, plateau.casePlateau(new Coordonee(coordonee.colonne(), coordonee.ligne())).bonus());
        }
    }

    @Test
    void testCoordoneesMotDouble() {
        for (Coordonee coordonee : Plateau.COORDONEES_MOT_DOUBLE_SYMETRIQUE) {
            assertEquals(Bonus.MOT_DOUBLE, plateau.casePlateau(coordonee).bonus());
            assertEquals(Bonus.MOT_DOUBLE, plateau.casePlateau(new Coordonee(coordonee.colonne(), coordonee.ligne())).bonus());
        }
    }

    @Test
    void testCoordoneesMotTriple() {
        for (Coordonee coordonee : Plateau.COORDONEES_MOT_TRIPLE_SYMETRIQUE) {
            assertEquals(Bonus.MOT_TRIPLE, plateau.casePlateau(coordonee).bonus());
            assertEquals(Bonus.MOT_TRIPLE, plateau.casePlateau(new Coordonee(coordonee.colonne(), coordonee.ligne())).bonus());
        }
    }

    @Test
    void testPlacerTuile() throws HorsPlateauException {
        Tuile tuile = new Tuile(LettreAlphabetFrancais.A);
        Coordonee coordonee = new Coordonee(7, 7);
        plateau.placerTuile(tuile, coordonee);
        assertEquals(tuile, plateau.casePlateau(coordonee).tuile());
    }

    @Test
    void testSupprimerToutesTuiles() throws HorsPlateauException {
        Tuile tuile = new Tuile(LettreAlphabetFrancais.A);
        Coordonee coordonee = new Coordonee(7, 7);
        plateau.placerTuile(tuile, coordonee);
        assertEquals(tuile, plateau.casePlateau(coordonee).tuile());

        plateau.supprimerToutesTuiles();
        assertNull(plateau.casePlateau(coordonee).tuile());
    }

    @Test
    void testCasePlateau() {
        Coordonee coordonee = new Coordonee(0, 0);
        assertNotNull(plateau.casePlateau(coordonee));
    }

    
}