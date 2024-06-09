package scrabble.model;

import scrabble.model.utils.Coordonee;
import scrabble.model.utils.DictionaireFrancais;
import scrabble.model.utils.Direction;

import java.util.ArrayList;

public class Mot {
    private Coordonee coordoneeDebut;
    private Direction direction;
    private final ArrayList<Tuile> mot = new ArrayList<>();

    public Mot(Coordonee coordoneeDebut, Direction direction) {
        this.coordoneeDebut = coordoneeDebut;
        this.direction = direction;
    }

    public Mot() {
        this(null, null);
    }

    public void ajouterLettre(Tuile tuile) {
        mot.add(tuile);
    }

    public Coordonee coordoneeDebut() {
        return coordoneeDebut;
    }

    public Direction direction() {
        return direction;
    }

    public void changerCoordoneeDebut(Coordonee coordoneeDebut) {
        this.coordoneeDebut = coordoneeDebut;
    }

    public void changerDirection(Direction direction) {
        this.direction = direction;
    }

    public ArrayList<Tuile> getMot() {
        return mot;
    }
    
    public int nombreDeLettre() {
    	return mot.size();
    }

    @Override
    public String toString() {
        StringBuilder mot = new StringBuilder();
        for (Tuile tuile : this.mot) {
            mot.append(tuile.lettre().toLowerCase());
        }

        return String.valueOf(mot);
    }
}
