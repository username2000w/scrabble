package scrabble.vues;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ActionsVue extends HBox {
    public ActionsVue() {
        super();

        ChevaletVue chevalet = new ChevaletVue();
        setMargin(chevalet, new Insets(0, 0, 0, 32));

        Button melangerBouton = new Button("R ");

        melangerBouton.setMinWidth(48);
        melangerBouton.setMinHeight(48);
        melangerBouton.setTranslateX(-48/2);
        melangerBouton.setTranslateY(10);
        melangerBouton.setBackground(new Background(new BackgroundFill(
            Color.WHITE,
            new CornerRadii(52, true),
            new Insets(1)
        )));

        melangerBouton.setOnMouseClicked(event -> {
            System.out.println("TODO: Mélanger les tuiles du chevalet");
        });

        melangerBouton.setBorder(new Border(new BorderStroke(
            Color.rgb(130, 27, 32),
            BorderStrokeStyle.SOLID,
            new CornerRadii(50, true),
            new BorderWidths(2)
        )));

        melangerBouton.setEffect(new DropShadow(
            0,
            0,
            4,
            Color.rgb(130, 27, 32)
        ));

        getChildren().addAll(chevalet, melangerBouton);

        setAlignment(Pos.CENTER);
    }
}
