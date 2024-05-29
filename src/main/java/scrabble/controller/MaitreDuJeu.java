package scrabble.controller;

import scrabble.gui.Console;
import scrabble.model.*;
import scrabble.model.utils.Coordonee;
import scrabble.model.utils.Direction;
import scrabble.model.utils.exception.HorsPlateauException;

public class MaitreDuJeu {
    private Sac sac;
    private Plateau plateau;
    private Joueur joueur;
    private int tour;

    public MaitreDuJeu() {
        this.sac = new Sac();
        this.plateau = new Plateau();
        this.joueur = new Joueur(new Chevalet(), "Joueur 1");
        this.joueur.remplirChevalet(sac);
        // v3 => Compter plusieurs joueurs
    }

    public boolean jouerTour() {
        Console.afficherPlateau(plateau);
        System.out.println(joueur.getNom() + " a les tuiles suivantes :");
        Console.afficherChevalet(joueur.getChevalet());
        System.out.println();

        System.out.println("Que voulez-vous faire ?");
        System.out.println(" - 1. Jouer un mot");
        System.out.println(" - 2. Échanger une tuile");
        System.out.println(" - 3. Quitter");

        int choix = Console.inputIntScanner();
        switch (choix) {
            case 1:
                Mot mot = jouerMot(plateau, joueur);
                System.out.println("Vous avez marqué " + this.calculerScoreMot(mot) + " points.");
                joueur.remplirChevalet(sac);
                break;
            case 2:
                System.out.println("Quelle tuile voulez-vous échanger ? (Numéro de l'emplacement)");
                int input = Console.inputIntScanner();
                joueur.echanger(sac, input-1);
                break;
            case 3:
                return false;
            default:
                System.out.println("Choix invalide.");
        }
        return true;
    }

    public int calculerScoreMot(Mot mot) {
        Coordonee coordoneeDebutMot = mot.getCoordoneeDebut();
        int ligneDebutMot = coordoneeDebutMot.getLigne();
        int colonneDebutMot = coordoneeDebutMot.getColonne();
        Direction directionMot = mot.getDirection();
        int scoreMot = 0;
        Case[][] plateau = this.plateau.getPlateau();

        if (directionMot.equals(Direction.HORIZONTAL)) {
            for (int colonne = colonneDebutMot; !plateau[ligneDebutMot][colonne].estVide(); colonne++) {
                scoreMot += plateau[ligneDebutMot][colonne].getLettre().getPoints();

                if (!plateau[ligneDebutMot - 1][colonne].estVide()) {
                    for (int ligne = ligneDebutMot; !plateau[ligne - 1][colonne].estVide(); ligne--) {
                        scoreMot += plateau[ligne - 1][colonne].getLettre().getPoints();
                    }
                }

                if (!plateau[ligneDebutMot + 1][colonne].estVide()) {
                    for (int ligne = ligneDebutMot; !plateau[ligne + 1][colonne].estVide(); ligne++) {
                        scoreMot += plateau[ligne + 1][colonne].getLettre().getPoints();
                    }
                }
            }
        } else {
            for (int ligne = ligneDebutMot; !plateau[ligne][colonneDebutMot].estVide(); ligne++) {
                scoreMot += plateau[ligne][colonneDebutMot].getLettre().getPoints();

                if (!plateau[ligne][colonneDebutMot - 1].estVide()) {
                    for (int colonne = colonneDebutMot; !plateau[ligne][colonne - 1].estVide(); colonne--) {
                        scoreMot += plateau[ligne][colonne - 1].getLettre().getPoints();
                    }
                }

                if (!plateau[ligne][colonneDebutMot + 1].estVide()) {
                    for (int colonne = colonneDebutMot; !plateau[ligne][colonne + 1].estVide(); colonne++) {
                        scoreMot += plateau[ligne][colonne + 1].getLettre().getPoints();
                    }
                }
            }
        }
        return scoreMot;
    }

    public void jouerLettre(Plateau plateau, Joueur joueur, int NombreLettrePosee, Mot mot) {
        boolean joker = false;
        String affichage = "";

        int posColonne = 0;
        int posLigne = 0;
        int choixSensMot = 0;

        if (NombreLettrePosee == 0) {

	        System.out.print("Où voulez-vous la placer ? ( ↔ Horizontalement ) : ");
	        String posColonneLettre = Console.inputStringScanner();
	        posColonne = Integer.parseInt(posColonneLettre);

	        System.out.print("Où voulez-vous la placer ? ( ↕ Verticalement ) : ");
	        String posLigneLettre = Console.inputStringScanner();
	        posLigne = Integer.parseInt(posLigneLettre);

	        mot.setCoordoneeDebut(new Coordonee(posLigne, posColonne));

	        System.out.println("Dans quel sens voulez-vous que le mot continue ?");
	        System.out.println("1. Horizontalement");
	        System.out.println("2. Verticalment");
	        String choixSens = Console.inputStringScanner();
	        choixSensMot = Integer.parseInt(choixSens);
	        switch (choixSensMot) {
	        	case 1:
	        		mot.setDirection(Direction.HORIZONTAL);
	        		break;
	        	case 2:
	        		mot.setDirection(Direction.VERTICAL);
	        		break;
	        }
        }

        System.out.print("Quel lettre voulez-vous jouer ? : ");
        String choixlettre = Console.inputStringScanner().toUpperCase();

        while (joueur.getChevalet().getTuileAvecLettre(choixlettre) == null) {
            System.out.print("Veuillez selectioner une lettre dans le chevalet : ");
            choixlettre = Console.inputStringScanner().toUpperCase();
        }

        if (choixlettre.equals("JOKER")) {
            System.out.print("[JOKER] Quel lettre voulez-vous jouer ? : ");
            joker = true;
            choixlettre = Console.inputStringScanner().toUpperCase();
            affichage = choixlettre;
        }

        LettreAlphabetFrancais lettre = joueur.getChevalet().getTuileAvecLettre(choixlettre);
        if (Boolean.TRUE.equals(joker)) {
            lettre = joueur.getChevalet().getTuileAvecLettre("JOKER");
        }
        mot.ajouterLettre(lettre);
        joueur.retirerLettreDuChevalet(lettre);
    }



    public Mot jouerMot(Plateau plateau, Joueur joueur) {
        Chevalet chevalet = joueur.getChevalet();
        int NombreLettrePosee = 0;
        Mot mot = new Mot();
        Coordonee coordonnees = null;
        Direction directionMot = null;

        System.out.println("Vous allez jouer un mot.");
        int choix = 0;
        while (choix != 2) {
            jouerLettre(plateau, joueur, NombreLettrePosee, mot);
            if (NombreLettrePosee == 0) {
                coordonnees = mot.getCoordoneeDebut();
            }
            directionMot = mot.getDirection();

            System.out.println("1. Continuez à placer une lettre ");
            System.out.println("2. finir le mot ");

            choix = Console.inputIntScanner();
            if (choix == 1) {
                Console.afficherChevalet(chevalet);
                NombreLettrePosee++;
            }
        }

        if (tour > 0) {
	        if (NombreLettrePosee > 1) {
	            if (directionMot.equals(Direction.HORIZONTAL)) {
	                int colonneDebutMot = coordonnees.getColonne();
	                int ligneDebutMot = coordonnees.getLigne();
	                if (!plateau.getPlateau()[ligneDebutMot][colonneDebutMot - 1].estVide() || !plateau.getPlateau()[ligneDebutMot][colonneDebutMot + 1].estVide() || !plateau.getPlateau()[ligneDebutMot - 1][colonneDebutMot].estVide()) {
                        System.out.println("Le mot est coorectemnt placer");
                        placerMotSurPlateau(mot, plateau);
                        return mot;
                    }
	                for (int ligne = ligneDebutMot; ligne < ligneDebutMot + mot.nombreDeLettre(); ligne++) {
	                    if (!plateau.getPlateau()[ligne][colonneDebutMot - 1].estVide() || !plateau.getPlateau()[ligne][colonneDebutMot + 1].estVide()) {
	                        System.out.println("Le mot est coorectemnt placer");
	                        placerMotSurPlateau(mot, plateau);
	                        return mot;
	                    }
	                }
	            } else {
                    int colonneDebutMot = coordonnees.getColonne();
                    int ligneDebutDebut = coordonnees.getLigne();
                    if (!plateau.getPlateau()[ligneDebutDebut - 1][colonneDebutMot].estVide() || !plateau.getPlateau()[ligneDebutDebut + 1][colonneDebutMot].estVide() || !plateau.getPlateau()[ligneDebutDebut][colonneDebutMot - 1].estVide()) {
                        System.out.println("Le mot est coorectemnt placer");
                        placerMotSurPlateau(mot, plateau);
                        return mot;
                    }
                    for (int colonne = colonneDebutMot; colonne < colonneDebutMot + mot.nombreDeLettre(); colonne++) {
                        if (!plateau.getPlateau()[ligneDebutDebut - 1][colonne].estVide() || !plateau.getPlateau()[ligneDebutDebut + 1][colonne].estVide()) {
                            System.out.println("Le mot est coorectemnt placer");
                            placerMotSurPlateau(mot, plateau);
                            return mot;
                        }
                    }
	            }
	        } else {
	        	int colonneDebutMot = coordonnees.getColonne();
	            int ligneDebutDebut = coordonnees.getLigne();
	        	if (!plateau.getPlateau()[ligneDebutDebut][colonneDebutMot - 1].estVide() || !plateau.getPlateau()[ligneDebutDebut][colonneDebutMot+ 1].estVide()
	        		|| !plateau.getPlateau()[ligneDebutDebut - 1][colonneDebutMot].estVide() || !plateau.getPlateau()[ligneDebutDebut + 1][colonneDebutMot].estVide()) {
	        			System.out.println("La lettre est coorectemnt placer");
	                    placerMotSurPlateau(mot, plateau);
	                    return mot;
	        	}
	        }
        } else {
        	System.out.println("coucocu");
        	placerMotSurPlateau(mot, plateau);
        	if (plateau.getPlateau()[7][7].estVide()) {
                System.out.println("Le premier mot doit commencer sur l'étoile");
                plateau.supprimerToutesLettres();
	        }
	    }
        tour ++;
        return mot;
    }

    public void placerMotSurPlateau(Mot mot, Plateau plateau) {
        Direction directionMot = mot.getDirection();

        Coordonee coordonnees = mot.getCoordoneeDebut();
        int ligne = coordonnees.getLigne();
        int colonne = coordonnees.getColonne();

        for (LettreAlphabetFrancais lettre : mot.getMot()) {

            if (directionMot.equals(Direction.HORIZONTAL)) {
                try {
                    if (plateau.getPlateau()[ligne][colonne].estVide()) {
                        plateau.placerlettre(lettre, new Coordonee(ligne, colonne));
                    } else {
                        colonne++;
                        System.out.println("Une lettre est déjà présente à cet endroit, on décale la lettre.");
                        plateau.placerlettre(lettre, new Coordonee(ligne, colonne));
                    }
                } catch (HorsPlateauException e) {
                    throw new RuntimeException(e);
                }
                colonne++;
            } else {
                try {
                    if (plateau.getPlateau()[ligne][colonne].estVide()) {
                        plateau.placerlettre(lettre, new Coordonee(ligne, colonne));
                    } else {
                        ligne++;
                        System.out.println("Une lettre est déjà présente à cet endroit, on décale la lettre.");
                        plateau.placerlettre(lettre, new Coordonee(ligne, colonne));
                    }
                } catch (HorsPlateauException e) {
                    throw new RuntimeException(e);
                }
                ligne++;
            }
        }
    }

    public Sac getSac() {
        return sac;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public Joueur getJoueur() {
        return joueur;
    }
}
