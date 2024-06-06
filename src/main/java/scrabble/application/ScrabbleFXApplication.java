package scrabble.application;

import com.pixelduke.window.ThemeWindowManager;
import com.pixelduke.window.ThemeWindowManagerFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scrabble.controller.JouerMotController;
import scrabble.controller.PartieController;
import scrabble.controller.PasserTourController;
import scrabble.model.Chevalet;
import scrabble.model.Joueur;
import scrabble.model.Plateau;
import scrabble.model.Sac;
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

        // On assigne les controllers aux éléments de la vue.
        new PartieController(plateau, joueur, sac, root);
        root.partieInformation().jouerUnMotBouton().setOnMouseClicked(new JouerMotController(joueur, plateau, sac, root));
        root.partieInformation().passerTourBouton().setOnMouseClicked(new PasserTourController(root, joueur.chevalet(), sac));

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
