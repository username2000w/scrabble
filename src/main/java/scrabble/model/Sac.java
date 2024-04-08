package scrabble.model;

public class Sac {
  private Tuile[] tuiles;  

  private void faireJetonsPour(int nombreDeJetons, LettreAlphabet lettre) {
    for (int i = 0; i < nombreDeJetons; i++) {
      this.tuiles[i] = new Tuile(lettre);
    }
  }

  public Sac () {
    this.tuiles = new Tuile[102];
    
    this.faireJetonsPour(15, LettreAlphabet.E);
    this.faireJetonsPour(9, LettreAlphabet.A);
    this.faireJetonsPour(8, LettreAlphabet.I);
    this.faireJetonsPour(6, LettreAlphabet.N);
    this.faireJetonsPour(6, LettreAlphabet.O);
    this.faireJetonsPour(6, LettreAlphabet.R);
    this.faireJetonsPour(6, LettreAlphabet.S);
    this.faireJetonsPour(6, LettreAlphabet.T);
    this.faireJetonsPour(6, LettreAlphabet.U);
    this.faireJetonsPour(5, LettreAlphabet.L);
    this.faireJetonsPour(3, LettreAlphabet.D);
    this.faireJetonsPour(2, LettreAlphabet.G);
    this.faireJetonsPour(3, LettreAlphabet.M);
    this.faireJetonsPour(2, LettreAlphabet.B);
    this.faireJetonsPour(2, LettreAlphabet.C);
    this.faireJetonsPour(2, LettreAlphabet.P);
    this.faireJetonsPour(2, LettreAlphabet.F);
    this.faireJetonsPour(2, LettreAlphabet.H);
    this.faireJetonsPour(2, LettreAlphabet.V);
    this.faireJetonsPour(1, LettreAlphabet.J);
    this.faireJetonsPour(1, LettreAlphabet.Q);
    this.faireJetonsPour(1, LettreAlphabet.K);
    this.faireJetonsPour(1, LettreAlphabet.W);
    this.faireJetonsPour(1, LettreAlphabet.X);
    this.faireJetonsPour(1, LettreAlphabet.Y);
    this.faireJetonsPour(1, LettreAlphabet.Z);
  }
}
