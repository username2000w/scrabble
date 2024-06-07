package scrabble.test;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import scrabble.model.LettreAlphabetFrancais;
import scrabble.model.Sac;
import scrabble.model.Tuile;
import scrabble.model.utils.exception.SacVideException;

import static org.junit.jupiter.api.Assertions.*;

public class SacTest {

    @Test
    void testContient102Tuiles() {
        Sac sac = new Sac();
        int nbTuiles = sac.nombreDeTuiles();
        assertEquals(102, nbTuiles);
    }

    @Test
    void testPiocherRetireUneTuile() {
        Sac sac = new Sac();

        // On retire une tuile.
        try {
            sac.piocher();
        } catch (SacVideException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(102 - 1, sac.nombreDeTuiles());

        // On en retire encore trois.
        try {
            for (int i = 0; i < 3; ++i) {
                sac.piocher();
            }
        } catch (SacVideException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(102 - 1 - 3, sac.nombreDeTuiles());
    }

    /**
     * Lors de l'instanciation, le sac est rempli de tuiles de scrabble qui sont mélangées.
     * On vérifie que le sac a bien été mélangé sur deux instances différentes.
     */
    @Test
    void testMelangePasIdentique() {
        int tailleSac = 102;

        Sac sac1 = new Sac();
        Sac sac2 = new Sac();

        ArrayList<Tuile> tuiles1 = new ArrayList<>();
        ArrayList<Tuile> tuiles2 = new ArrayList<>();

        try {
            for (int i = 0; i < tailleSac; ++i) {
                tuiles1.add(sac1.piocher());
                tuiles2.add(sac2.piocher());
            }
        } catch (SacVideException e) {
            System.out.println(e.getMessage());
        }
        assertNotEquals(tuiles1, tuiles2);
    }

    @Test
    void testMelangePasIdentiqueException() {
        int tailleSac = 102;

        Sac sac1 = new Sac();
        Sac sac2 = sac1;

        ArrayList<Tuile> tuiles1 = new ArrayList<>();
        ArrayList<Tuile> tuiles2 = new ArrayList<>();

        try {
            for (int i = 0; i < tailleSac; ++i) {
                tuiles1.add(sac1.piocher());
                tuiles2.add(sac2.piocher());
            }
        } catch (SacVideException e) {
            System.out.println(e.getMessage());
        }
        assertNotEquals(tuiles1, tuiles2);

        // Tenter de piocher une tuile supplémentaire pour déclencher l'exception
        assertThrows(SacVideException.class, () -> sac1.piocher());
    }


    @Test
    void testSacVide() {
        Sac sac = new Sac();
        sac.viderSac();
        try {
            sac.piocher();
        } catch (SacVideException e) {
            // On vérifie que le message d'erreur est bien celui attendu.
            assertEquals("Le sac est vide.", e.getMessage());
        }
    }

    @Test
    public void testAjouterTuile() {
        Sac sac = new Sac();
        sac.viderSac();
        sac.ajouter(new Tuile(LettreAlphabetFrancais.A));
        assertEquals(1, sac.nombreDeTuiles());
    }

    @Test
    public void testExceptionSacVide() {
        Sac sac = new Sac();
        sac.viderSac();
        assertThrows(SacVideException.class, sac::piocher);
    }
}
