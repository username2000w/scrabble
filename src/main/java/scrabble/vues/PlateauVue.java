package scrabble.vues;

import javafx.beans.binding.DoubleExpression;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import scrabble.gui.utils.ImageUtilitaire;
import scrabble.model.Plateau;
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

                Coordonee coordonee = new Coordonee(ligne, colonne);

                // On ajoute la case dans la hashmap plateau.
                cases.put(coordonee, casePlateau);
            }
        }

        getChildren().addAll(cases.values());

        for (Coordonee coordonee : Plateau.COORDONEES_ETOILE) {
            initialiserCaseEtoile(cases.get(coordonee));
        }
        for (Coordonee coordonee : Plateau.COORDONEES_LETTRE_DOUBLE_SYMETRIQUE) {
            initialiserCaseLettreDouble(cases.get(coordonee));
            coordonee = new Coordonee(coordonee.colonne(), coordonee.ligne());
            initialiserCaseLettreDouble(cases.get(coordonee));
        }
        for (Coordonee coordonee : Plateau.COORDONEES_LETTRE_TRIPLE_SYMETRIQUE) {
            initialiserCaseLettreTriple(cases.get(coordonee));
            coordonee = new Coordonee(coordonee.colonne(), coordonee.ligne());
            initialiserCaseLettreTriple(cases.get(coordonee));
        }
        for (Coordonee coordonee : Plateau.COORDONEES_MOT_DOUBLE_SYMETRIQUE) {
            initialiserCaseMotDouble(cases.get(coordonee));
            coordonee = new Coordonee(coordonee.colonne(), coordonee.ligne());
            initialiserCaseMotDouble(cases.get(coordonee));
        }
        for (Coordonee coordonee : Plateau.COORDONEES_MOT_TRIPLE_SYMETRIQUE) {
            initialiserCaseMotTriple(cases.get(coordonee));
            coordonee = new Coordonee(coordonee.colonne(), coordonee.ligne());
            initialiserCaseMotTriple(cases.get(coordonee));
        }
    }

    /**
     * Récupérer la case située à la coordonnée donnée.
     * @param coordonee La coordonnée de la case.
     * @return La case située à la coordonnée donnée.
     */
    public PlateauCaseVue caseSitueA(Coordonee coordonee) {
        return cases.get(coordonee);
    }

    private void initialiserCaseEtoile(PlateauCaseVue casePlateau) {
        StackPane stackPane = new StackPane();

        stackPane.getChildren().add(new ImageView(ImageUtilitaire.chargerImage("etoile.png")));
        stackPane.setBackground(new Background(new BackgroundFill(
            PlateauCaseVue.MD_OU_ETOILE_COULEUR,
            new CornerRadii(4),
            null
        )));

        casePlateau.poser(stackPane);
    }

    private void initialiserCaseLettreDouble(PlateauCaseVue casePlateau) {
        StackPane stackPane = new StackPane();

        stackPane.getChildren().add(new Label("LD"));
        stackPane.setBackground(new Background(new BackgroundFill(
            PlateauCaseVue.LD_COULEUR,
            new CornerRadii(4),
            null
        )));

        casePlateau.poser(stackPane);
    }

    private void initialiserCaseLettreTriple(PlateauCaseVue casePlateau) {
        StackPane stackPane = new StackPane();

        stackPane.getChildren().add(new Label("LT"));
        stackPane.setBackground(new Background(new BackgroundFill(
            PlateauCaseVue.LT_COULEUR,
            new CornerRadii(4),
            null
        )));

        casePlateau.poser(stackPane);
    }

    private void initialiserCaseMotDouble(PlateauCaseVue casePlateau) {
        StackPane stackPane = new StackPane();

        stackPane.getChildren().add(new Label("MD"));
        stackPane.setBackground(new Background(new BackgroundFill(
            PlateauCaseVue.MD_OU_ETOILE_COULEUR,
            new CornerRadii(4),
            null
        )));

        casePlateau.poser(stackPane);
    }
    private void initialiserCaseMotTriple(PlateauCaseVue casePlateau) {
        StackPane stackPane = new StackPane();

        stackPane.getChildren().add(new Label("MT"));
        stackPane.setBackground(new Background(new BackgroundFill(
            PlateauCaseVue.MT_COULEUR,
            new CornerRadii(4),
            null
        )));

        casePlateau.poser(stackPane);
    }
}
