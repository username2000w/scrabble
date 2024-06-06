package scrabble.composants;

import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import scrabble.gui.utils.ImageUtilitaire;

/**
 * Composant pour le bouton mélanger du chevalet.
 */
public class BoutonMelanger extends StackPane {
    public BoutonMelanger () {
        // On ajoute l'icône du bouton.
        Image icone = ImageUtilitaire.chargerImage("refresh.png");
        ImageView iconeVue = new ImageView(icone);
        getChildren().add(iconeVue);

        setMaxWidth(48);
        setMinWidth(48);
        setMaxHeight(48);
        setTranslateX(-26);
        setTranslateY(10);
        setBackground(new Background(new BackgroundFill(
                Color.WHITE,
                new CornerRadii(52, true),
                new Insets(1)
        )));

        setOnMouseClicked(event -> {
            // TODO: Refactor dans un controller.
            System.out.println("TODO: Mélanger les tuiles du chevalet");
        });

        setBorder(new Border(new BorderStroke(
                Color.rgb(130, 27, 32),
                BorderStrokeStyle.SOLID,
                new CornerRadii(50, true),
                new BorderWidths(2)
        )));

        setEffect(new DropShadow(
                0,
                0,
                4,
                Color.rgb(130, 27, 32)
        ));
    }
}
