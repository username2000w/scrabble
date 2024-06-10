package scrabble.application;

import com.pixelduke.window.ThemeWindowManager;
import com.pixelduke.window.ThemeWindowManagerFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scrabble.controller.PasserTourController;
import scrabble.controller.ChevaletVueController;
import scrabble.gui.Console;
import scrabble.model.*;
import scrabble.model.utils.exception.SacVideException;
import scrabble.vues.PartieVue;

public class ScrabbleFXApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // On définit les éléments de la partie.
        Sac sac = new Sac();
        Plateau plateau = new Plateau();
        Joueur joueur = new Joueur(new Chevalet(), "Joueur 1");

        // On crée la vue principale.
        PartieVue root = new PartieVue();
        ChevaletVueController chevaletVueController = new ChevaletVueController(root, joueur, plateau, sac);

        // On remplit le chevalet du joueur.
        try {
            joueur.remplirChevalet(sac);
        } catch (SacVideException e) {
            Console.message("Le sac est vide.");
        }

        // On actualise le contenu dans l'IHM.
        chevaletVueController.rafraichirContenu();

        // On peut passer son tour dès le début de la partie.
        root.partieInformation().utiliserPasserTourBouton(1).setOnMouseClicked(new PasserTourController(root, joueur.chevalet(), sac));
        root.boutonMelanger().setOnMouseClicked(event -> chevaletVueController.melangerTuiles());
        root.partieInformation().sacVue().changerNombreDeTuiles(sac.nombreDeTuiles());

        // On met en place la scène.
        Scene scene = new Scene(root, 1180, 982);
        primaryStage.setTitle("Scrabble");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);

        // On affiche la fenêtre.
        primaryStage.show();

        // On active le mode sombre pour la fenêtre.
        ThemeWindowManager themeWindowManager = ThemeWindowManagerFactory.create();
        themeWindowManager.setDarkModeForWindowFrame(primaryStage, true);
    }
}
