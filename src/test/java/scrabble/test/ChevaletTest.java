package scrabble.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.Chevalet;
import scrabble.model.Sac;

public class ChevaletTest {
  private Chevalet chevalet;
  
  @BeforeEach
  void init() {
    Sac sac = new Sac();
    chevalet = new Chevalet(sac);
  }

  @Test
  void tailleInitiale() {
    int nombreDeTuiles = chevalet.getTuiles().size();
    // Lors de l'instanciation, on a 7 tuiles qui sont piochées.
    assertEquals(7, nombreDeTuiles);
  }
}
