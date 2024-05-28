package scrabble.controller;

import scrabble.gui.Console;
import scrabble.model.*;
import scrabble.model.utils.Coordonee;
import scrabble.model.utils.Direction;

public class MaitreDuJeu {
    private Sac sac;
    private Plateau plateau;
    private Joueur joueur;

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
                Coordonee coordonnees = mot.getCoordoneeDebut();
                System.out.println("Vous avez marqué " + this.calculerScoreMot(coordonnees) + " points.");
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

    public int calculerScoreMot(Coordonee coordoneeDebutMot) {
        int ligneDebutMot = coordoneeDebutMot.getLigne();
        int colonneDebutMot = coordoneeDebutMot.getColonne();
        int scoreMot = 0;
        Case[][] plateau = this.plateau.getPlateau();

        // score de la case initiale
        scoreMot += plateau[ligneDebutMot][colonneDebutMot].getLettre().getPoints();

        for (int ligne = ligneDebutMot; !plateau[ligne + 1][colonneDebutMot].estVide(); ligne++) {
            scoreMot += plateau[ligneDebutMot + 1][colonneDebutMot].getLettre().getPoints();

            if (!plateau[ligne + 1][colonneDebutMot - 1].estVide()) {
                for (int colonne = colonneDebutMot; !plateau[ligne + 1][colonne - 1].estVide(); colonne--) {
                    scoreMot += plateau[ligne + 1][colonne - 1].getLettre().getPoints();
                }
            }

            if (!plateau[ligne + 1][colonneDebutMot + 1].estVide()) {
                for (int colonne = colonneDebutMot; !plateau[ligne + 1][colonne + 1].estVide(); colonne++) {
                    scoreMot += plateau[ligne + 1][colonne + 1].getLettre().getPoints();
                }
            }
        }

        for (int colonne = colonneDebutMot; !plateau[ligneDebutMot][colonne + 1].estVide(); colonne++) {
            scoreMot += plateau[ligneDebutMot][colonneDebutMot + 1].getLettre().getPoints();

            if (!plateau[ligneDebutMot - 1][colonne + 1].estVide()) {
                for (int ligne = ligneDebutMot; !plateau[ligne - 1][colonne + 1].estVide(); ligne--) {
                    scoreMot += plateau[ligne - 1][colonne + 1].getLettre().getPoints();
                }
            }

            if (!plateau[ligneDebutMot + 1][colonne + 1].estVide()) {
                for (int ligne = ligneDebutMot; !plateau[ligne + 1][colonne + 1].estVide(); ligne++) {
                    scoreMot += plateau[ligne + 1][colonne + 1].getLettre().getPoints();
                }
            }
        }

        return scoreMot;
    }

    public void jouerLettre(Plateau plateau, Joueur joueur, int NombreLettrePosee, Mot mot) {
        boolean joker = false;
        String affichage = "";

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

        System.out.print("Où voulez-vous la placer ? ( ↔ Horizontalement ) : ");
        String posColonneLettre = Console.inputStringScanner();
        int posColonne = Integer.parseInt(posColonneLettre);

        System.out.print("Où voulez-vous la placer ? ( ↕ Verticalement ) : ");
        String posLigneLettre = Console.inputStringScanner();
        int posLigne = Integer.parseInt(posLigneLettre);

        LettreAlphabetFrancais lettre = joueur.getChevalet().getTuileAvecLettre(choixlettre);
        if (Boolean.TRUE.equals(joker)) {
            lettre = joueur.getChevalet().getTuileAvecLettre("JOKER");
        }


        if (!plateau.getPlateau()[posLigne][posColonne].estVide()) {
            System.out.println("Impossible de placer une lettre ici, une lettre est déjà placée.");
        } else {
            if (NombreLettrePosee != 0) {
                if (!plateau.getPlateau()[posLigne][posColonne - 1].estVide()) {
                    if (NombreLettrePosee == 1) {
                        joueur.placerEtRetirerLettre(lettre, plateau, new Coordonee(posLigne, posColonne), affichage);
                        mot.setDirection(Direction.HORIZONTAL);
                    } else {
                        if (!plateau.getPlateau()[posLigne - 1][posColonne - 1].estVide() || !plateau.getPlateau()[posLigne + 1][posColonne - 1].estVide()) {
                            System.out.println("Le mot est vertical on ne peut pas le continué horizontalement");
                        } else {
                            joueur.placerEtRetirerLettre(lettre, plateau, new Coordonee(posLigne, posColonne), affichage);
                        }
                    }
                } else {
                    if (!plateau.getPlateau()[posLigne - 1][posColonne].estVide()) {
                        if (NombreLettrePosee == 1) {
                            joueur.placerEtRetirerLettre(lettre, plateau, new Coordonee(posLigne, posColonne), affichage);
                            mot.setDirection(Direction.VERTICAL);
                        } else {
                            if (!plateau.getPlateau()[posLigne - 1][posColonne - 1].estVide() || !plateau.getPlateau()[posLigne - 1][posColonne + 1].estVide()) {
                                System.out.println("Le mot est horizontal on ne peut pas le continué verticalment");
                            } else {
                                joueur.placerEtRetirerLettre(lettre, plateau, new Coordonee(posLigne, posColonne), affichage);
                            }
                        }
                    } else {
                        System.out.println("La lettre doit suivre la première lettre ");
                    }
                }
            } else {
                joueur.placerEtRetirerLettre(lettre, plateau, new Coordonee(posLigne, posColonne), affichage);
                Coordonee coordonnees = new Coordonee(posLigne, posColonne);
                mot.setCoordoneeDebut(coordonnees);
            }
        }
    }

    public Mot jouerMot(Plateau plateau, Joueur joueur) {
        Chevalet chevalet = joueur.getChevalet();
        int NombreLettrePosee = 0;
        Mot mot = new Mot();
        Coordonee coordonnees = null;
        Direction directionMot = null;

        System.out.println("Quel Mot voulez-vous jouer ? : ");
        int choix = 0;
        while (choix != 2) {
            if (NombreLettrePosee == 0) {
                jouerLettre(plateau, joueur, NombreLettrePosee, mot);
                coordonnees = mot.getCoordoneeDebut();
                directionMot = mot.getDirection();
            } else {
                jouerLettre(plateau, joueur, NombreLettrePosee, mot);
                directionMot = mot.getDirection();
            }
            System.out.println("1. Continuez à placer une lettre ");
            System.out.println("2. finir le tour ");

            choix = Console.inputIntScanner();
            if (choix == 1) {
                Console.afficherChevalet(chevalet);
                NombreLettrePosee++;
            }
        }

        if (plateau.getPlateau()[7][7].estVide()) {
            System.out.println("Le premier mot doit commencer sur l'étoile");
            plateau.supprimerToutesLettres();
        }

        if (NombreLettrePosee != 0 ) {
            if (directionMot.equals(Direction.HORIZONTAL)) {
                int colonneDebutMot = coordonnees.getColonne();
                int ligneDebutMot = coordonnees.getLigne();
                for (int y = ligneDebutMot; !plateau.getPlateau()[y + 1][colonneDebutMot].estVide(); y++) {
                    if (!plateau.getPlateau()[y][colonneDebutMot - 1].estVide() || !plateau.getPlateau()[y][colonneDebutMot + 1].estVide()) {
                        System.out.println("Le mot est coorectemnt placer");
                    } else {
                        if (plateau.getPlateau()[y-1][colonneDebutMot].estVide() || plateau.getPlateau()[y+1][colonneDebutMot].estVide() &&  plateau.getPlateau()[y][colonneDebutMot+1].estVide()){
                            System.out.println("Le mot doit être en contacte avec un autre mot");
                        }

                    }
                }
            } else {
                if (directionMot.equals(Direction.VERTICAL)) {
                    int colonneDebutMot = coordonnees.getColonne();
                    int ligneDebutDebut = coordonnees.getLigne();
                    for (int x = colonneDebutMot; !plateau.getPlateau()[ligneDebutDebut + 1][x].estVide(); x++) {
                        if (!plateau.getPlateau()[ligneDebutDebut][x - 1].estVide() || !plateau.getPlateau()[ligneDebutDebut][x + 1].estVide()) {
                            System.out.println("Le mot est coorectemnt placer");
                        } else {
                            if (plateau.getPlateau()[ligneDebutDebut][x - 1].estVide() || plateau.getPlateau()[ligneDebutDebut][x + 1].estVide() &&  plateau.getPlateau()[ligneDebutDebut+1][x].estVide()){
                                System.out.println("Le mot doit être en contacte avec un autre mot");
                            }
                        }
                    }
                }
            }
        }else {
        	int colonneDebutMot = coordonnees.getColonne();
            int ligneDebutDebut = coordonnees.getLigne();
        	if (!plateau.getPlateau()[ligneDebutDebut][colonneDebutMot - 1].estVide() || !plateau.getPlateau()[ligneDebutDebut][colonneDebutMot+ 1].estVide()
        		|| !plateau.getPlateau()[ligneDebutDebut][colonneDebutMot - 1].estVide() || !plateau.getPlateau()[ligneDebutDebut][colonneDebutMot + 1].estVide()) {
        			System.out.println("La lettre est coorectemnt placer");
        	}else {
            
                System.out.println("Le mot doit être en contacte avec un autre mot");
            }
        }
        return mot;
    }
}
