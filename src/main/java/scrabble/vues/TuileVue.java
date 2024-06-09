package scrabble.vues;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import scrabble.gui.utils.PoliceTexteUtilitaire;

public class TuileVue extends Pane {
    private final String lettre;
    private final int points;

    public static final int TUILE_TAILLE_PX = 64;
    private static final Color TUILE_BORD_COULEUR = Color.rgb(107, 10, 26);

    public TuileVue (String lettre, int points) {
        super();

        this.lettre = lettre;
        this.points = points;

        if (points > 0) {
            Label lettreLabel = new Label(lettre);
            lettreLabel.setFont(PoliceTexteUtilitaire.utiliserReadexPro(24));
            // On centre la lettre dans la tuile en utilisant ses positions.
            lettreLabel.layoutXProperty().bind(widthProperty().subtract(lettreLabel.widthProperty()).divide(2));
            lettreLabel.layoutYProperty().bind(heightProperty().subtract(lettreLabel.heightProperty()).divide(2));
            getChildren().add(lettreLabel);

            Label pointsLabel = new Label(String.valueOf(points));
            pointsLabel.setFont(PoliceTexteUtilitaire.utiliserReadexPro(12));
            // On la met en bas à droite de la tuile (décalage de 4px).
            pointsLabel.layoutXProperty().bind(widthProperty().subtract(pointsLabel.widthProperty()).subtract(8));
            pointsLabel.layoutYProperty().bind(heightProperty().subtract(pointsLabel.heightProperty()).subtract(4));
            pointsLabel.setTextFill(Color.rgb(70, 70, 70));

            getChildren().add(pointsLabel);
        }

        setMaxWidth(TUILE_TAILLE_PX);
        setMaxHeight(TUILE_TAILLE_PX);
        setMinWidth(TUILE_TAILLE_PX);
        setMinHeight(TUILE_TAILLE_PX);

        setBackground(new Background(new BackgroundFill(
            Color.WHITE,
            new CornerRadii(12), // 8px (+ 4 pour éviter de passer sur le bord)
            null
        )));

        setBorder(new Border(new BorderStroke(
            TUILE_BORD_COULEUR,
            BorderStrokeStyle.SOLID,
            new CornerRadii(8),
            new BorderWidths(2)
        )));

        setEffect(new DropShadow(
            0,
            0,
            4,
            TUILE_BORD_COULEUR
        ));
    }

    /**
     * Joker.
     */
    public TuileVue (String lettre) {
        this(lettre, 0);
    }

    public String lettre() {
        return lettre;
    }

    public int points() {
        return points;
    }

    public boolean estJoker() {
        return points == 0;
    }
}
