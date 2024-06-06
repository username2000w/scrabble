package scrabble.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import scrabble.model.Chevalet;
import scrabble.model.LettreAlphabetFrancais;
import scrabble.model.Sac;
import scrabble.vues.PartieVue;
import scrabble.vues.TuileVue;

import java.util.ArrayList;
import java.util.Objects;

public class PasserTourController implements EventHandler<MouseEvent> {
    private final PartieVue vue;
    // L'état "passer tour" est non actif lors de l'instanciation de la classe.
    private boolean actif = false;
    // Liste des tuiles sélectionnée pour un échange.
    private final ArrayList<TuileVue> selection = new ArrayList<>();

    public PasserTourController (PartieVue vue, Chevalet chevalet, Sac sac) {
        this.vue = vue;
        vue.partieInformation().validerEchangeBouton().setOnMouseClicked(event -> {
            if (!actif) return;
            if (selection.isEmpty()) return;

            selection.forEach(tuileVue -> {
                int tuileIndex = vue.chevalet().getChildren().indexOf(tuileVue);
                LettreAlphabetFrancais lettre = chevalet.echanger(sac, tuileIndex);
                vue.chevalet().ajouterLettreA(lettre.toString(), lettre.getPoints(), tuileIndex);

            });

            // On désactive l'état "passer tour".
            actif = false;
            selection.clear();
            vue.partieInformation().validerEchangeBouton().setVisible(false);
        });
    }

    @Override
    public void handle(MouseEvent event) {
        // Inverse l'état actif du bouton "Passer son tour".
        actif = !actif;
        // On décide d'afficher ou non le bouton pour valider l'échange.
        vue.partieInformation().validerEchangeBouton().setVisible(actif);

        // Si on désactive l'échange...
        if (!actif) {
            // On supprime la liste de sélection actuelle
            selection.clear();
        }

        vue.chevalet().getChildren().forEach(node -> {
            if (actif) { // on ajoute un gestionnaire de clic.
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, tuileEchangeGestionnaireClick);
            }
            else { // on enlève le gestionnaire de clic.
                node.removeEventHandler(MouseEvent.MOUSE_CLICKED, tuileEchangeGestionnaireClick);
            }
        });
    }

    /**
     * Gestionnaire de clic sur une tuile du chevalet
     * lorsque l'état "passer tour" est actif.
     */
    private final EventHandler<MouseEvent> tuileEchangeGestionnaireClick = event -> {
        // On récupère la tuile cliquée3.
        TuileVue tuile = (TuileVue) event.getSource();
        if (selection.contains(tuile)) {
            // On retire la tuile de la sélection.
            selection.remove(tuile);
            System.out.println("Tuile désélectionnée : " + tuile.lettre());
        }
        else {
            // On ajoute la tuile à la sélection.
            selection.add(tuile);
            System.out.println("Tuile sélectionnée : " + tuile.lettre());
        }
    };
}
