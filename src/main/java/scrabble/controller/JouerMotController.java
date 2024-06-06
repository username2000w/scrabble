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
                LettreAlphabetFrancais lettre = LettreAlphabetFrancais.valueOf(motField.getText());

                gererLettre(lettre);
                this.handle(event);
            }
            else if (res.startsWith("FINISH@")) {
                LettreAlphabetFrancais lettre = LettreAlphabetFrancais.valueOf(motField.getText());

                int NombreLettrePosee = mot.nombreDeLettre();
                gererLettre(lettre);

                if (verificationMot(mot, tour, NombreLettrePosee)) {
                    jouerMotSurPlateau();
                }

                mot = null;
                vue.chevalet().getChildren().clear();
                joueur.getChevalet().remplirChevalet(sac);
                for (LettreAlphabetFrancais nouvelleLettre : joueur.getChevalet().getLettres()) {
                    if (nouvelleLettre == LettreAlphabetFrancais.JOKER) {
                        vue.chevalet().ajouterLettreJoker();
                    }
                    else {
                        vue.chevalet().ajouterLettre(nouvelleLettre.toString(), nouvelleLettre.getPoints());
                    }
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
     * @param lettre
     */
    private void gererLettre(LettreAlphabetFrancais lettre) {
        joueur.getChevalet().retirerLettre(lettre);
        if (lettre == LettreAlphabetFrancais.JOKER) {
            vue.chevalet().retirerLettre(" "); // JOKER = espace vide

            // text input dialog
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Modifier votre joker en une lettre");
            String lettreJoker = dialog.showAndWait().get();

            if (lettreJoker.length() != 1) {
                throw new IllegalArgumentException("Le joker doit être remplacé par une seule lettre.");
            }

            mot.ajouterLettre(LettreAlphabetFrancais.valueOf(lettreJoker));
        }
        else {
            vue.chevalet().retirerLettre(lettre.toString());
            mot.ajouterLettre(lettre);
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

    private boolean verificationMot(Mot mot, int tour, int NombreLettrePosee) {
        Coordonee coordonnees = mot.getCoordoneeDebut();
        Direction directionMot = mot.getDirection();
        if (tour > 0) {
            if (NombreLettrePosee > 1) {
                if (directionMot.equals(Direction.HORIZONTAL)) {
                    int colonneDebutMot = coordonnees.getColonne();
                    int ligneDebutMot = coordonnees.getLigne();
                    if (!plateau.getPlateau()[ligneDebutMot][colonneDebutMot - 1].estVide() || !plateau.getPlateau()[ligneDebutMot][colonneDebutMot + 1].estVide() || !plateau.getPlateau()[ligneDebutMot - 1][colonneDebutMot].estVide()) {
                        return true;
                    }
                    for (int ligne = ligneDebutMot; ligne < ligneDebutMot + mot.nombreDeLettre(); ligne++) {
                        if (!plateau.getPlateau()[ligne][colonneDebutMot - 1].estVide() || !plateau.getPlateau()[ligne][colonneDebutMot + 1].estVide()) {
                            return true;
                        }
                    }
                } else {
                    int colonneDebutMot = coordonnees.getColonne();
                    int ligneDebutDebut = coordonnees.getLigne();
                    if (!plateau.getPlateau()[ligneDebutDebut - 1][colonneDebutMot].estVide() || !plateau.getPlateau()[ligneDebutDebut + 1][colonneDebutMot].estVide() || !plateau.getPlateau()[ligneDebutDebut][colonneDebutMot - 1].estVide()) {
                        return true;
                    }
                    for (int colonne = colonneDebutMot; colonne < colonneDebutMot + mot.nombreDeLettre(); colonne++) {
                        if (!plateau.getPlateau()[ligneDebutDebut - 1][colonne].estVide() || !plateau.getPlateau()[ligneDebutDebut + 1][colonne].estVide()) {
                            return true;
                        }
                    }
                }
            } else {
                int colonneDebutMot = coordonnees.getColonne();
                int ligneDebutDebut = coordonnees.getLigne();
                return !plateau.getPlateau()[ligneDebutDebut][colonneDebutMot - 1].estVide() || !plateau.getPlateau()[ligneDebutDebut][colonneDebutMot + 1].estVide()
                        || !plateau.getPlateau()[ligneDebutDebut - 1][colonneDebutMot].estVide() || !plateau.getPlateau()[ligneDebutDebut + 1][colonneDebutMot].estVide();
            }
        } else {
            // Regarde si le mot est bien placé sur l'étoile
            if (directionMot.equals(Direction.HORIZONTAL)) {
                int ligne = coordonnees.getLigne();
                int colonne = coordonnees.getColonne();
                for (int colonneMot = colonne; colonneMot < colonne + mot.nombreDeLettre(); colonneMot++) {
                    if (plateau.getPlateau()[ligne][colonneMot].getBonus() == Bonus.ETOILE) {
                        return true;
                    }
                }
            } else {
                int ligne = coordonnees.getLigne();
                int colonne = coordonnees.getColonne();
                for (int ligneMot = ligne; ligneMot < ligne + mot.nombreDeLettre(); ligneMot++) {
                    if (plateau.getPlateau()[ligneMot][colonne].getBonus() == Bonus.ETOILE) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
