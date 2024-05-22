package scrabble.model;

public enum LettreAlphabetFrancais {
    A(1),
    B(3),
    C(3),
    D(2),
    E(1),
    F(4),
    G(2),
    H(4),
    I(1),
    J(8),
    K(10),
    L(1),
    M(2),
    N(1),
    O(1),
    P(3),
    Q(8),
    R(1),
    S(1),
    T(1),
    U(1),
    V(4),
    W(10),
    X(10),
    Y(10),
    Z(10),
    JOKER(0, "", "");

    private final int points;
    private String affichageJoker1;
    private String affichageJoker2;

    LettreAlphabetFrancais(int points) {
        this.points = points;
    }

    LettreAlphabetFrancais(int points, String joker1, String joker2) {
        this.points = points;
        this.affichageJoker1 = joker1;
        this.affichageJoker2 = joker2;
    }

    public int getPoints() {
        return this.points;
    }

    public void setJoker1(String affichage) {
        this.affichageJoker1 = affichage;
    }

    public void setJoker2(String affichage) {
        this.affichageJoker2 = affichage;
    }

    public String afficherLettre(int compt) {
        if (this == LettreAlphabetFrancais.JOKER && compt == 0) {
            return this.affichageJoker1;
        } else if (this == LettreAlphabetFrancais.JOKER && compt == 1) {
            return this.affichageJoker2;
        } else {
            return this.name();
        }
    }
}
