package scrabble.model.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;

public class DictionaireFrancais {
    private final HashSet<String> dictionnaire = new HashSet<>();

    public DictionaireFrancais() {
        try (BufferedReader br = new BufferedReader(new FileReader(Objects.requireNonNull(getClass().getClassLoader().getResource("dictionnaire.txt")).getFile()))) {
            String line = br.readLine();
            while (line != null) {
                this.dictionnaire.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean contient(String mot) {
        return dictionnaire.contains(mot);
    }
}
