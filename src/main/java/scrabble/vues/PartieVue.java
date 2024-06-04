package scrabble.vues;

import javafx.scene.layout.*;
import scrabble.controller.PartieController;
import scrabble.gui.utils.ImageUtilitaire;

/**
 * Vue qui représente une partie de Scrabble.
 * Elle va contenir le plateau, les points des joueurs, les chevalets, etc.
 */
public class PartieVue extends HBox {
    private final ActionsVue actions;
    private final PlateauVue plateau;
    private final PartieInformationVue partieInformation;

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

        // Conteneur pour le plateau et les actions (partie gauche)
        // TODO: Séparer dans une autre classe.
        VBox conteneur = new VBox();
        actions = new ActionsVue();
        plateau = new PlateauVue();
        VBox.setVgrow(plateau, Priority.ALWAYS);
        conteneur.getChildren().addAll(plateau, actions);

        // Demander au conteneur de prendre toute la place disponible en largeur.
        HBox.setHgrow(conteneur, Priority.ALWAYS);

        partieInformation = new PartieInformationVue();
        getChildren().addAll(conteneur, partieInformation);
    }

    public PlateauVue plateau() {
        return plateau;
    }

    public PartieInformationVue partieInformation() {
        return partieInformation;
    }

    public ChevaletVue chevalet() {
        return actions.chevalet();
    }
}
