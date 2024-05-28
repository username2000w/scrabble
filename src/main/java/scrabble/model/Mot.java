package scrabble.model;

import scrabble.model.utils.Coordonee;
import scrabble.model.utils.Direction;

public class Mot {
    private Coordonee coordoneeDebut;
    private Direction direction;

    public Mot(Coordonee coordoneeDebut, Direction direction) {
        this.coordoneeDebut = coordoneeDebut;
        this.direction = direction;
    }

    public Mot() {
        this(null, null);
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
}
