package scrabble.controller;

import javafx.scene.Node;
import javafx.scene.control.Button;
import scrabble.model.*;
import scrabble.model.utils.Coordonee;
import scrabble.model.utils.Direction;
import scrabble.vues.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Ensemble de méthodes utilitaires pour la vue du chevalet.
 * @see ChevaletVue
 * @see Chevalet
 */
public class ChevaletVueController {
    private final PartieVue root;
    private final Joueur joueur;
    private final Plateau plateau;
    private final Sac sac;

    /**
     * À partir de cette position Y, on considère que la tuile
     * a été déplacée en dehors du chevalet : probablement dans le plateau.
     */
    private static final int DRAG_Y_EN_DEHORS_CHEVALET = -20;

    /**
     * Le controller du mot en cours.
     * `null` lorsqu'un mot n'est pas en cours d'assemblage.
     */
    private JouerMotController motEnCours = null;

    /**
     * Tuiles du chevalet qu'on est actuellement
     * en train de jouer sur le plateau = qui ont été "drag 'n dropé".
     */
    private final Map<Coordonee, Tuile> casesEnCours = new HashMap<>();

    public ChevaletVueController(PartieVue root, Joueur joueur, Plateau plateau, Sac sac) {
        this.root = root;
        this.joueur = joueur;
        this.plateau = plateau;
        this.sac = sac;
    }

    /**
     * On vide le chevalet dans la vue et on le remplit
     * avec les tuiles du chevalet (métier) actuel.
     *
     * On en profite pour réassigner les écouteurs de drag and drop
     * et tout autre écouteur nécessaire qui ont pu être perdus
     * lors de la suppression des tuiles.
     */
    public void rafraichirContenu () {
        root.chevalet().getChildren().clear();
        for (Tuile tuile : joueur.chevalet().tuiles()) {
            root.chevalet().ajouterLettre(tuile);
        }

        // On ajoute les écouteurs
        assignerDragDropEcouteurs();
    }

    public void melangerTuiles () {
        Collections.shuffle(joueur.chevalet().tuiles());
        rafraichirContenu();
    }

    /**
     * Assignation des écouteurs pour le "drag and drop"
     * des tuiles du chevalet.
     */
    private void assignerDragDropEcouteurs() {
        root.chevalet().getChildren().forEach(tuileVueNoeud -> {
            TuileVue tuileVue = (TuileVue) tuileVueNoeud;

            tuileVue.setOnMouseReleased(event -> {
                double currentY = tuileVue.getTranslateY() + event.getY();

                if (currentY < DRAG_Y_EN_DEHORS_CHEVALET) { // on est en dehors du chevalet.
                    double currentX = event.getSceneX();
                    currentY = event.getSceneY();

                    for (Node tuileVuePourEchangerNoeud : root.plateau().getChildren()) {
                        PlateauCaseVue plateauCase = (PlateauCaseVue) tuileVuePourEchangerNoeud;
                        double x = plateauCase.getLayoutX();
                        double y = plateauCase.getLayoutY();

                        if (currentX >= x && currentX <= x + plateauCase.getWidth() &&
                            currentY >= y && currentY <= y + plateauCase.getHeight()) {
                            Coordonee coordonee = root.plateau().coordoneeDeCaseVue(plateauCase);

                            // On vérifie si la case est déjà occupée.
                            if (casesEnCours.containsKey(coordonee)) break;
                            String lettre = tuileVue.lettre();

                            if (tuileVue.estJoker()) {
                                // On ouvre la fenêtre de sélection de lettre.
                                lettre = root.ouvrirJokerSelection();
                            }

                            // On supprime la tuile du chevalet du joueur.
                            int indexTuileDansChevalet = root.chevalet().getChildren().indexOf(tuileVue);
                            joueur.chevalet().tuiles().remove(indexTuileDansChevalet);
                            root.chevalet().getChildren().remove(tuileVue);

                            // On pose la tuile temporairement sur le plateau.
                            PlateauCaseTuile plateauCaseTuile = new PlateauCaseTuile(lettre, tuileVue.points());
                            PlateauCaseTuileController plateauCaseTuileController = new PlateauCaseTuileController(plateauCaseTuile, root, plateauCase);
                            plateauCase.poser(plateauCaseTuile);
                            plateauCaseTuileController.assignerGlisserDeposerEcouteurs();
                            // On ajoute la tuile à la liste des tuiles en cours.
                            casesEnCours.put(coordonee, new Tuile(lettre, tuileVue.points()));

                            Button annulerBouton = root.partieInformation().utiliserAnnulerBouton(.5);
                            if (casesEnCours.size() > 1) {
                                AtomicInteger caseX = new AtomicInteger(-1);
                                AtomicInteger caseY = new AtomicInteger(-1);

                                root.partieInformation().validerBouton().setOnMouseClicked(e -> {
                                    Direction direction = null;

                                    for (Map.Entry<Coordonee, Tuile> tuileActuelle : casesEnCours.entrySet()) {
                                        if (caseX.get() == -1) caseX.set(tuileActuelle.getKey().ligne());
                                        if (caseY.get() == -1) caseY.set(tuileActuelle.getKey().colonne());

                                        boolean memeLigne = caseX.get() == tuileActuelle.getKey().ligne();
                                        boolean memeColonne = caseY.get() == tuileActuelle.getKey().colonne();
                                        if (!memeLigne && !memeColonne) {
                                            System.out.println("Les tuiles ne sont pas alignées.");
                                            return;
                                        }

                                        // On détermine la direction.
                                        direction = memeLigne ? Direction.HORIZONTAL : Direction.VERTICAL;
                                    }

                                    System.out.println("Les tuiles sont alignées.");
                                    System.out.println(direction);

                                    boolean estSansEspace = true;
                                    ArrayList<Tuile> tuiles = new ArrayList<>();
                                    Coordonee coordoneeDebut = null;

                                    // On remet dans l'ordre les tuiles.
                                    for (int i = 0; i < 15; i++) {
                                        Coordonee coordoneeDeValeur = direction == Direction.HORIZONTAL
                                            ? new Coordonee(caseX.get(), i)
                                            : new Coordonee(i, caseY.get());

                                        Tuile tuile = casesEnCours.get(coordoneeDeValeur);

                                        // Première lettre.
                                        if (coordoneeDebut == null) {
                                            if (tuile == null) continue;
                                            coordoneeDebut = coordoneeDeValeur;
                                        }
                                        else {
                                            if (tuile == null) {
                                                if (tuiles.size() == casesEnCours.size()) break;
                                                estSansEspace = false;
                                                break;
                                            }
                                        }

                                        tuiles.add(tuile);
                                    }

                                    if (!estSansEspace) {
                                        System.out.println("Il y a un espace entre les tuiles.");
                                        return;
                                    }

                                    System.out.println(tuiles.stream().map(Tuile::lettre).reduce("", String::concat));
                                });
                                root.partieInformation().validerBouton().setOpacity(1);
                            }

                            annulerBouton.setOnMouseClicked(e -> {
                                for (Map.Entry<Coordonee, Tuile> tuileActuelle : casesEnCours.entrySet()) {
                                    // On l'enlève du plateau.
                                    plateau.enleverTuile(tuileActuelle.getKey());
                                    root.plateau().caseSitueA(tuileActuelle.getKey()).retirerCaseTuile();

                                    // On la remet dans le chevalet du joueur.
                                    Tuile tuile = tuileActuelle.getValue();

                                    // Si joker, on remet un joker sans lettre utilisée.
                                    if (tuile.estJoker()) tuile = new Tuile();

                                    joueur.ajouterLettreAuChevalet(tuile);
                                }

                                rafraichirContenu();
                                // On vide la liste des tuiles en cours.
                                casesEnCours.clear();
                                // On rétablit à l'état par défaut.
                                root.partieInformation().utiliserPasserTourBouton(1);
                                root.partieInformation().validerBouton().setOpacity(.5);
                            });
                            annulerBouton.setOpacity(1);

                            // On sort de la fonction pour ne pas itérer sur les
                            // enfants qui ont été supprimés.
                            break;
                        }
                    }
                }
                else { // on est dans le chevalet.
                    double currentX = tuileVue.getTranslateX() + tuileVue.getLayoutX() + event.getX();

                    for (Node tuileVuePourEchanger : root.chevalet().getChildren()) {
                        if (!tuileVuePourEchanger.equals(tuileVue)) {
                            // Espacement à gauche + espace entre chaque tuile.
                            double x = tuileVuePourEchanger.getLayoutX() - (12);

                            if (currentX >= x && currentX <= x + TuileVue.TUILE_TAILLE_PX) {
                                int anciennePositionIndex = root.chevalet().getChildren().indexOf(tuileVue);
                                int nouvellePositionIndex = root.chevalet().getChildren().indexOf(tuileVuePourEchanger);

                                // On échange la position des tuiles dans le chevalet (métier).
                                Collections.swap(joueur.chevalet().tuiles(), anciennePositionIndex, nouvellePositionIndex);

                                // On met à jour la vue.
                                rafraichirContenu();

                                // On sort de la fonction pour ne pas itérer sur les
                                // enfants qui ont été supprimés.
                                break;
                            }
                        }
                    }
                }

                tuileVue.setTranslateX(0);
                tuileVue.setTranslateY(0);

                root.setOnMouseReleased(null);
                root.setOnMouseDragged(null);
            });

            tuileVue.setOnMousePressed(event -> {
                AtomicReference<Double> initialX = new AtomicReference<>(event.getSceneX());
                AtomicReference<Double> initialY = new AtomicReference<>(event.getSceneY());

                root.setOnMouseDragged(event1 -> {
                    int movedX = (int) (event1.getX() - initialX.get());
                    int movedY = (int) (event1.getY() - initialY.get());
                    initialX.set(event1.getX());
                    initialY.set(event1.getY());

                    tuileVue.setTranslateX(tuileVue.getTranslateX() + movedX);
                    tuileVue.setTranslateY(tuileVue.getTranslateY() + movedY);
                });
            });
        });
    }
}
