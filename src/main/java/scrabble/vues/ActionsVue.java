package scrabble.vues;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
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
            new CornerRadii(50, true),
            null
        )));

        melangerBouton.setOnMouseClicked(event -> {
            System.out.println("TODO: Mélanger les tuiles du chevalet");
        });

        getChildren().addAll(chevalet, melangerBouton);

        setAlignment(Pos.CENTER);
    }
}
