package scrabble.model;

import scrabble.model.utils.Coordonee;
import scrabble.model.utils.Direction;

import java.util.ArrayList;

public class Mot {
    private Coordonee coordoneeDebut;
    private Direction direction;
    private ArrayList<Tuile> mot = new ArrayList<>();

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

    public Coordonee getCoordoneeDebut() {
        return coordoneeDebut;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setCoordoneeDebut(Coordonee coordoneeDebut) {
        this.coordoneeDebut = coordoneeDebut;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public ArrayList<Tuile> getMot() {
        return mot;
    }
    
    public int nombreDeLettre() {
    	return mot.size();
    }
}
