package scrabble.model;

import scrabble.model.utils.exception.SacVideException;

import java.util.ArrayList;

/**
 * Lors de l'instanciation, le sac est rempli de tuiles de scrabble
 * qui sont ensuite mélangées.
 */
public class Sac {
  private ArrayList<LettreAlphabetFrancais> lettres;  

  private void faireJetonsPour(int nombreDeTuile, LettreAlphabetFrancais lettre) {
    for (int i = 0; i < nombreDeTuile; i++) {
      this.lettres.add(lettre);
    }
  }

  private void melanger() {
    for (int i = 0; i < this.lettres.size(); i++) {
      int index = (int) (Math.random() * this.lettres.size());
      LettreAlphabetFrancais temp = this.lettres.get(i);
      this.lettres.set(i, this.lettres.get(index));
      this.lettres.set(index, temp);
    }
  }

  public Sac() {
    this.lettres = new ArrayList<>();
    
    // On ajout tous les jetons dans le sac.
    this.faireJetonsPour(15, LettreAlphabetFrancais.E);
    this.faireJetonsPour(9, LettreAlphabetFrancais.A);
    this.faireJetonsPour(8, LettreAlphabetFrancais.I);
    this.faireJetonsPour(6, LettreAlphabetFrancais.N);
    this.faireJetonsPour(6, LettreAlphabetFrancais.O);
    this.faireJetonsPour(6, LettreAlphabetFrancais.R);
    this.faireJetonsPour(6, LettreAlphabetFrancais.S);
    this.faireJetonsPour(6, LettreAlphabetFrancais.T);
    this.faireJetonsPour(6, LettreAlphabetFrancais.U);
    this.faireJetonsPour(5, LettreAlphabetFrancais.L);
    this.faireJetonsPour(3, LettreAlphabetFrancais.D);
    this.faireJetonsPour(2, LettreAlphabetFrancais.G);
    this.faireJetonsPour(3, LettreAlphabetFrancais.M);
    this.faireJetonsPour(2, LettreAlphabetFrancais.B);
    this.faireJetonsPour(2, LettreAlphabetFrancais.C);
    this.faireJetonsPour(2, LettreAlphabetFrancais.P);
    this.faireJetonsPour(2, LettreAlphabetFrancais.F);
    this.faireJetonsPour(2, LettreAlphabetFrancais.H);
    this.faireJetonsPour(2, LettreAlphabetFrancais.V);
    this.faireJetonsPour(2, LettreAlphabetFrancais.JOKER);
    this.faireJetonsPour(1, LettreAlphabetFrancais.J);
    this.faireJetonsPour(1, LettreAlphabetFrancais.Q);
    this.faireJetonsPour(1, LettreAlphabetFrancais.K);
    this.faireJetonsPour(1, LettreAlphabetFrancais.W);
    this.faireJetonsPour(1, LettreAlphabetFrancais.X);
    this.faireJetonsPour(1, LettreAlphabetFrancais.Y);
    this.faireJetonsPour(1, LettreAlphabetFrancais.Z);

    // On mélange le contenu du sac.
    this.melanger();
  }

  /** Piocher la première tuile actuellement dans le sac. */
  public LettreAlphabetFrancais piocher () throws SacVideException {
    if (this.lettres.isEmpty()) {
      throw new SacVideException("Le sac est vide.");
    }

    return this.lettres.remove(0);
  }
  
  /** Ajouter une tuile à la fin du sac. */
  public void ajouter(LettreAlphabetFrancais tuile) {
	  lettres.add(tuile);
  }

  public int getNombreDeTuiles() {
    return this.lettres.size();
  }
}
