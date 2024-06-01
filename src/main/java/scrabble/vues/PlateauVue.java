package scrabble.vues;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class PlateauVue extends GridPane {
    public PlateauVue() {
        super();

        setPadding(new Insets(32, 0, 0, 32));
        setVgap(2);
        setHgap(2);

        for (int ligne = 0; ligne < 15; ligne++) {
            for (int colonne = 0; colonne < 15; colonne++) {
                add(new PlateauCaseVue("S", 2), ligne, colonne);
            }
        }
    }
}
