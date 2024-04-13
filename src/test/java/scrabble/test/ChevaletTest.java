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
  void testTailleInitiale() {
    int nombreDeTuiles = chevalet.getTuiles().size();
    Boolean estVide = chevalet.getTuiles().isEmpty();

    assertEquals(0, nombreDeTuiles);
    assertEquals(true, estVide);
  }
}
