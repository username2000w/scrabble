package scrabble.vues;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import scrabble.gui.utils.PoliceTexteUtilitaire;

public class PlateauCaseVue extends Pane {
    private final String lettre;
    private final int points;

    private static final int TUILE_TAILLE_PX = 52;

    public PlateauCaseVue (String lettre, int points) {
        super();

        this.lettre = lettre;
        this.points = points;

        Label lettreLabel = new Label(lettre);
        lettreLabel.setFont(PoliceTexteUtilitaire.utiliserReadexPro(24));
        // On centre la lettre dans la tuile en utilisant ses positions.
        lettreLabel.layoutXProperty().bind(widthProperty().subtract(lettreLabel.widthProperty()).divide(2));
        lettreLabel.layoutYProperty().bind(heightProperty().subtract(lettreLabel.heightProperty()).divide(2));

        Label pointsLabel = new Label(String.valueOf(points));
        pointsLabel.setFont(PoliceTexteUtilitaire.utiliserReadexPro(12, FontWeight.BOLD));
        // On la met en bas à droite de la tuile (décalage de 4px).
        pointsLabel.layoutXProperty().bind(widthProperty().subtract(pointsLabel.widthProperty()).subtract(4));
        pointsLabel.layoutYProperty().bind(heightProperty().subtract(pointsLabel.heightProperty()).subtract(2));
        pointsLabel.setTextFill(Color.rgb(70, 70, 70));

        getChildren().addAll(lettreLabel, pointsLabel);

        setMaxWidth(TUILE_TAILLE_PX);
        setMaxHeight(TUILE_TAILLE_PX);
        setMinWidth(TUILE_TAILLE_PX);
        setMinHeight(TUILE_TAILLE_PX);

        setBackground(new Background(new BackgroundFill(
                Color.WHITE,
                new CornerRadii(4), // 8px (+ 4 pour éviter de passer sur le bord)
                null
        )));
    }

    public String lettre() {
        return lettre;
    }

    public int points() {
        return points;
    }
}
