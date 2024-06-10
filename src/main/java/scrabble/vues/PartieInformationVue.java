package scrabble.vues;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import scrabble.gui.utils.PoliceTexteUtilitaire;

public class PartieInformationVue extends VBox {
    private final Button validerBouton;
    private final Button actionSecondaire;
    private final Label scoreLabel;
    private final SacVue sacVue;

    public PartieInformationVue() {
        super();

        validerBouton = new Button("OK");
        validerBouton.setOpacity(.5);

        actionSecondaire = new Button();

        scoreLabel = new Label("0");
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setFont(PoliceTexteUtilitaire.utiliserReadexPro(80));

        sacVue = new SacVue();

        getChildren().addAll(new Label("Joueur"), scoreLabel, validerBouton, actionSecondaire, sacVue);
        setMinWidth(300);
        setMaxWidth(300);
    }

    public Button utiliserAnnulerBouton(double opacite) {
        actionSecondaire.setText("ANNULER");
        actionSecondaire.setOpacity(opacite);
        return actionSecondaire;
    }

    public Button utiliserPasserTourBouton(double opacite) {
        actionSecondaire.setText("PASSER");
        actionSecondaire.setOpacity(opacite);
        return actionSecondaire;
    }

    public Button validerBouton() {
        return validerBouton;
    }

    public Label scoreLabel() {
        return scoreLabel;
    }

    public SacVue sacVue() {
    	return sacVue;
    }
}
