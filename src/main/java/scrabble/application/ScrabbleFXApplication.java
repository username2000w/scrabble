package scrabble.application;

import com.pixelduke.window.ThemeWindowManager;
import com.pixelduke.window.ThemeWindowManagerFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scrabble.vues.PartieVue;

public class ScrabbleFXApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        PartieVue root = new PartieVue();

        Scene scene = new Scene(root, 1180, 982);
        primaryStage.setResizable(true);

        primaryStage.setTitle("Scrabble");
        primaryStage.setScene(scene);
        primaryStage.show();

        // On active le mode sombre pour la fenêtre.
        ThemeWindowManager themeWindowManager = ThemeWindowManagerFactory.create();
        themeWindowManager.setDarkModeForWindowFrame(primaryStage, true);
    }
}
