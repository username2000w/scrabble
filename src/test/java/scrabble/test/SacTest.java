package scrabble.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import scrabble.model.LettreAlphabetFrancais;
import scrabble.model.Sac;
import scrabble.model.utils.exception.SacVideException;

public class SacTest {
  @Test
  void testContient102Tuiles() {
    Sac sac = new Sac();
    int nbTuiles = sac.getNombreDeTuiles();
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
    assertEquals(102 - 1, sac.getNombreDeTuiles());
    
    // On en retire encore trois.
    try {
      for (int i = 0; i < 3; ++i) {
        sac.piocher();
      }
    } catch (SacVideException e) {
      System.out.println(e.getMessage());
    }
    assertEquals(102 - 1 - 3, sac.getNombreDeTuiles());
  }

  /**
   * Lors de l'instanciation, le sac est rempli de tuiles de scrabble qui sont mélangées.
   * On vérifie que le sac a bien été mélangé sur deux instances différentes.
   */
  @Test
  void testMelangePasIdentique() {
    Sac sac1 = new Sac();
    Sac sac2 = new Sac();

    // On pioche toutes les tuiles du sac 1.
    ArrayList<LettreAlphabetFrancais> lettreSac1 = new ArrayList<>();
    while (sac1.getNombreDeTuiles() > 0) {
      try {
        lettreSac1.add(sac1.piocher());
      } catch (SacVideException e) {
        System.out.println(e.getMessage());
      }
    }

    // On pioche toutes les tuiles du sac 2.
    ArrayList<LettreAlphabetFrancais> lettreSac2 = new ArrayList<>();
    while (sac2.getNombreDeTuiles() > 0) {
      try {
        lettreSac2.add(sac2.piocher());
      } catch (SacVideException e) {
        System.out.println(e.getMessage());
      }
    }

    // On vérifie que les deux listes de tuiles sont différentes.
    assertFalse(lettreSac1.equals(lettreSac2));
  }

  @Test
  void testSacVide() {
    Sac sac = new Sac();
    try {
      for (int i = 0; i < 102; ++i) {
        sac.piocher();
      }
    } catch (SacVideException e) {
      // On vérifie si on a bien une exception SacVideException.
      assertEquals("Le sac est vide.", e.getMessage());
    }
  }
}
