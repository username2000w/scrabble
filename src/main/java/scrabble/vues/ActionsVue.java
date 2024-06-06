package scrabble.vues;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import scrabble.composants.BoutonMelanger;

public class ActionsVue extends HBox {
    private final ChevaletVue chevalet;
    private final BoutonMelanger boutonMelanger;

    public ActionsVue() {
        super();

        chevalet = new ChevaletVue();
        setMargin(chevalet, new Insets(0, 0, 0, 32));

        boutonMelanger = new BoutonMelanger();
        getChildren().addAll(chevalet, boutonMelanger);

        setAlignment(Pos.CENTER);
    }

    public ChevaletVue chevalet() {
        return chevalet;
    }

    public BoutonMelanger boutonMelanger() {
        return boutonMelanger;
    }
}
