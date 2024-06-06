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
    private int tour = 0;

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
            motField.setPromptText("Lettre (ou JOKER)");
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
                String lettre = motField.getText();
                if (lettre.equals("JOKER")) {
                    lettre = " "; // = JOKER
                }

                Tuile tuile = joueur.chevalet().tuileAvecLettre(lettre);
                if (tuile != null) gererLettre(tuile);
                this.handle(event);
            }
            else if (res.startsWith("FINISH@")) {
                Tuile tuile = joueur.chevalet().tuileAvecLettre(motField.getText());

                int nombreLettrePosee = mot.nombreDeLettre();
                gererLettre(tuile);

                if (verificationMot(mot, tour, nombreLettrePosee)) {
                    jouerMotSurPlateau();
                }

                mot = null;
                vue.chevalet().getChildren().clear();
                joueur.chevalet().remplirChevalet(sac);
                for (Tuile nouvelleTuile : joueur.chevalet().tuiles()) {
                    vue.chevalet().ajouterLettre(nouvelleTuile);
                }

                tour++;
            }
            else {
                mot = null;
            }
        }
    }

    /**
     * Retire la lettre du chevalet du joueur et l'ajoute au mot.
     * On gère le cas du JOKER en même temps.
     */
    private void gererLettre(Tuile tuile) {
        joueur.chevalet().retirerLettre(tuile);
        if (tuile.estJoker()) {
            vue.chevalet().retirerLettre(" "); // JOKER = espace vide

            // text input dialog
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Modifier votre joker en une lettre");
            String lettreJoker = dialog.showAndWait().get();

            if (lettreJoker.length() != 1) {
                throw new IllegalArgumentException("Le joker doit être remplacé par une seule lettre.");
            }

            Joker joker = (Joker) tuile;
            joker.changerLettre(LettreAlphabetFrancais.valueOf(lettreJoker));

            mot.ajouterLettre(joker);
        }
        else {
            vue.chevalet().retirerLettre(tuile.toString());
            mot.ajouterLettre(tuile);
        }
    }

    /**
     * Permet de jouer un mot sur le plateau.
     * On met à jour les données de la classe métier
     * et on met aussi à jour l'interface de l'utilisateur.
     */
    private void jouerMotSurPlateau() {
        Direction directionMot = mot.direction();

        Coordonee coordonnees = mot.coordoneeDebut();
        int ligne = coordonnees.ligne();
        int colonne = coordonnees.colonne();

        for (Tuile tuile : mot.getMot()) {
            try {
                if (Boolean.TRUE.equals(plateau.plateau()[ligne][colonne].estVide())) {
                    Coordonee pos = new Coordonee(ligne, colonne);

                    plateau.placerTuile(tuile, pos);
                    vue.plateau().caseSitueA(pos).assigner(tuile.toString(), tuile.points());
                } else {
                    if (directionMot.equals(Direction.HORIZONTAL)) colonne++;
                    else ligne++;

                    Coordonee pos = new Coordonee(ligne, colonne);

                    plateau.placerTuile(tuile, pos);
                    vue.plateau().caseSitueA(pos).assigner(tuile.toString(), tuile.points());
                }
            } catch (HorsPlateauException e) {
                throw new RuntimeException(e);
            }

            if (directionMot.equals(Direction.HORIZONTAL)) colonne++;
            else ligne++;
        }
    }

    private boolean verificationMot(Mot mot, int tour, int nombreLettrePosee) {
        Coordonee coordonnees = mot.coordoneeDebut();
        Direction directionMot = mot.direction();
        if (tour > 0) {
            if (nombreLettrePosee > 1) {
                if (directionMot.equals(Direction.HORIZONTAL)) {
                    int colonneDebutMot = coordonnees.colonne();
                    int ligneDebutMot = coordonnees.ligne();
                    if (!plateau.plateau()[ligneDebutMot][colonneDebutMot - 1].estVide() || !plateau.plateau()[ligneDebutMot][colonneDebutMot + 1].estVide() || !plateau.plateau()[ligneDebutMot - 1][colonneDebutMot].estVide()) {
                        return true;
                    }
                    for (int ligne = ligneDebutMot; ligne < ligneDebutMot + mot.nombreDeLettre(); ligne++) {
                        if (!plateau.plateau()[ligne][colonneDebutMot - 1].estVide() || !plateau.plateau()[ligne][colonneDebutMot + 1].estVide()) {
                            return true;
                        }
                    }
                } else {
                    int colonneDebutMot = coordonnees.colonne();
                    int ligneDebutDebut = coordonnees.ligne();
                    if (!plateau.plateau()[ligneDebutDebut - 1][colonneDebutMot].estVide() || !plateau.plateau()[ligneDebutDebut + 1][colonneDebutMot].estVide() || !plateau.plateau()[ligneDebutDebut][colonneDebutMot - 1].estVide()) {
                        return true;
                    }
                    for (int colonne = colonneDebutMot; colonne < colonneDebutMot + mot.nombreDeLettre(); colonne++) {
                        if (!plateau.plateau()[ligneDebutDebut - 1][colonne].estVide() || !plateau.plateau()[ligneDebutDebut + 1][colonne].estVide()) {
                            return true;
                        }
                    }
                }
            } else {
                int colonneDebutMot = coordonnees.colonne();
                int ligneDebutDebut = coordonnees.ligne();
                return !plateau.plateau()[ligneDebutDebut][colonneDebutMot - 1].estVide() || !plateau.plateau()[ligneDebutDebut][colonneDebutMot + 1].estVide()
                        || !plateau.plateau()[ligneDebutDebut - 1][colonneDebutMot].estVide() || !plateau.plateau()[ligneDebutDebut + 1][colonneDebutMot].estVide();
            }
        } else {
            int ligne = coordonnees.ligne();
            int colonne = coordonnees.colonne();

            // Regarde si le mot est bien placé sur l'étoile
            if (directionMot.equals(Direction.HORIZONTAL)) {
                for (int colonneMot = colonne; colonneMot < colonne + mot.nombreDeLettre(); colonneMot++) {
                    if (plateau.plateau()[ligne][colonneMot].bonus() == Bonus.ETOILE) {
                        return true;
                    }
                }
            } else {
                for (int ligneMot = ligne; ligneMot < ligne + mot.nombreDeLettre(); ligneMot++) {
                    if (plateau.plateau()[ligneMot][colonne].bonus() == Bonus.ETOILE) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
