package scrabble.controller;

import scrabble.gui.Console;
import scrabble.model.*;
import scrabble.model.utils.Coordonee;
import scrabble.model.utils.DictionaireFrancais;
import scrabble.model.utils.Direction;
import scrabble.model.utils.Score;
import scrabble.model.utils.exception.HorsPlateauException;
import scrabble.model.utils.exception.SacVideException;

public class MaitreDuJeu {
    private final Sac sac;
    private final Plateau plateau;
    private Joueur joueurActuelle;
    private Joueur joueurAprès;
    private Joueur joueurIntermediaire;
    private final DictionaireFrancais dictionaire;
    private int tour;

    public MaitreDuJeu() {
        this.sac = new Sac();
        this.plateau = new Plateau();
        this.joueurActuelle = new Joueur(new Chevalet(), "Joueur 1");
        System.out.println("Nom du joueur 1: " );
        joueurActuelle.changerNom();
        this.joueurAprès = new Joueur(new Chevalet(), "Joueur 2");
        System.out.println("Nom du joueur 2: " );
        joueurAprès.changerNom();
        try {
            this.joueurActuelle.remplirChevalet(sac);
        } catch (SacVideException e) {
            throw new RuntimeException(e);
        }
        this.dictionaire = new DictionaireFrancais();
    }

    public void changerTour() {
    	joueurIntermediaire = joueurActuelle;
        joueurActuelle = joueurAprès;
        joueurAprès = joueurIntermediaire;
    }

    public boolean jouerTour() {
        try {
            joueurActuelle.remplirChevalet(sac);
        } catch (SacVideException e) {
            throw new RuntimeException(e);
        }
        try {
            joueurAprès.remplirChevalet(sac);
        } catch (SacVideException e) {
            throw new RuntimeException(e);
        }
        Console.afficherPlateau(plateau);
        Console.message(joueurActuelle.nom() + " a les tuiles suivantes :");
        Console.afficherChevalet(joueurActuelle.chevalet());
        Console.message("");

        Console.message("Que voulez-vous faire ?");
        Console.message(" - 1. Jouer un mot");
        Console.message(" - 2. Échanger une tuile");
        Console.message(" - 3. Mélanger le chevalet");
        Console.message(" - 4. Passer son tour");
        Console.message(" - 5. Quitter");

        int choix = Console.inputIntScanner();
        switch (choix) {
            case 1:
                Mot mot = jouerMot(plateau, joueurActuelle);
                int score = Score.calculerScoreMot(mot, plateau);
                Console.message("Vous avez marqué " + score + " points.");
                joueurActuelle.ajouterScore(score);
                Console.message("Votre score est de " + joueurActuelle.score() + " points.");
                break;
            case 2:
                Console.message("Quelle tuile voulez-vous échanger ? (Numéro de l'emplacement)");
                int input = Console.inputIntScanner();
                try {
                    joueurActuelle.echanger(sac, input-1);
                } catch (SacVideException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 3:
            	joueurActuelle.chevalet().melangerChevalet();
            	break;
            case 4:
            	 Console.message("Votre score est de " + joueurActuelle.score() + " points.");
            	break;
            case 5:
                return false;
            default:
                Console.message("Choix invalide.");
        }
        changerTour();
        return true;
    }

    public void demanderLettre(Joueur joueur, int NombreLettrePosee, Mot mot) {
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
            tuile.remplacerLettre(choixlettrejoker);
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
            demanderLettre(joueur, NombreLettrePosee, mot);
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
        if (motValide(mot)) {
            for (Tuile tuile : mot.getMot()) {
                try {
                    if (plateau.plateau()[coordonnees.ligne()][coordonnees.colonne()].estVide()) {
                        plateau.placerTuile(tuile, coordonnees);
                    } else {
                        Console.message("Une lettre est déjà présente à cet endroit, on décale la lettre.");
                        plateau.placerTuile(tuile, coordonnees.avancer(directionMot));
                    }
                } catch (HorsPlateauException e) {
                    throw new RuntimeException(e);
                }
                coordonnees = coordonnees.avancer(directionMot);
            }
        }
    }

    public boolean motValide(Mot mot) {
        Mot motNouveau = new Mot();

        for (Tuile tuile : mot.getMot()) {
            motNouveau.ajouterLettre(tuile);
        }
        for (Coordonee coordonee = mot.coordoneeDebut().reculer(mot.direction()); !plateau.casePlateau(coordonee).estVide(); coordonee = coordonee.reculer(mot.direction())) {
            motNouveau.getMot().add(0, plateau.casePlateau(coordonee).tuile());
        }

    	return dictionaire.contient(motNouveau.toString());
    }

    public Sac sac() {
        return sac;
    }

    public Plateau plateau() {
        return plateau;
    }

    public Joueur joueur() {
        return joueurActuelle;
    }
}
