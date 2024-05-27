package scrabble.test;

import org.junit.jupiter.api.Test;
import scrabble.model.Chevalet;
import scrabble.model.Joueur;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JoueurTest {

    @Test
    void nom_du_joueur() {
        Chevalet chevalet = new Chevalet();
        Joueur joueur = new Joueur(chevalet, "Joueur 1");

        assertEquals("Joueur 1", joueur.getNom());
    }
}
