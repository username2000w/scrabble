package scrabble.controller;

import scrabble.vues.PartieVue;
import scrabble.vues.PlateauCaseTuile;
import scrabble.vues.PlateauCaseVue;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Contrôleur pour les tuiles sur les cases du plateau.
 * Une fois qu'une tuile est posée "définitivement" sur le plateau, elle ne peut plus être déplacée.
 * Cependant, quand elle ne l'est pas, elle peut être déplacée sur le plateau et dans le chevalet.
 */
public class PlateauCaseTuileController {
    private final PartieVue root;
    private final PlateauCaseTuile plateauCaseTuile;
    private final PlateauCaseVue plateauCaseVue;

    public PlateauCaseTuileController(PlateauCaseTuile plateauCaseTuile, PartieVue root, PlateauCaseVue plateauCaseVue) {
        this.plateauCaseTuile = plateauCaseTuile;
        this.root = root;
        this.plateauCaseVue = plateauCaseVue;
    }

    /**
     *
     */
    public void assignerGlisserDeposerEcouteurs() {
        final var plateau = root.plateau().getChildren();

        final var lastItemIndex = plateau.size() - 1;
        final var plateauCaseVueIndex = plateau.indexOf(plateauCaseVue);

        // On s'assure que la tuile est bien la dernière du plateau
        // pour qu'elle est bien au-dessus des autres tuiles.
        if (lastItemIndex != plateauCaseVueIndex) {
            final var lastItem = plateau.get(lastItemIndex);
            plateau.remove(lastItem);
            plateau.remove(plateauCaseVue);
            plateau.add(plateauCaseVueIndex, lastItem);
            plateau.add(lastItemIndex, plateauCaseVue);
        }

        plateauCaseTuile.setOnMousePressed(event -> {
            AtomicReference<Double> initialX = new AtomicReference<>(event.getSceneX());
            AtomicReference<Double> initialY = new AtomicReference<>(event.getSceneY());

            root.setOnMouseDragged(event1 -> {
                double movedX = event1.getX() - initialX.get();
                double movedY = event1.getY() - initialY.get();
                initialX.set(event1.getX());
                initialY.set(event1.getY());

                plateauCaseTuile.setTranslateX(plateauCaseTuile.getTranslateX() + movedX);
                plateauCaseTuile.setTranslateY(plateauCaseTuile.getTranslateY() + movedY);
            });
        });
    }
}
