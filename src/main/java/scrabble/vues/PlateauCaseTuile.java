package scrabble.vues;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import scrabble.gui.utils.PoliceTexteUtilitaire;

public class PlateauCaseTuile extends Pane {
    public PlateauCaseTuile(String lettre, int points) {
        super();

        Label contenuLabel = new Label(lettre);
        contenuLabel.setFont(PoliceTexteUtilitaire.utiliserReadexPro(24));
        // On centre la lettre dans la tuile en utilisant ses positions.
        contenuLabel.layoutXProperty().bind(widthProperty().subtract(contenuLabel.widthProperty()).divide(2));
        contenuLabel.layoutYProperty().bind(heightProperty().subtract(contenuLabel.heightProperty()).divide(2));

        Label pointsLabel = new Label(String.valueOf(points));
        pointsLabel.setFont(PoliceTexteUtilitaire.utiliserReadexPro(12, FontWeight.BOLD));
        // On la met en bas à droite de la tuile (décalage de 4px).
        pointsLabel.layoutXProperty().bind(widthProperty().subtract(pointsLabel.widthProperty()).subtract(4));
        pointsLabel.layoutYProperty().bind(heightProperty().subtract(pointsLabel.heightProperty()).subtract(2));
        pointsLabel.setTextFill(Color.rgb(70, 70, 70));

        getChildren().addAll(contenuLabel, pointsLabel);

        setBackground(new Background(new BackgroundFill(
            Color.WHITE,
            new CornerRadii(4), // 8px (+ 4 pour éviter de passer sur le bord)
            null
        )));
    }
}
