package scrabble.vues;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class PartieInformationVue extends VBox {
    private final Button jouerUnMotBouton;
    private final Button passerTourBouton;
    private final Button validerEchangeBouton;
    private final Label scoreLabel;

    public PartieInformationVue() {
        super();

        jouerUnMotBouton = new Button("Jouer un mot");
        passerTourBouton = new Button("Passer son tour");
        validerEchangeBouton = new Button("Valider l'échange");

        scoreLabel = new Label("0");
        scoreLabel.setStyle("-fx-text-fill: white; -fx-font-size: 69px; -fx-font-weight: bold; -fx-font-family: 'Arial';");

        // On le cache par défaut, doit apparaître seulement lors d'un échange actif.
        validerEchangeBouton.setVisible(false);

        getChildren().addAll(new Label("Joueur"), scoreLabel, jouerUnMotBouton, passerTourBouton, validerEchangeBouton);
        setMinWidth(300);
        setMaxWidth(300);
    }

    public Button jouerUnMotBouton() {
        return jouerUnMotBouton;
    }

    public Button passerTourBouton() {
        return passerTourBouton;
    }

    public Button validerEchangeBouton() {
        return validerEchangeBouton;
    }

    public Label scoreLabel() {
        return scoreLabel;
    }
}
