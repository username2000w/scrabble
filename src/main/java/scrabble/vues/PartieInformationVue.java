package scrabble.vues;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class PartieInformationVue extends VBox {
    private final Button jouerUnMotBouton;
    private final Button passerTourBouton;
    private final Button validerEchangeBouton;

    public PartieInformationVue() {
        super();

        jouerUnMotBouton = new Button("Jouer un mot");
        passerTourBouton = new Button("Passer son tour");
        validerEchangeBouton = new Button("Valider l'échange");
        // On le cache par défaut, doit apparaître seulement lors d'un échange actif.
        validerEchangeBouton.setVisible(false);

        getChildren().addAll(new Label("Joueur"), jouerUnMotBouton, passerTourBouton, validerEchangeBouton);
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
}
