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
        joueur.remplirChevalet(sac);
        Console.afficherPlateau(plateau);
        Console.message(joueur.getNom() + " a les tuiles suivantes :");
        Console.afficherChevalet(joueur.getChevalet());
        Console.message("");

        Console.message("Que voulez-vous faire ?");
        Console.message(" - 1. Jouer un mot");
        Console.message(" - 2. Échanger une tuile");
        Console.message(" - 3. Quitter");

        int choix = Console.inputIntScanner();
        switch (choix) {
            case 1:
                Mot mot = jouerMot(plateau, joueur);
                Console.message("Vous avez marqué " + this.calculerScoreMot(mot) + " points.");
                break;
            case 2:
                Console.message("Quelle tuile voulez-vous échanger ? (Numéro de l'emplacement)");
                int input = Console.inputIntScanner();
                joueur.echanger(sac, input-1);
                break;
            case 3:
                return false;
            default:
                Console.message("Choix invalide.");
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
                scoreMot += plateau[ligneDebutMot][colonne].getTuile().getPoints();

                if (!plateau[ligneDebutMot - 1][colonne].estVide()) {
                    for (int ligne = ligneDebutMot; !plateau[ligne - 1][colonne].estVide(); ligne--) {
                        scoreMot += plateau[ligne - 1][colonne].getTuile().getPoints();
                    }
                }

                if (!plateau[ligneDebutMot + 1][colonne].estVide()) {
                    for (int ligne = ligneDebutMot; !plateau[ligne + 1][colonne].estVide(); ligne++) {
                        scoreMot += plateau[ligne + 1][colonne].getTuile().getPoints();
                    }
                }
            }
        } else {
            for (int ligne = ligneDebutMot; !plateau[ligne][colonneDebutMot].estVide(); ligne++) {
                scoreMot += plateau[ligne][colonneDebutMot].getTuile().getPoints();

                if (!plateau[ligne][colonneDebutMot - 1].estVide()) {
                    for (int colonne = colonneDebutMot; !plateau[ligne][colonne - 1].estVide(); colonne--) {
                        scoreMot += plateau[ligne][colonne - 1].getTuile().getPoints();
                    }
                }

                if (!plateau[ligne][colonneDebutMot + 1].estVide()) {
                    for (int colonne = colonneDebutMot; !plateau[ligne][colonne + 1].estVide(); colonne++) {
                        scoreMot += plateau[ligne][colonne + 1].getTuile().getPoints();
                    }
                }
            }
        }
        return scoreMot;
    }

    public void jouerLettre(Plateau plateau, Joueur joueur, int NombreLettrePosee, Mot mot) {
        int posColonne;
        int posLigne;
        int choixSensMot;

        if (NombreLettrePosee == 0) {

	        System.out.print("Où voulez-vous la placer ? ( ↔ Horizontalement ) : ");
	        String posColonneLettre = Console.inputStringScanner();
	        posColonne = Integer.parseInt(posColonneLettre);

	        System.out.print("Où voulez-vous la placer ? ( ↕ Verticalement ) : ");
	        String posLigneLettre = Console.inputStringScanner();
	        posLigne = Integer.parseInt(posLigneLettre);

	        mot.setCoordoneeDebut(new Coordonee(posLigne, posColonne));

	        Console.message("Dans quel sens voulez-vous que le mot continue ?");
	        Console.message("1. Horizontalement");
	        Console.message("2. Verticalment");
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

        Tuile tuile = joueur.getChevalet().getTuileAvecLettre(choixlettre);

        if (tuile.estJoker()) {
            System.out.print("[JOKER] Par quelle lettre voulez-vous remplacer le Joker ? : ");
            String choixlettrejoker = Console.inputStringScanner().toUpperCase();
            tuile.setLettreJoker(choixlettrejoker);
        }

        mot.ajouterLettre(tuile);
        joueur.retirerLettreDuChevalet(tuile);
    }



    public Mot jouerMot(Plateau plateau, Joueur joueur) {
        Chevalet chevalet = joueur.getChevalet();
        int NombreLettrePosee = 0;
        Mot mot = new Mot();
        Coordonee coordonnees = null;
        Direction directionMot = null;

        Console.message("Vous allez jouer un mot.");
        int choix = 0;
        while (choix != 2) {
            jouerLettre(plateau, joueur, NombreLettrePosee, mot);
            if (NombreLettrePosee == 0) {
                coordonnees = mot.getCoordoneeDebut();
            }
            directionMot = mot.getDirection();

            Console.message("1. Continuez à placer une lettre ");
            Console.message("2. finir le mot ");

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
                        placerMotSurPlateau(mot, plateau);
                        return mot;
                    }
	                for (int ligne = ligneDebutMot; ligne < ligneDebutMot + mot.nombreDeLettre(); ligne++) {
	                    if (!plateau.getPlateau()[ligne][colonneDebutMot - 1].estVide() || !plateau.getPlateau()[ligne][colonneDebutMot + 1].estVide()) {
	                        placerMotSurPlateau(mot, plateau);
	                        return mot;
	                    }
	                }
	            } else {
                    int colonneDebutMot = coordonnees.getColonne();
                    int ligneDebutDebut = coordonnees.getLigne();
                    if (!plateau.getPlateau()[ligneDebutDebut - 1][colonneDebutMot].estVide() || !plateau.getPlateau()[ligneDebutDebut + 1][colonneDebutMot].estVide() || !plateau.getPlateau()[ligneDebutDebut][colonneDebutMot - 1].estVide()) {
                        placerMotSurPlateau(mot, plateau);
                        return mot;
                    }
                    for (int colonne = colonneDebutMot; colonne < colonneDebutMot + mot.nombreDeLettre(); colonne++) {
                        if (!plateau.getPlateau()[ligneDebutDebut - 1][colonne].estVide() || !plateau.getPlateau()[ligneDebutDebut + 1][colonne].estVide()) {
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
	                    placerMotSurPlateau(mot, plateau);
	                    return mot;
	        	}
	        }
        } else {
        	placerMotSurPlateau(mot, plateau);
        	if (plateau.getPlateau()[7][7].estVide()) {
                Console.message("Le premier mot doit commencer sur l'étoile");
                plateau.supprimerToutesTuiles();
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

        for (Tuile tuile : mot.getMot()) {

            if (directionMot.equals(Direction.HORIZONTAL)) {
                try {
                    if (plateau.getPlateau()[ligne][colonne].estVide()) {
                        plateau.placerTuile(tuile, new Coordonee(ligne, colonne));
                    } else {
                        colonne++;
                        Console.message("Une lettre est déjà présente à cet endroit, on décale la lettre.");
                        plateau.placerTuile(tuile, new Coordonee(ligne, colonne));
                    }
                } catch (HorsPlateauException e) {
                    throw new RuntimeException(e);
                }
                colonne++;
            } else {
                try {
                    if (plateau.getPlateau()[ligne][colonne].estVide()) {
                        plateau.placerTuile(tuile, new Coordonee(ligne, colonne));
                    } else {
                        ligne++;
                        Console.message("Une lettre est déjà présente à cet endroit, on décale la lettre.");
                        plateau.placerTuile(tuile, new Coordonee(ligne, colonne));
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
