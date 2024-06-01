package scrabble.vues;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PartieInformationVue extends VBox {
    public PartieInformationVue() {
        super();

        getChildren().addAll(new Label("Joueur 1"), new Label("Joueur 2"));
        setMinWidth(300);
        setMaxWidth(300);
    }
}
