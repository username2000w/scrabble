package scrabble.gui.utils;

import javafx.scene.image.Image;

import java.net.URL;
import java.util.Objects;

public class ImageUtilitaire {
    private ImageUtilitaire() {
        throw new IllegalStateException("Classe utilitaire");
    }

    /**
     * Permet de récupérer une image à partir d'un chemin.
     * Le chemin sera relatif au package "scrabble.images".
     * @param chemin
     * @return
     */
    public static Image chargerImage(String chemin) {
        String cheminRelatif = "images/" + chemin;
        URL cheminRessource = Objects.requireNonNull(ImageUtilitaire.class.getClassLoader().getResource(cheminRelatif));

        return new Image(cheminRessource.toExternalForm());
    }
}
