package scrabble.gui.utils;

import javafx.scene.Node;
import scrabble.model.Chevalet;
import scrabble.model.Joueur;
import scrabble.model.Tuile;
import scrabble.vues.*;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Ensemble de méthodes utilitaires pour la vue du chevalet.
 * @see ChevaletVue
 * @see Chevalet
 */
public class ChevaletVueUtilitaire {
    // Constructeur privé pour empêcher l'instanciation de la classe.
    private ChevaletVueUtilitaire() {}

    /**
     * On vide le chevalet dans la vue et on le remplit
     * avec les tuiles du chevalet (métier) actuel.
     *
     * On en profite pour réassigner les écouteurs de drag and drop
     * et tout autre écouteur nécessaire qui ont pu être perdus
     * lors de la suppression des tuiles.
     */
    public static void rafraichirContenu (PartieVue root, Joueur joueur) {
        root.chevalet().getChildren().clear();
        for (Tuile tuile : joueur.chevalet().tuiles()) {
            root.chevalet().ajouterLettre(tuile);
        }

        // On ajoute les écouteurs
        assignerDragDropEcouteurs(root, joueur);
    }

    public static void melangerTuiles (PartieVue root, Joueur joueur) {
        Collections.shuffle(joueur.chevalet().tuiles());
        rafraichirContenu(root, joueur);
    }

    /**
     * À partir de cette position Y, on considère que la tuile
     * a été déplacée en dehors du chevalet : probablement dans le plateau.
     */
    private static final int DRAG_Y_EN_DEHORS_CHEVALET = -20;

    /**
     * Assignation des écouteurs pour le "drag and drop"
     * des tuiles du chevalet.
     */
    private static void assignerDragDropEcouteurs(PartieVue root, Joueur joueur) {
        root.chevalet().getChildren().forEach(tuileVueNoeud -> {
            TuileVue tuileVue = (TuileVue) tuileVueNoeud;

            tuileVue.setOnMouseReleased(event -> {
                double currentY = tuileVue.getTranslateY() + event.getY();

                if (currentY < DRAG_Y_EN_DEHORS_CHEVALET) { // on est en dehors du chevalet.
                    double currentX = event.getSceneX();
                    currentY = event.getSceneY();

                    for (Node tuileVuePourEchangerNoeud : root.plateau().getChildren()) {
                        PlateauCaseVue tuileVuePourEchanger = (PlateauCaseVue) tuileVuePourEchangerNoeud;
                        double x = tuileVuePourEchanger.getLayoutX();
                        double y = tuileVuePourEchanger.getLayoutY();

                        if (currentX >= x && currentX <= x + TuileVue.TUILE_TAILLE_PX &&
                            currentY >= y && currentY <= y + TuileVue.TUILE_TAILLE_PX) {
                            tuileVuePourEchanger.poser(new PlateauCaseTuile(tuileVue.lettre(), tuileVue.points()));
                            tuileVue.setOpacity(.25);

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
                            double y = tuileVuePourEchanger.getLayoutY(); // TODO: vérifier l'axe y pour les tuiles

                            if (currentX >= x && currentX <= x + TuileVue.TUILE_TAILLE_PX) {
                                int anciennePositionIndex = root.chevalet().getChildren().indexOf(tuileVue);
                                int nouvellePositionIndex = root.chevalet().getChildren().indexOf(tuileVuePourEchanger);

                                // On échange la position des tuiles dans le chevalet (métier).
                                Collections.swap(joueur.chevalet().tuiles(), anciennePositionIndex, nouvellePositionIndex);

                                // On met à jour la vue.
                                rafraichirContenu(root, joueur);

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
