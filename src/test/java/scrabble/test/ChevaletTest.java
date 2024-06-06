package scrabble.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scrabble.model.Chevalet;
import scrabble.model.LettreAlphabetFrancais;
import scrabble.model.Sac;
import scrabble.model.Tuile;

import static org.junit.jupiter.api.Assertions.*;

public class ChevaletTest {
  private Chevalet chevalet;
  private Sac sac;
  private Tuile tuileA;
  private Tuile tuileB;

  @BeforeEach
  void init() {
    sac = new Sac();
    chevalet = new Chevalet();
    tuileA = new Tuile(LettreAlphabetFrancais.A);
    tuileB = new Tuile(LettreAlphabetFrancais.B);
  }

  @Test
  void testTailleInitiale() {
    int nombreDeTuiles = chevalet.tuiles().size();
    boolean estVide = chevalet.tuiles().isEmpty();

    assertEquals(0, nombreDeTuiles); // Vérifie que le chevalet est initialement vide
    assertTrue(estVide); // Vérifie que la méthode isEmpty() retourne true pour un nouveau chevalet
  }

  @Test
  void testPiocher() {
    sac.viderSac();
    sac.ajouter(tuileA);
    chevalet.piocher(sac);
    assertTrue(chevalet.tuiles().contains(tuileA)); // Vérifie que la tuile a été ajoutée au chevalet

    sac.viderSac();
    chevalet.piocher(sac);
    assertEquals(1, chevalet.tuiles().size()); // Vérifie que le chevalet n'a pas pioché si le sac est vide
  }

  @Test
  void testEchanger() {
    sac.viderSac();
    sac.ajouter(tuileA);
    sac.ajouter(tuileB);
    chevalet.piocher(sac);

    Tuile echange = chevalet.echanger(sac, 0);
    assertEquals(tuileA, echange); // Vérifie que la tuile échangée est correcte
    assertTrue(chevalet.tuiles().contains(tuileB)); // Vérifie que la nouvelle tuile est dans le chevalet
  }

  @Test
  void testRetirerLettre() {
    chevalet.tuiles().add(tuileA);
    chevalet.retirerLettre(tuileA);
    assertFalse(chevalet.tuiles().contains(tuileA)); // Vérifie que la tuile a été retirée

    chevalet.retirerLettre(tuileB); // Essaie de retirer une tuile non présente
    assertFalse(chevalet.tuiles().contains(tuileB)); // Vérifie que la tuile non présente n'a pas été retirée
  }

  @Test
  void testTuileAvecIndex() {
    chevalet.tuiles().add(tuileA);
    assertEquals(tuileA, chevalet.tuileAvecIndex(0)); // Vérifie que la tuile retournée par l'index est correcte
  }

  @Test
  void testRemplirChevalet() {
    sac.viderSac();
    for (int i = 0; i < Chevalet.TAILLE; i++) {
      sac.ajouter(new Tuile(LettreAlphabetFrancais.A));
    }
    chevalet.remplirChevalet(sac);
    assertEquals(Chevalet.TAILLE, chevalet.tuiles().size()); // Vérifie que le chevalet est rempli à la taille correcte
  }

  @Test
  void testTuileAvecLettre() {
    chevalet.tuiles().add(tuileA);
    assertEquals(tuileA, chevalet.tuileAvecLettre("A")); // Vérifie que la tuile retournée par la lettre est correcte
    assertNull(chevalet.tuileAvecLettre("B")); // Vérifie qu'une lettre non présente retourne null
  }
}
