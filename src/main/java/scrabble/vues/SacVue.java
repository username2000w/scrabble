package scrabble.vues;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import scrabble.gui.utils.ImageUtilitaire;
import scrabble.gui.utils.PoliceTexteUtilitaire;

public class SacVue extends VBox {
    private final Label nombreDeTuiles = new Label();

    public SacVue () {
        super();

        setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), null)));
        setAlignment(Pos.CENTER);

        setBorder(new Border(new BorderStroke(
            Color.rgb(172, 35, 42),
            BorderStrokeStyle.SOLID,
            new CornerRadii(18),
            new BorderWidths(2)
        )));

        setEffect(new DropShadow(
            0,
            0,
            4,
            Color.rgb(172, 35, 42)
        ));

        setPadding(new Insets(12, 0, 12, 0));

        Label description = new Label("tuiles restantes");
        description.setTextFill(Color.rgb(172, 35, 42));
        description.setFont(PoliceTexteUtilitaire.utiliserReadexPro(12, FontWeight.LIGHT));

        nombreDeTuiles.setTextFill(Color.rgb(172, 35, 42));
        nombreDeTuiles.setFont(PoliceTexteUtilitaire.utiliserReadexPro(56, FontWeight.SEMI_BOLD));

        Image imageSacFerme = ImageUtilitaire.chargerImage("sac_ferme.png");
        ImageView imageSac = new ImageView(imageSacFerme);

        this.getChildren().addAll(imageSac, nombreDeTuiles, description);
    }

    public void changerNombreDeTuiles(int nombre) {
        nombreDeTuiles.setText(String.valueOf(nombre));
    }

    public int nombreDeTuiles() {
    	return Integer.parseInt(nombreDeTuiles.getText());
    }
}
