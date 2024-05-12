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
  JOKER(0);

  private int points;
  
  private LettreAlphabetFrancais(int points) {
    this.points = points;
  }
  
  public int getPoints() {
	return this.points;
  }
}
