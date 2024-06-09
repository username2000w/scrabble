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
    /**
     * Par défaut, une case n'est pas définitivement posée.
     * 1. Une tuile est "drag 'n drop" sur le plateau ;
     * 2. On crée un objet PlateauCaseTuile pour le plateau et on supprime la tuile du chevalet ;
     * 3. Une fois que le mot est validé, la case est "définitivement posée".
     */
    private boolean estDefinitivementPosee = false;

    private final String lettre;
    private final int points;

    public PlateauCaseTuile(String lettre, int points) {
        super();

        this.lettre = lettre;
        this.points = points;

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

    public boolean estDefinitivementPosee() {
        return estDefinitivementPosee;
    }

    public void estDefinitivementPosee(boolean estDefinitivementPosee) {
        this.estDefinitivementPosee = estDefinitivementPosee;
    }

    public String lettre() {
        return lettre;
    }

    public int points() {
        return points;
    }
}
