package scrabble.model;

import java.util.ArrayList;
import java.util.Scanner;

import scrabble.gui.Console;
import scrabble.model.utils.exception.HorsPlateauException;

public class Joueur {
    private Chevalet chevalet;
    private final String nom;
    private Plateau plateau;

    public Joueur(Chevalet chevalet, String nom, Plateau plateau, Sac sac) {
        this.chevalet = chevalet;
        this.nom = nom;
        this.plateau = plateau;
        chevalet.remplirChevalet(sac);
    }

    public String getNom() {
        return nom;
    }

    public void echanger(Sac sac, int tuileIndex) {
        this.chevalet.echanger(sac, tuileIndex);
        this.chevalet.piocher(sac);
    }

    public void retirerLettreDuChevalet(LettreAlphabetFrancais lettre) {
        this.chevalet.retirerLettre(lettre);
    }

    int tour = 0;


    private String joker = "";
    private String affichage = "";
    private int compteurJoker = 0;

    public void placerEtRetirerLettre(LettreAlphabetFrancais lettre, String pos_x_lettre, String pos_y_lettre, String affichage) {
    	try {
            if (joker.equals("JOKER")) {
                if (compteurJoker == 0) {
                    plateau.placerlettreJoker(Integer.parseInt(pos_x_lettre), Integer.parseInt(pos_y_lettre), affichage, compteurJoker);
                } else if (compteurJoker == 1) {
                    plateau.placerlettreJoker(Integer.parseInt(pos_x_lettre), Integer.parseInt(pos_y_lettre), affichage, compteurJoker);
                } else {
                    System.out.println("Vous ne pouvez pas placer plus de 2 jokers");
                }
                retirerLettreDuChevalet(LettreAlphabetFrancais.JOKER);
                compteurJoker++;
                joker = "";
            } else {
                plateau.placerlettre(lettre, Integer.parseInt(pos_x_lettre), Integer.parseInt(pos_y_lettre));
                chevalet.retirerLettre(lettre);
            }

        } catch (NumberFormatException | HorsPlateauException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> jouerlettre() {
        System.out.print("Quel lettre voulez-vous jouer ? : ");
        Scanner scanner = new Scanner(System.in);
        String choixlettre = scanner.nextLine().toUpperCase();


        while (chevalet.getTuileAvecLettre(choixlettre) == null) {
            System.out.print("Quel lettre voulez-vous jouer ? : ");
            Scanner scanner4 = new Scanner(System.in);
            choixlettre = scanner4.nextLine().toUpperCase();
        }

        if (choixlettre.equals("JOKER")) {
            System.out.print("[JOKER] Quel lettre voulez-vous jouer ? : ");
            scanner = new Scanner(System.in);
            joker = "JOKER";
            choixlettre = scanner.nextLine().toUpperCase();
            affichage = choixlettre;
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
                    placerEtRetirerLettre(lettre, pos_x_lettre, pos_y_lettre, affichage);

                } else {
                    if (!plateau.getPlateau()[posy - 1][posx - 1].estVide() || !plateau.getPlateau()[posy + 1][posx - 1].estVide()) {
                        System.out.println("Le mot est vertical on ne peut pas le continué horizontalement");
                        return null;
                    } else {
                        placerEtRetirerLettre(lettre, pos_x_lettre, pos_y_lettre, affichage);

                    }
                }
            } else {
                if (!plateau.getPlateau()[posy - 1][posx].estVide()) {
                    if (tour == 1) {
                        placerEtRetirerLettre(lettre, pos_x_lettre, pos_y_lettre, affichage);

                    } else {
                        if (!plateau.getPlateau()[posy - 1][posx - 1].estVide() || !plateau.getPlateau()[posy - 1][posx + 1].estVide()) {
                            System.out.println("Le mot est horizontal on ne peut pas le continué verticalment");
                            return null;
                        } else {
                            placerEtRetirerLettre(lettre, pos_x_lettre, pos_y_lettre, affichage);

                        }
                    }
                } else {
                	 System.out.println("La lettre doit suivre la première lettre ");
                }
            }
        } else {
        	 placerEtRetirerLettre(lettre, pos_x_lettre, pos_y_lettre, affichage);
        	 ArrayList<Integer> coordonnees = new ArrayList<>();
             coordonnees.add(posx);
             coordonnees.add(posy);
             return coordonnees;
        }
		return null;



    }

    public ArrayList<Integer> jouerMot() {
    	ArrayList<Integer> coordonnees = null;
    	tour = 0;

        System.out.println("Quel Mot voulez-vous jouer ? : ");
        Scanner scanner = new Scanner(System.in);
        int choix = 0;
        while (choix != 2) {
        	if (tour == 0) {
        		coordonnees = jouerlettre();
        	} else {
        		jouerlettre();
        	}
            System.out.println("1. Continuez à placer une lettre ");
            System.out.println("2. finir le tour ");

            choix = scanner.nextInt();
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

    public int calculerScoreMot(int xDebut, int yDebut) {
        int score = 0;
        Case[][] plateau = this.plateau.getPlateau();

        // score de la case initiale
        score += plateau[yDebut][xDebut].getLettre().getPoints();

        for (int y = yDebut; !plateau[y + 1][xDebut].estVide(); y++) {
            score += plateau[yDebut + 1][xDebut].getLettre().getPoints();

            if (!plateau[y + 1][xDebut - 1].estVide()) {
                for (int x = xDebut; !plateau[y + 1][x - 1].estVide(); x--) {
                    score += plateau[y + 1][x - 1].getLettre().getPoints();
                }
            }

            if (!plateau[y + 1][xDebut + 1].estVide()) {
                for (int x = xDebut; !plateau[y + 1][x + 1].estVide(); x++) {
                    score += plateau[y + 1][x + 1].getLettre().getPoints();
                }
            }
        }

        for (int x = xDebut; !plateau[yDebut][x + 1].estVide(); x++) {
            score += plateau[yDebut][xDebut + 1].getLettre().getPoints();

            if (!plateau[yDebut - 1][x + 1].estVide()) {
                for (int y = yDebut; !plateau[y - 1][x + 1].estVide(); y--) {
                    score += plateau[y - 1][x + 1].getLettre().getPoints();
                }
            }

            if (!plateau[yDebut + 1][x + 1].estVide()) {
                for (int y = yDebut; !plateau[y + 1][x + 1].estVide(); y++) {
                    score += plateau[y + 1][x + 1].getLettre().getPoints();
                }
            }
        }

        return score;
    }

    public Chevalet getChevalet() {
        return chevalet;
    }
}
