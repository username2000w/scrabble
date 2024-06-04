package scrabble.vues;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import scrabble.composants.BoutonMelanger;

public class ActionsVue extends HBox {
    private final ChevaletVue chevalet;

    public ActionsVue() {
        super();

        chevalet = new ChevaletVue();
        setMargin(chevalet, new Insets(0, 0, 0, 32));

        BoutonMelanger melangerBouton = new BoutonMelanger();
        getChildren().addAll(chevalet, melangerBouton);

        setAlignment(Pos.CENTER);
    }

    public ChevaletVue chevalet() {
        return chevalet;
    }
}
