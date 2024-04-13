package scrabble.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import scrabble.model.Sac;
import scrabble.model.Tuile;

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
    sac.piocher();
    assertEquals(102 - 1, sac.getNombreDeTuiles());
    
    // On en retire encore trois.
    sac.piocher();
    sac.piocher();
    sac.piocher();
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
    ArrayList<Tuile> tuilesSac1 = new ArrayList<>();
    while (sac1.getNombreDeTuiles() > 0) {
      tuilesSac1.add(sac1.piocher());
    }

    // On pioche toutes les tuiles du sac 2.
    ArrayList<Tuile> tuilesSac2 = new ArrayList<>();
    while (sac2.getNombreDeTuiles() > 0) {
      tuilesSac2.add(sac2.piocher());
    }

    // On vérifie que les deux listes de tuiles sont différentes.
    assertEquals(false, tuilesSac1.equals(tuilesSac2));
  }
}
