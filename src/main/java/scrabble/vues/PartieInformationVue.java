package scrabble.vues;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import scrabble.gui.utils.PoliceTexteUtilitaire;

public class PartieInformationVue extends VBox {
    private final Button validerBouton;
    private final Button actionSecondaire;
    private final Label scoreLabel;
    private final SacVue sacVue;

    public PartieInformationVue() {
        super();

        setPadding(new Insets(0, 16, 0, 0));
        setSpacing(16);

        scoreLabel = new Label("0");
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setFont(PoliceTexteUtilitaire.utiliserReadexPro(80));

        validerBouton = new Button("OK");
        validerBouton.setOpacity(.5);
        validerBouton.setMaxWidth(Double.MAX_VALUE);

        actionSecondaire = new Button();
        actionSecondaire.setMaxWidth(Double.MAX_VALUE);

        HBox actions = new HBox();
        actions.setSpacing(8);
        actions.getChildren().addAll(validerBouton, actionSecondaire);

        sacVue = new SacVue();
        getChildren().addAll(new Label("Joueur"), scoreLabel, actions, sacVue);
        setMinWidth(300);
        setMaxWidth(300);

        HBox.setHgrow(validerBouton, Priority.ALWAYS);
        HBox.setHgrow(actionSecondaire, Priority.ALWAYS);

        validerBouton.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), null)));
        validerBouton.setAlignment(Pos.CENTER);

        validerBouton.setBorder(new Border(new BorderStroke(
            Color.rgb(172, 35, 42),
            BorderStrokeStyle.SOLID,
            new CornerRadii(18),
            new BorderWidths(2)
        )));

        validerBouton.setEffect(new DropShadow(
            0,
            0,
            4,
            Color.rgb(172, 35, 42)
        ));

        validerBouton.setFont(PoliceTexteUtilitaire.utiliserReadexPro(16));
        validerBouton.setTextFill(Color.rgb(172, 35, 42));

        validerBouton.setPadding(new Insets(12, 0, 12, 0));

        actionSecondaire.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), null)));
        actionSecondaire.setAlignment(Pos.CENTER);

        actionSecondaire.setBorder(new Border(new BorderStroke(
            Color.rgb(172, 35, 42),
            BorderStrokeStyle.SOLID,
            new CornerRadii(18),
            new BorderWidths(2)
        )));

        actionSecondaire.setEffect(new DropShadow(
            0,
            0,
            4,
            Color.rgb(172, 35, 42)
        ));

        actionSecondaire.setFont(PoliceTexteUtilitaire.utiliserReadexPro(16));
        actionSecondaire.setTextFill(Color.rgb(172, 35, 42));

        actionSecondaire.setPadding(new Insets(12, 0, 12, 0));
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
