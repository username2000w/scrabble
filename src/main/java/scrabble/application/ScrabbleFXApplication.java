package scrabble.application;

import com.pixelduke.window.ThemeWindowManagerFactory;
import com.pixelduke.window.Win11ThemeWindowManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import scrabble.vues.ActionsVue;
import scrabble.vues.PartieVue;
import scrabble.vues.PlateauVue;

public class ScrabbleFXApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        PartieVue root = new PartieVue();
        VBox conteneur = new VBox();

        ActionsVue actions = new ActionsVue();
        PlateauVue plateau = new PlateauVue();
        conteneur.getChildren().addAll(plateau, actions);
        VBox.setVgrow(plateau, Priority.ALWAYS);
        root.getChildren().add(conteneur);

        Scene scene = new Scene(root, 1180, 982);
        primaryStage.setResizable(false);

        primaryStage.setTitle("Scrabble");
        primaryStage.setScene(scene);
        primaryStage.show();

        // On active le mode sombre pour la fenêtre sur Windows 11.
        Win11ThemeWindowManager themeWindowManager = (Win11ThemeWindowManager) ThemeWindowManagerFactory.create();
        themeWindowManager.setDarkModeForWindowFrame(primaryStage, true);
    }
}
