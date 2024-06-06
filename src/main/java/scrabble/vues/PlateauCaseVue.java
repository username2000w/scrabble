package scrabble.vues;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import scrabble.gui.utils.PoliceTexteUtilitaire;

public class PlateauCaseVue extends Pane {
    public static final Color LD_COULEUR = Color.rgb(87, 149, 191);
    public static final Color MT_COULEUR = Color.rgb(160, 60, 67);
    public static final Color LT_COULEUR = Color.rgb(8, 85, 150);
    public static final Color MD_OU_ETOILE_COULEUR = Color.rgb(204, 102, 102);

    public PlateauCaseVue () {
        super();

        setBackground(new Background(new BackgroundFill(
            Color.WHITE,
            new CornerRadii(4), // 8px (+ 4 pour éviter de passer sur le bord)
            null
        )));
    }

    public void poser(Pane node) {
        node.minWidthProperty().bind(widthProperty());
        node.minHeightProperty().bind(heightProperty());
        getChildren().add(node);
    }
}
