package scrabble.model;

public class Plateau {
	private Case[][] plateau = new Case[15][15]; 
	private String lettreDebut;

	public Plateau() {
		for (int i = 0; i < 15; i++) {
      for (int j = 0; j < 15; j++) {
        plateau[i][j] = new Case();
      }
		}
	}

  public void afficher() {

	// Affichage première ligne
	for (int i = -1; i < 15; i++) {
		if (i == -1 ) {
			System.out.print("X  ");
		}

		else if (i >= 10) {
			System.out.print(i + " ");
		}

	  	else {
	  		System.out.print(i + "  ");
	  	}
	}

	// Saut de ligne avant l'affichage
	System.out.println();

    for (int i = 0; i < 15; i++) {
      for (int j = 0; j < 15; j++) {
        // On a une étoile sur la case du milieu.
        if (j == 7 && i == 7) {
          System.out.print("★  ");
          continue;
        }

        LettreAlphabetFrancais lettre = plateau[i][j].getLettre();

        // Affichage des coordonées colonnes
        if (j==0) {
        	if (lettre == null) { // si la case est vide, on affiche un carré vide.
                lettreDebut = "□  ";
        	}

    		if (i >= 10) {
        		System.out.print(i + " " + lettreDebut);
        	}
        	else {
        	System.out.print(i + "  " + lettreDebut);
        	}
    	}


        else if (lettre == null) { // si la case est vide, on affiche un carré vide.
          System.out.print("□  ");
        }

        else {
          System.out.print(lettre + "  ");
        }
      }

      // Retour à la ligne.
      System.out.println();
    }
  }
}
