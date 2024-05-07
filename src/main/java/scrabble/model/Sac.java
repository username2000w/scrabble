package scrabble.model;

import scrabble.model.utils.exception.SacVideException;

import java.util.ArrayList;

/**
 * Lors de l'instanciation, le sac est rempli de tuiles de scrabble
 * qui sont ensuite mélangées.
 */
public class Sac {
  private ArrayList<LettreAlphabet> lettres;  

  private void faireJetonsPour(int nombreDeJetons, LettreAlphabet lettre) {
    for (int i = 0; i < nombreDeJetons; i++) {
      this.lettres.add(lettre);
    }
  }

  private void melanger() {
    for (int i = 0; i < this.lettres.size(); i++) {
      int index = (int) (Math.random() * this.lettres.size());
      LettreAlphabet temp = this.lettres.get(i);
      this.lettres.set(i, this.lettres.get(index));
      this.lettres.set(index, temp);
    }
  }

  public Sac() {
    this.lettres = new ArrayList<>();
    
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
    this.melanger();
  }

  /** Piocher la première tuile actuellement dans le sac. */
  public LettreAlphabet piocher () throws SacVideException {
    if (this.lettres.isEmpty()) {
      throw new SacVideException("Le sac est vide.");
    }

    return this.lettres.remove(0);
  }
  
  /** Ajouter une tuile à la fin du sac. */
  public void ajouter(LettreAlphabet tuile) {
	  lettres.add(tuile);
  }

  public int getNombreDeTuiles() {
    return this.lettres.size();
  }
}
