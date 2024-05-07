package scrabble.model;

import scrabble.model.utils.exception.HorsPlateauException;

public class Plateau {
	private Case[][] plateau = new Case[15][15]; 
	
	public Plateau() {
		for (int i = 0; i < 15; i++) {
      for (int j = 0; j < 15; j++) {
        plateau[i][j] = new Case();
      }
		}
	}

  public void afficher() {
    for (int i = 0; i < 15; i++) {
      for (int j = 0; j < 15; j++) {
        // On a une étoile sur la case du milieu.
        if (j == 7 && i == 7) {
          System.out.print("★ ");
          continue;
        }

        LettreAlphabet lettre = plateau[i][j].getLettre();

        if (lettre == null) { // si la case est vide, on affiche un carré vide.
          System.out.print("□ ");
        }
        else {
          System.out.print(lettre + " ");
        }
      }

      // Retour à la ligne.
      System.out.println();
    }
  }
  
  public void placerlettre(LettreAlphabet lettre,int x, int y) throws HorsPlateauException {
	  this.plateau[y][x].setLettre(lettre);
  }
}
