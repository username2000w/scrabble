package scrabble.vues;

import javafx.scene.layout.*;
import scrabble.gui.utils.ImageUtilitaire;

/**
 * Vue qui représente une partie de Scrabble.
 * Elle va contenir le plateau, les points des joueurs, les chevalets, etc.
 */
public class PartieVue extends HBox {
    public PartieVue() {
        super();

        BackgroundImage imageDeFond = new BackgroundImage(
            ImageUtilitaire.chargerImage("fond.png"),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(
                100,
                100,
                true,
                true,
                true,
                true
            )
        );

        Background fond = new Background(imageDeFond);

        // On ajoute l'image de fond à la vue.
        setBackground(fond);
    }
}
