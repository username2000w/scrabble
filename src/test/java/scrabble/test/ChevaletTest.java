package scrabble.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.Chevalet;
import scrabble.model.Sac;

public class ChevaletTest {
  private Chevalet chevalet;
  
  @BeforeEach
  void init() {
    Sac sac = new Sac();
    chevalet = new Chevalet();
  }

  @Test
  void testTailleInitiale() {
    int nombreDeTuiles = chevalet.getLettres().size();
    boolean estVide = chevalet.getLettres().isEmpty();

    assertEquals(0, nombreDeTuiles);
    assertTrue(estVide);
  }
}
