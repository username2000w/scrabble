package scrabble.model;
import java.util.Scanner;

import scrabble.model.utils.exception.HorsPlateauException;

public class Joueur {
	private Chevalet chevalet;
	private final String nom;
	private Plateau plateau;
	
	public Joueur(Chevalet chevalet, String nom, Plateau plateau) {
		this.chevalet = chevalet;
		this.nom = nom;
		this.plateau = plateau;
		chevalet.remplirChevalet();
	}

	public String getNom() {
		return nom;
	}
	
	public void echanger(int tuileIndex) {
		this.chevalet.echanger(tuileIndex);
		this.chevalet.piocher();
	}

  public void afficherChevalet() {
    this.chevalet.afficherTuiles();
  }
  
  int tour = 0;
  public void jouerlettre() {
	System.out.print("Quel lettre voulez-vous jouer ? : ");
	Scanner scanner = new Scanner(System.in);
	String choixlettre = scanner.nextLine();
	
	
	while (chevalet.getTuileAvecLettre(choixlettre) == null) {
		System.out.print("Quel lettre voulez-vous jouer ? : ");
		Scanner scanner4 = new Scanner(System.in);
		choixlettre = scanner4.nextLine();
	}
	
	System.out.print("Où voulez-vous la placer ? ( ↔ Horizontalement ) : ");
	Scanner scanner2 = new Scanner(System.in);
	String pos_x_lettre = scanner2.nextLine();
	int posx = Integer.parseInt(pos_x_lettre);
	
	System.out.print("Où voulez-vous la placer ? ( ↕ Verticalement ) : ");
	Scanner scanner3 = new Scanner(System.in);
	String pos_y_lettre = scanner3.nextLine();
	int posy = Integer.parseInt(pos_y_lettre);
	
	
	
	LettreAlphabetFrancais lettre = chevalet.getTuileAvecLettre(choixlettre);
	
	
  
  if (!plateau.getPlateau()[posy][posx].estVide()) {
      System.out.println("Impossible de placer une lettre ici, une lettre est déjà placée.");
      return;
  }
  	
	  try {
			plateau.placerlettre(lettre, Integer.parseInt(pos_x_lettre), Integer.parseInt(pos_y_lettre));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HorsPlateauException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
  }
  
	 
  
  public void jouerMot() {
		System.out.println("Quel Mot voulez-vous jouer ? : ");
		Scanner scanner = new Scanner(System.in);
        int choix = 0;
        
		while (choix != 2) {
			jouerlettre();
			System.out.println("1. Continuez à placer une lettre ");
			System.out.println("2. finir le tour ");
			
			choix = scanner.nextInt();
			if (choix == 1) {
				afficherChevalet();
				tour ++;
				
			}
		}
			
  }
}
