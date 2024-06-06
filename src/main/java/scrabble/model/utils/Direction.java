package scrabble.model.utils;

public enum Direction {
    HORIZONTAL,
    VERTICAL;

    public Direction oppose() {
        return switch (this) {
            case HORIZONTAL -> VERTICAL;
            case VERTICAL -> HORIZONTAL;
        };
    }
}
