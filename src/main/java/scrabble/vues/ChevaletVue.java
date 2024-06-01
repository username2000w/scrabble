package scrabble.vues;

import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ChevaletVue extends HBox {
    public ChevaletVue() {
        super();

        getChildren().addAll(
            new TuileVue("Z", 10),
            new TuileVue("I", 1),
            new TuileVue("G", 2),
            new TuileVue("Y", 2),
            new TuileVue("E", 1),
            new TuileVue("R", 1),
            new TuileVue(" ") // JOKER
        );

        setSpacing(12);
        setMaxWidth(576);
        setMaxHeight(96);
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
}
