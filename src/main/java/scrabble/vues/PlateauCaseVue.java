package scrabble.vues;

import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import scrabble.gui.utils.PoliceTexteUtilitaire;

public class PlateauCaseVue extends Pane {
    private final String placeholder;

    private String lettre;
    private final Label contenuLabel;
    private int points = -1;
    private final Label pointsLabel;

    public static final Color LD_COULEUR = Color.rgb(87, 149, 191);
    public static final Color MT_COULEUR = Color.rgb(190, 81, 66);
    public static final Color LT_COULEUR = Color.rgb(8, 85, 150);
    public static final Color MD_OU_ETOILE_COULEUR = Color.rgb(204, 102, 102);

    /**
     * Une case spéciale.
     */
    public PlateauCaseVue (String placeholder, Color couleurFond) {
        super();

        // Si jamais la case est spéciale.
        this.placeholder = placeholder;

        this.contenuLabel = new Label(placeholder);
        contenuLabel.setFont(PoliceTexteUtilitaire.utiliserReadexPro(24));
        // On centre la lettre dans la tuile en utilisant ses positions.
        contenuLabel.layoutXProperty().bind(widthProperty().subtract(contenuLabel.widthProperty()).divide(2));
        contenuLabel.layoutYProperty().bind(heightProperty().subtract(contenuLabel.heightProperty()).divide(2));

        this.pointsLabel = new Label();
        pointsLabel.setFont(PoliceTexteUtilitaire.utiliserReadexPro(12, FontWeight.BOLD));
        // On la met en bas à droite de la tuile (décalage de 4px).
        pointsLabel.layoutXProperty().bind(widthProperty().subtract(pointsLabel.widthProperty()).subtract(4));
        pointsLabel.layoutYProperty().bind(heightProperty().subtract(pointsLabel.heightProperty()).subtract(2));
        pointsLabel.setTextFill(Color.rgb(70, 70, 70));

        getChildren().addAll(contenuLabel, pointsLabel);

        setBackground(new Background(new BackgroundFill(
            couleurFond,
            new CornerRadii(4), // 8px (+ 4 pour éviter de passer sur le bord)
            null
        )));

    }

    /**
     * Une case vide.
     */
    public PlateauCaseVue() {
        this("", Color.WHITE);
    }

    public String lettre() {
        return lettre;
    }

    public int points() {
        return points;
    }

    public void assigner(String lettre, int points) {
        this.lettre = lettre;
        contenuLabel.setText(lettre);

        this.points = points;
        pointsLabel.setText(String.valueOf(points));
    }

    public void vider() {
        lettre = null;
        contenuLabel.setText(placeholder);

        points = -1;
        pointsLabel.setText("");
    }

    public boolean estAssignee() {
        return lettre != null && points >= 0;
    }
}
