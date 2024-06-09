package scrabble.vues;

import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import scrabble.model.Tuile;

public class ChevaletVue extends HBox {
    public ChevaletVue() {
        super();

        setSpacing(12);

        setMaxWidth(576);
        setMinWidth(576);

        setMaxHeight(116);
        setMinHeight(116);

        setPadding(new Insets(16, 38, 32, 18));

        setBackground(new Background(new BackgroundFill(
            Color.rgb(173, 36, 43),
            new CornerRadii(20), // 16px (+ 4 pour éviter de passer sur le bord)
            null
        )));

        setBorder(new Border(new BorderStroke(
            Color.rgb(130, 27, 32),
            BorderStrokeStyle.SOLID,
            new CornerRadii(16),
            new BorderWidths(2)
        )));

        setEffect(new DropShadow(
            0,
            0,
            4,
            Color.rgb(130, 27, 32)
        ));

        setTranslateY(16);
    }

    public void ajouterLettre (Tuile tuile) {
        getChildren().add(new TuileVue(tuile.lettre(), tuile.points()));
    }

    public void ajouterLettreA (Tuile tuile, int index) {
        getChildren().set(index, new TuileVue(tuile.lettre(), tuile.points()));
    }

    public void retirerLettre (String lettre) {
        // On enlève seulement la première occurrence.
        for (int i = 0; i < getChildren().size(); i++) {
            TuileVue tuile = (TuileVue) getChildren().get(i);
            if (tuile.lettre().equals(lettre)) {
                getChildren().remove(i);
                break;
            }
        }
    }
}
