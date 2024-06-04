package scrabble.controller;

import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import scrabble.model.*;
import scrabble.model.utils.Coordonee;
import scrabble.model.utils.Direction;
import scrabble.model.utils.exception.HorsPlateauException;
import scrabble.vues.PartieVue;

public class JouerMotController implements EventHandler<MouseEvent> {
    private final Plateau plateau;
    private final Joueur joueur;
    private final Sac sac;

    private final PartieVue vue;
    private Mot mot = null;

    public JouerMotController(Joueur joueur, Plateau plateau, Sac sac, PartieVue vue) {
        this.plateau = plateau;
        this.joueur = joueur;
        this.sac = sac;

        this.vue = vue;
    }

    @Override
    public void handle(MouseEvent event) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Jouer un mot");

        if (mot == null) {
            TextField xField = new TextField();
            xField.setPromptText("ligne");
            TextField yField = new TextField();
            yField.setPromptText("colonne");
            TextField directionField = new TextField();
            directionField.setPromptText("Direction (HORIZONTAL, VERTICAL)");
            VBox vbox = new VBox();
            vbox.getChildren().addAll(new Label("Coordonnées"), xField, yField, directionField);
            dialog.getDialogPane().setContent(vbox);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.NEXT, ButtonType.CANCEL);
            dialog.showAndWait();

            int ligne = Integer.parseInt(xField.getText());
            int colonne = Integer.parseInt(yField.getText());
            Coordonee coordonee = new Coordonee(ligne, colonne);
            Direction direction = Direction.valueOf(directionField.getText());

            mot = new Mot(coordonee, direction);
            this.handle(event);
        }
        else {
            TextField motField = new TextField();
            motField.setPromptText("Lettre");
            dialog.getDialogPane().setContent(motField);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.NEXT, ButtonType.FINISH, ButtonType.CANCEL);

            dialog.setResultConverter((ButtonType type) ->  {
                if ( type == ButtonType.NEXT ) {
                    return "NEXT@" + motField.getText();
                }
                else if ( type == ButtonType.FINISH) {
                    return "FINISH@" + motField.getText();
                }
                else {
                    return null;
                }
            });

            String res = dialog.showAndWait().get();
            if (res.startsWith("NEXT@")) {
                LettreAlphabetFrancais lettre = LettreAlphabetFrancais.valueOf(motField.getText());

                joueur.getChevalet().retirerLettre(lettre);
                vue.chevalet().retirerLettre(lettre.toString());

                mot.ajouterLettre(lettre);
                this.handle(event);
            }
            else if (res.startsWith("FINISH@")) {
                LettreAlphabetFrancais lettre = LettreAlphabetFrancais.valueOf(motField.getText());

                joueur.getChevalet().retirerLettre(lettre);
                vue.chevalet().retirerLettre(lettre.toString());

                mot.ajouterLettre(lettre);
                jouerMotSurPlateau();

                mot = null;
                vue.chevalet().getChildren().clear();
                joueur.getChevalet().remplirChevalet(sac);
                for (LettreAlphabetFrancais nouvelleLettre : joueur.getChevalet().getLettres()) {
                    if (nouvelleLettre == LettreAlphabetFrancais.JOKER) {
                        vue.chevalet().ajouterLettre();
                    }
                    else {
                        vue.chevalet().ajouterLettre(nouvelleLettre.toString(), nouvelleLettre.getPoints());
                    }
                }
            }
            else {
                mot = null;
            }
        }
    }

    /**
     * Permet de jouer un mot sur le plateau.
     * On met à jour les données de la classe métier
     * et on met aussi à jour l'interface de l'utilisateur.
     */
    private void jouerMotSurPlateau() {
        Direction directionMot = mot.getDirection();

        Coordonee coordonnees = mot.getCoordoneeDebut();
        int ligne = coordonnees.getLigne();
        int colonne = coordonnees.getColonne();

        for (LettreAlphabetFrancais lettre : mot.getMot()) {
            try {
                if (Boolean.TRUE.equals(plateau.getPlateau()[ligne][colonne].estVide())) {
                    Coordonee pos = new Coordonee(ligne, colonne);

                    plateau.placerlettre(lettre, pos);
                    vue.plateau().caseSitueA(pos).assigner(lettre.toString(), lettre.getPoints());
                } else {
                    if (directionMot.equals(Direction.HORIZONTAL)) colonne++;
                    else ligne++;

                    System.out.println("Une lettre est déjà présente à cet endroit, on décale la lettre.");
                    Coordonee pos = new Coordonee(ligne, colonne);

                    plateau.placerlettre(lettre, pos);
                    vue.plateau().caseSitueA(pos).assigner(lettre.toString(), lettre.getPoints());
                }
            } catch (HorsPlateauException e) {
                throw new RuntimeException(e);
            }

            if (directionMot.equals(Direction.HORIZONTAL)) colonne++;
            else ligne++;
        }
    }
}
