package scrabble.vues;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class PartieInformationVue extends VBox {
    private final Button validerBouton;
    private final Button passerTourBouton;
    private final Button annulerBouton;
    private final Label scoreLabel;

    public PartieInformationVue() {
        super();

        validerBouton = new Button("OK");
        validerBouton.setOpacity(.5);

        passerTourBouton = new Button("SKIP");
        annulerBouton = new Button("CANCEL");

        scoreLabel = new Label("0");
        scoreLabel.setStyle("-fx-text-fill: white; -fx-font-size: 69px; -fx-font-weight: bold; -fx-font-family: 'Arial';");


        getChildren().addAll(new Label("Joueur"), scoreLabel, validerBouton);
        setMinWidth(300);
        setMaxWidth(300);
    }

    public Button utiliserAnnulerBouton(double opacite) {
        getChildren().remove(passerTourBouton);
        if (!getChildren().contains(annulerBouton)) {
            getChildren().add(annulerBouton);
        }
        annulerBouton.setOpacity(opacite);
        return annulerBouton;
    }

    public Button utiliserPasserTourBouton(double opacite) {
        getChildren().remove(annulerBouton);
        if (!getChildren().contains(passerTourBouton)) {
            getChildren().add(passerTourBouton);
        }
        passerTourBouton.setOpacity(opacite);
        return passerTourBouton;
    }

    public Button validerBouton() {
        return validerBouton;
    }

    public Label scoreLabel() {
        return scoreLabel;
    }
}
