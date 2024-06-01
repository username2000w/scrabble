package scrabble.vues;

import javafx.beans.binding.DoubleExpression;
import javafx.scene.layout.*;
import scrabble.model.utils.Coordonee;

import java.util.HashMap;
import java.util.Map;

public class PlateauVue extends Pane {
    private final Map<Coordonee, PlateauCaseVue> cases = new HashMap<>();

    public PlateauVue() {
        super();

        for (int ligne = 0; ligne < 15; ligne++) {
            for (int colonne = 0; colonne < 15; colonne++) {
                PlateauCaseVue casePlateau = new PlateauCaseVue();
                // On ajoute un écart de 2px entre les cases.
                DoubleExpression tailleCase = heightProperty().subtract(32 + (2 * 15)).divide(15);

                // On lie la taille de la case en fonction de la taille de la fenêtre.
                casePlateau.minWidthProperty().bind(tailleCase);
                casePlateau.maxWidthProperty().bind(tailleCase);
                casePlateau.minHeightProperty().bind(tailleCase);
                casePlateau.maxHeightProperty().bind(tailleCase);

                // On récupère la taille de toutes les cases pour les centrer.
                DoubleExpression tailleToutesLesCases = tailleCase.multiply(15).add(2 * 15);

                // On ajoute horizontalement la case.
                casePlateau.layoutXProperty().bind(
                    // Centrage horizontal.
                    widthProperty().subtract(tailleToutesLesCases).divide(2)
                    // 2px d'espace entre colonnes.
                    .add(tailleCase.add(2).multiply(colonne))
                );

                // On ajoute verticalement la case.
                casePlateau.layoutYProperty().bind(
                    // Centrage vertical.
                    heightProperty().subtract(tailleToutesLesCases).divide(2)
                    // 2px d'espace entre lignes.
                    .add(tailleCase.add(2).multiply(ligne))
                );

                // On ajoute la case dans la hashmap plateau.
                cases.put(new Coordonee(ligne, colonne), casePlateau);
            }
        }

        getChildren().addAll(cases.values());
    }

    /**
     * Récupérer la case située à la coordonnée donnée.
     * @param coordonee La coordonnée de la case.
     * @return La case située à la coordonnée donnée.
     */
    public PlateauCaseVue caseSitueA(Coordonee coordonee) {
        return cases.get(coordonee);
    }
}
