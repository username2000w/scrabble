package scrabble.model;

import java.util.ArrayList;
import java.util.Scanner;

import scrabble.gui.Console;
import scrabble.model.utils.exception.HorsPlateauException;

public class Joueur {
    private Chevalet chevalet;
    private final String nom;
    private int tour = 0;
    private String joker = "";
    private String affichage = "";

    public Joueur(Chevalet chevalet, String nom) {
        this.chevalet = chevalet;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void echanger(Sac sac, int tuileIndex) {
        this.chevalet.echanger(sac, tuileIndex);
    }

    public void retirerLettreDuChevalet(LettreAlphabetFrancais lettre) {
        this.chevalet.retirerLettre(lettre);
    }


    public void placerEtRetirerLettre(LettreAlphabetFrancais lettre, Plateau plateau, String pos_x_lettre, String pos_y_lettre, String affichage) {
    	try {
            if (joker.equals("JOKER")) {
                plateau.placerlettreJoker(Integer.parseInt(pos_x_lettre), Integer.parseInt(pos_y_lettre), affichage);
                retirerLettreDuChevalet(LettreAlphabetFrancais.JOKER);
                joker = "";
            } else {
                plateau.placerlettre(lettre, Integer.parseInt(pos_x_lettre), Integer.parseInt(pos_y_lettre));
                chevalet.retirerLettre(lettre);
            }

        } catch (NumberFormatException | HorsPlateauException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> jouerlettre(Plateau plateau) {
        System.out.print("Quel lettre voulez-vous jouer ? : ");
        String choixlettre = Console.inputStringScanner().toUpperCase();


        while (chevalet.getTuileAvecLettre(choixlettre) == null) {
            System.out.print("Quel lettre voulez-vous jouer ? : ");
            choixlettre = Console.inputStringScanner().toUpperCase();
        }

        if (choixlettre.equals("JOKER")) {
            System.out.print("[JOKER] Quel lettre voulez-vous jouer ? : ");
            joker = "JOKER";
            choixlettre = Console.inputStringScanner().toUpperCase();
            affichage = choixlettre;
        }

        System.out.print("Où voulez-vous la placer ? ( ↔ Horizontalement ) : ");
        String pos_x_lettre = Console.inputStringScanner();
        int posx = Integer.parseInt(pos_x_lettre);


        System.out.print("Où voulez-vous la placer ? ( ↕ Verticalement ) : ");
        String pos_y_lettre = Console.inputStringScanner();
        int posy = Integer.parseInt(pos_y_lettre);

        LettreAlphabetFrancais lettre = chevalet.getTuileAvecLettre(choixlettre);
        if (joker.equals("JOKER")) {
            lettre = chevalet.getTuileAvecLettre("JOKER");
        }

        if (!plateau.getPlateau()[posy][posx].estVide()) {
            System.out.println("Impossible de placer une lettre ici, une lettre est déjà placée.");
            return null;
        }

        if (tour != 0) {

            if (!plateau.getPlateau()[posy][posx - 1].estVide()) {
                if (tour == 1) {
                    placerEtRetirerLettre(lettre, plateau, pos_x_lettre, pos_y_lettre, affichage);

                } else {
                    if (!plateau.getPlateau()[posy - 1][posx - 1].estVide() || !plateau.getPlateau()[posy + 1][posx - 1].estVide()) {
                        System.out.println("Le mot est vertical on ne peut pas le continué horizontalement");
                        return null;
                    } else {
                        placerEtRetirerLettre(lettre, plateau, pos_x_lettre, pos_y_lettre, affichage);

                    }
                }
            } else {
                if (!plateau.getPlateau()[posy - 1][posx].estVide()) {
                    if (tour == 1) {
                        placerEtRetirerLettre(lettre, plateau, pos_x_lettre, pos_y_lettre, affichage);

                    } else {
                        if (!plateau.getPlateau()[posy - 1][posx - 1].estVide() || !plateau.getPlateau()[posy - 1][posx + 1].estVide()) {
                            System.out.println("Le mot est horizontal on ne peut pas le continué verticalment");
                            return null;
                        } else {
                            placerEtRetirerLettre(lettre, plateau, pos_x_lettre, pos_y_lettre, affichage);

                        }
                    }
                } else {
                	 System.out.println("La lettre doit suivre la première lettre ");
                }
            }
        } else {
        	 placerEtRetirerLettre(lettre, plateau, pos_x_lettre, pos_y_lettre, affichage);
        	 ArrayList<Integer> coordonnees = new ArrayList<>();
             coordonnees.add(posx);
             coordonnees.add(posy);
             return coordonnees;
        }
		return null;
    }

    public ArrayList<Integer> jouerMot(Plateau plateau) {
    	ArrayList<Integer> coordonnees = null;
    	tour = 0;

        System.out.println("Quel Mot voulez-vous jouer ? : ");
        int choix = 0;
        while (choix != 2) {
        	if (tour == 0) {
        		coordonnees = jouerlettre(plateau);
        	} else {
        		jouerlettre(plateau);
        	}
            System.out.println("1. Continuez à placer une lettre ");
            System.out.println("2. finir le tour ");

            choix = Console.inputIntScanner();
            if (choix == 1) {
                Console.afficherChevalet(chevalet);
                tour++;
            }
        }

        if (plateau.getPlateau()[7][7].estVide()) {
            System.out.println("Le premier mot doit commencer sur l'étoile");
            plateau.supprimerToutesLettres();

        }
		return coordonnees;
    }

    public Chevalet getChevalet() {
        return chevalet;
    }

    public void remplirChevalet(Sac sac) {
        this.chevalet.remplirChevalet(sac);
    }
}
