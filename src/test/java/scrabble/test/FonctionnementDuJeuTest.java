package scrabble.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.Chevalet;
import scrabble.model.Sac;

class FonctionnementDuJeuTest {
	Sac sac;
	Chevalet chevalet;

	@BeforeEach
	void init() {
		sac = new Sac();
		chevalet = new Chevalet(sac);
	}
	
	@Test
	void chevaletVide() {
		assertEquals(true, chevalet.getTuiles().isEmpty());
	}
	
	@Test
	void pioche() {
		int taille = chevalet.getTuiles().size();
		chevalet.piocher();
		
		assertEquals(taille + 1, chevalet.getTuiles().size());
	}

}
