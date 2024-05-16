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

        if (tour != 0) {

            if (!plateau.getPlateau()[posy][posx - 1].estVide()) {
                if (tour < 2) {
                    try {
                        plateau.placerlettre(lettre, Integer.parseInt(pos_x_lettre), Integer.parseInt(pos_y_lettre));
                    } catch (NumberFormatException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (HorsPlateauException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    if (!plateau.getPlateau()[posy - 1][posx - 1].estVide() || !plateau.getPlateau()[posy + 1][posx - 1].estVide()) {
                        System.out.println("Le mot est vertical on ne peut pas le continué horizontalement");
                        return;
                    } else {
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
                }
            } else {
                if (!plateau.getPlateau()[posy - 1][posx].estVide()) {
                    if (tour < 2) {
                        try {
                            plateau.placerlettre(lettre, Integer.parseInt(pos_x_lettre), Integer.parseInt(pos_y_lettre));
                        } catch (NumberFormatException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (HorsPlateauException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else {
                        if (!plateau.getPlateau()[posy - 1][posx - 1].estVide() || !plateau.getPlateau()[posy - 1][posx + 1].estVide()) {
                            System.out.println("Le mot est horizontal on ne peut pas le continué verticalment");
                            return;
                        } else {
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
                    }
                }
            }


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
                tour++;

            }
        }

    }

    public int calculerScoreMot(int xDebut, int yDebut) {
        int score = 0;
        Case[][] plateau = this.plateau.getPlateau();

        // score de la case initiale
        score += plateau[yDebut][xDebut].getLettre().getPoints();

        for (int y = yDebut; !plateau[yDebut+1][xDebut].estVide();y++) {
            score += plateau[yDebut+1][xDebut].getLettre().getPoints();

            if (!plateau[y+1][xDebut-1].estVide()) {
                for (int x = xDebut;!plateau[y+1][x-1].estVide();x--) {
                    score += plateau[y+1][x-1].getLettre().getPoints();
                }
            }

            if (!plateau[y+1][xDebut+1].estVide()) {
                for (int x = xDebut;!plateau[y+1][x+1].estVide();x++) {
                    score += plateau[y+1][x+1].getLettre().getPoints();
                }
            }
        }

        for (int x = xDebut;!plateau[yDebut][xDebut+1].estVide();x++) {
            score += plateau[yDebut][xDebut+1].getLettre().getPoints();

            if (!plateau[yDebut-1][x+1].estVide()) {
                for (int y = yDebut;!plateau[y-1][x+1].estVide();y--) {
                    score += plateau[y-1][x+1].getLettre().getPoints();
                }
            }

            if (!plateau[yDebut+1][x+1].estVide()) {
                for (int y = yDebut;!plateau[y+1][x+1].estVide();y++) {
                    score += plateau[y+1][x+1].getLettre().getPoints();
                }
            }
        }

        return score;
    }
}
