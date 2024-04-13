package scrabble.model;

import java.util.ArrayList;

/**
 * Lors de l'instanciation, le sac est rempli de tuiles de scrabble
 * qui sont ensuite mélangées.
 */
public class Sac {
  private ArrayList<Tuile> tuiles;  

  private void faireJetonsPour(int nombreDeJetons, LettreAlphabet lettre) {
    for (int i = 0; i < nombreDeJetons; i++) {
      this.tuiles.add(new Tuile(lettre));
    }
  }

  private void mélanger() {
    for (int i = 0; i < this.tuiles.size(); i++) {
      int index = (int) (Math.random() * this.tuiles.size());
      Tuile temp = this.tuiles.get(i);
      this.tuiles.set(i, this.tuiles.get(index));
      this.tuiles.set(index, temp);
    }
  }

  public Sac() {
    this.tuiles = new ArrayList<>();
    
    // On ajout tous les jetons dans le sac.
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
    this.faireJetonsPour(2, LettreAlphabet.JOKER);
    this.faireJetonsPour(1, LettreAlphabet.J);
    this.faireJetonsPour(1, LettreAlphabet.Q);
    this.faireJetonsPour(1, LettreAlphabet.K);
    this.faireJetonsPour(1, LettreAlphabet.W);
    this.faireJetonsPour(1, LettreAlphabet.X);
    this.faireJetonsPour(1, LettreAlphabet.Y);
    this.faireJetonsPour(1, LettreAlphabet.Z);

    // On mélange le contenu du sac.
    this.mélanger();
  }

  public Tuile piocher () {
    if (this.tuiles.size() == 0) {
      return null;
    }

    // On retire et récupère la première tuile du sac.
    return this.tuiles.remove(0);
  }
  
  public void ajouter(Tuile tuile) {
	  tuiles.add(tuile);
  }

  public int getNombreDeTuiles() {
    return this.tuiles.size();
  }
}
