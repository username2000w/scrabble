package scrabble.gui.utils;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.HashMap;
import java.util.Objects;

public class PoliceTexteUtilitaire  {
    private static final HashMap<String, Font> policesEnCache = new HashMap<>();

    private PoliceTexteUtilitaire() {
        throw new IllegalStateException("Classe utilitaire");
    }

    public static String obtenirCheminPolice (String nomPolice, FontWeight grosseur) {
        return Objects.requireNonNull(
            PoliceTexteUtilitaire.class
                .getClassLoader()
                .getResource("polices/" + nomPolice + "-" + grosseur.getWeight() + ".ttf")
        ).toExternalForm();
    }

    public static Font utiliser (String nomPolice, FontWeight grosseur, int taille) {
        String cle = nomPolice + "-" + grosseur.getWeight() + "-" + taille;
        if (policesEnCache.containsKey(cle)) {
            return policesEnCache.get(cle);
        }

        Font police = Font.loadFont(
            obtenirCheminPolice(nomPolice, grosseur),
            taille
        );

        policesEnCache.put(cle, police);
        return police;
    }

    public static Font utiliser (String nomPolice, int taille) {
        return utiliser(nomPolice, FontWeight.NORMAL, taille);
    }

    public static Font utiliserReadexPro (int taille, FontWeight grosseur) {
        return utiliser("ReadexPro", grosseur, taille);
    }

    public static Font utiliserReadexPro (int taille) {
        return utiliser("ReadexPro", taille);
    }
}
