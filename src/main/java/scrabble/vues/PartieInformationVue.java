package scrabble.vues;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class PartieInformationVue extends VBox {
    private final Button jouerMotBouton;

    public PartieInformationVue() {
        super();

        jouerMotBouton = new Button("Jouer un mot");
        getChildren().addAll(new Label("Joueur"), jouerMotBouton);
        setMinWidth(300);
        setMaxWidth(300);
    }

    public Button recupererJouerMotBouton() {
        return jouerMotBouton;
    }
}
