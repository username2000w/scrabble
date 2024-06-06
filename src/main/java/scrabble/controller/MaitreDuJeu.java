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
        Console.message(joueur.nom() + " a les tuiles suivantes :");
        Console.afficherChevalet(joueur.chevalet());
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
        Coordonee coordoneeDebutMot = mot.coordoneeDebut();
        Direction directionMot = mot.direction();
        Case[][] plateau = this.plateau.plateau();

        int scoreMot = 0;

        // Calcul du score du mot de base avec multiplicateurs
        int multiplicateurMot = 1;

        for (Coordonee coordonee = coordoneeDebutMot; !plateau[coordonee.ligne()][coordonee.colonne()].estVide(); coordonee = coordonee.avancer(directionMot)) {
            int multiplicateurLettre = 1;

            if (plateau[coordonee.ligne()][coordonee.colonne()].bonus() != null) {
                multiplicateurLettre = plateau[coordonee.ligne()][coordonee.colonne()].bonus().multiplicateurLettre();
                multiplicateurMot = plateau[coordonee.ligne()][coordonee.colonne()].bonus().multiplicateurMot();
            }

            scoreMot += plateau[coordonee.ligne()][coordonee.colonne()].tuile().points() * multiplicateurLettre;
        }
        scoreMot *= multiplicateurMot;

        // Calcul des mots formés par le mot de base avec multiplicateurs
        for (Coordonee coordonee = coordoneeDebutMot; !plateau[coordonee.ligne()][coordonee.colonne()].estVide(); coordonee = coordonee.avancer(directionMot)) {
            Coordonee coordoneeCoteDroit = coordonee.avancer(directionMot.oppose());
            if (!this.plateau.casePlateau(coordoneeCoteDroit).estVide()) {
                for (Coordonee coordoneeMotCote = coordoneeCoteDroit; !this.plateau.casePlateau(coordoneeMotCote).estVide(); coordoneeMotCote = coordoneeMotCote.avancer(directionMot.oppose())) {
                    scoreMot += this.plateau.casePlateau(coordoneeMotCote).tuile().points();
                }
            }

            Coordonee coordoneeCoteGauche = coordonee.reculer(directionMot.oppose());
            if (!this.plateau.casePlateau(coordoneeCoteGauche).estVide()) {
                for (Coordonee coordoneeMotCote = coordoneeCoteGauche; !this.plateau.casePlateau(coordoneeMotCote).estVide(); coordoneeMotCote = coordoneeMotCote.reculer(directionMot.oppose())) {
                    scoreMot += this.plateau.casePlateau(coordoneeMotCote).tuile().points();
                }
            }
        }

        int scrabble = mot.nombreDeLettre() == Chevalet.TAILLE ? 50 : 0;
        return scoreMot + scrabble;
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

	        mot.changerCoordoneeDebut(new Coordonee(posLigne, posColonne));

	        Console.message("Dans quel sens voulez-vous que le mot continue ?");
	        Console.message("1. Horizontalement");
	        Console.message("2. Verticalment");
	        String choixSens = Console.inputStringScanner();
	        choixSensMot = Integer.parseInt(choixSens);
	        switch (choixSensMot) {
	        	case 1:
	        		mot.changerDirection(Direction.HORIZONTAL);
	        		break;
	        	case 2:
	        		mot.changerDirection(Direction.VERTICAL);
	        		break;
	        }
        }

        System.out.print("Quel lettre voulez-vous jouer ? : ");
        String choixlettre = Console.inputStringScanner().toUpperCase();


        while (joueur.chevalet().tuileAvecLettre(choixlettre) == null) {
            System.out.print("Veuillez selectioner une lettre dans le chevalet : ");
            choixlettre = Console.inputStringScanner().toUpperCase();
        }

        Tuile tuile = joueur.chevalet().tuileAvecLettre(choixlettre);

        if (tuile.estJoker()) {
            System.out.print("[JOKER] Par quelle lettre voulez-vous remplacer le Joker ? : ");
            String choixlettrejoker = Console.inputStringScanner().toUpperCase();
            tuile.changerLettre(LettreAlphabetFrancais.valueOf(choixlettrejoker));
        }

        mot.ajouterLettre(tuile);
        joueur.retirerLettreDuChevalet(tuile);
    }



    public Mot jouerMot(Plateau plateau, Joueur joueur) {
        Chevalet chevalet = joueur.chevalet();
        int NombreLettrePosee = 0;
        Mot mot = new Mot();
        Coordonee coordonnees = null;
        Direction directionMot = null;

        Console.message("Vous allez jouer un mot.");
        int choix = 0;
        while (choix != 2) {
            jouerLettre(plateau, joueur, NombreLettrePosee, mot);
            if (NombreLettrePosee == 0) {
                coordonnees = mot.coordoneeDebut();
            }
            directionMot = mot.direction();

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
	                int colonneDebutMot = coordonnees.colonne();
	                int ligneDebutMot = coordonnees.ligne();
	                if (!plateau.plateau()[ligneDebutMot][colonneDebutMot - 1].estVide() || !plateau.plateau()[ligneDebutMot][colonneDebutMot + 1].estVide() || !plateau.plateau()[ligneDebutMot - 1][colonneDebutMot].estVide()) {
                        placerMotSurPlateau(mot, plateau);
                        return mot;
                    }
	                for (int ligne = ligneDebutMot; ligne < ligneDebutMot + mot.nombreDeLettre(); ligne++) {
	                    if (!plateau.plateau()[ligne][colonneDebutMot - 1].estVide() || !plateau.plateau()[ligne][colonneDebutMot + 1].estVide()) {
	                        placerMotSurPlateau(mot, plateau);
	                        return mot;
	                    }
	                }
	            } else {
                    int colonneDebutMot = coordonnees.colonne();
                    int ligneDebutDebut = coordonnees.ligne();
                    if (!plateau.plateau()[ligneDebutDebut - 1][colonneDebutMot].estVide() || !plateau.plateau()[ligneDebutDebut + 1][colonneDebutMot].estVide() || !plateau.plateau()[ligneDebutDebut][colonneDebutMot - 1].estVide()) {
                        placerMotSurPlateau(mot, plateau);
                        return mot;
                    }
                    for (int colonne = colonneDebutMot; colonne < colonneDebutMot + mot.nombreDeLettre(); colonne++) {
                        if (!plateau.plateau()[ligneDebutDebut - 1][colonne].estVide() || !plateau.plateau()[ligneDebutDebut + 1][colonne].estVide()) {
                            placerMotSurPlateau(mot, plateau);
                            return mot;
                        }
                    }
	            }
	        } else {
	        	int colonneDebutMot = coordonnees.colonne();
	            int ligneDebutDebut = coordonnees.ligne();
	        	if (!plateau.plateau()[ligneDebutDebut][colonneDebutMot - 1].estVide() || !plateau.plateau()[ligneDebutDebut][colonneDebutMot+ 1].estVide()
	        		|| !plateau.plateau()[ligneDebutDebut - 1][colonneDebutMot].estVide() || !plateau.plateau()[ligneDebutDebut + 1][colonneDebutMot].estVide()) {
	                    placerMotSurPlateau(mot, plateau);
	                    return mot;
	        	}
	        }
        } else {
        	placerMotSurPlateau(mot, plateau);
        	if (plateau.plateau()[7][7].estVide()) {
                Console.message("Le premier mot doit commencer sur l'étoile");
                plateau.supprimerToutesTuiles();
	        }
	    }
        tour ++;
        return mot;
    }

    public void placerMotSurPlateau(Mot mot, Plateau plateau) {
        Direction directionMot = mot.direction();

        Coordonee coordonnees = mot.coordoneeDebut();
        int ligne = coordonnees.ligne();
        int colonne = coordonnees.colonne();

        for (Tuile tuile : mot.getMot()) {

            if (directionMot.equals(Direction.HORIZONTAL)) {
                try {
                    if (plateau.plateau()[ligne][colonne].estVide()) {
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
                    if (plateau.plateau()[ligne][colonne].estVide()) {
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

    public Sac sac() {
        return sac;
    }

    public Plateau plateau() {
        return plateau;
    }

    public Joueur joueur() {
        return joueur;
    }
}
