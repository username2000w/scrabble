package scrabble.test;

import org.junit.jupiter.api.Test;
import scrabble.model.utils.DictionaireFrancais;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DictionaireTest {

    @Test
    void mot_dgshsg_pas_dans_le_dictionaire() {
        DictionaireFrancais dictionaireFrancais = new DictionaireFrancais();
        // Teste si le mot "dgshsg" n'est pas dans le dictionaire
        assertFalse(dictionaireFrancais.contient("dgshsg"));
    }

    @Test
    void mot_pates_dans_le_dictionaire() {
        DictionaireFrancais dictionaireFrancais = new DictionaireFrancais();
        // Teste si le mot "abc" est dans le dictionaire
        assertTrue(dictionaireFrancais.contient("pates"));
    }
}
