package scrabble.model;

import java.util.ArrayList;
import java.util.Scanner;

import scrabble.gui.Console;
import scrabble.model.utils.Coordonee;
import scrabble.model.utils.exception.HorsPlateauException;

public class Joueur {
    private Chevalet chevalet;
    private final String nom;
    private int tour = 0;
    private String joker = "";
    private String affichage = "";
    private int compteurJoker = 0;
    private int position = 0;
    

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

    public void placerEtRetirerLettre(LettreAlphabetFrancais lettre, Plateau plateau, Coordonee coordonee, String affichage) {
    	try {
            if (joker.equals("JOKER")) {
                if (compteurJoker == 0) {
                    plateau.placerlettreJoker(coordonee, affichage, compteurJoker);
                } else if (compteurJoker == 1) {
                    plateau.placerlettreJoker(coordonee, affichage, compteurJoker);
                } else {
                    System.out.println("Vous ne pouvez pas placer plus de 2 jokers");
                }
                retirerLettreDuChevalet(LettreAlphabetFrancais.JOKER);
                compteurJoker++;
                joker = "";
            } else {
                plateau.placerlettre(lettre, coordonee);
                chevalet.retirerLettre(lettre);
            }

        } catch (NumberFormatException | HorsPlateauException e) {
            e.printStackTrace();
        }
    }

    public Coordonee jouerlettre(Plateau plateau) {
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
        String posColonneLettre = Console.inputStringScanner();
        int posColonne = Integer.parseInt(posColonneLettre);

        System.out.print("Où voulez-vous la placer ? ( ↕ Verticalement ) : ");
        String posLigneLettre = Console.inputStringScanner();
        int posLigne = Integer.parseInt(posLigneLettre);

        LettreAlphabetFrancais lettre = chevalet.getTuileAvecLettre(choixlettre);
        if (joker.equals("JOKER")) {
            lettre = chevalet.getTuileAvecLettre("JOKER");
        }

        
		if (!plateau.getPlateau()[posLigne][posColonne].estVide()) {
            System.out.println("Impossible de placer une lettre ici, une lettre est déjà placée.");
            return null;
        }

        if (tour != 0) {

            if (!plateau.getPlateau()[posLigne][posColonne - 1].estVide()) {
                if (tour == 1) {
                    placerEtRetirerLettre(lettre, plateau, new Coordonee(posLigne, posColonne), affichage);
                    position = 1;
                } else {
                    if (!plateau.getPlateau()[posLigne - 1][posColonne - 1].estVide() || !plateau.getPlateau()[posLigne + 1][posColonne - 1].estVide()) {
                        System.out.println("Le mot est vertical on ne peut pas le continué horizontalement");
                        return null;
                    } else {
                        placerEtRetirerLettre(lettre, plateau, new Coordonee(posLigne, posColonne), affichage);

                    }
                }
            } else {
                if (!plateau.getPlateau()[posLigne - 1][posColonne].estVide()) {
                    if (tour == 1) {
                        placerEtRetirerLettre(lettre, plateau, new Coordonee(posLigne, posColonne), affichage);
                        position = 2;
                    } else {
                        if (!plateau.getPlateau()[posLigne - 1][posColonne - 1].estVide() || !plateau.getPlateau()[posLigne - 1][posColonne + 1].estVide()) {
                            System.out.println("Le mot est horizontal on ne peut pas le continué verticalment");
                            return null;
                        } else {
                            placerEtRetirerLettre(lettre, plateau, new Coordonee(posLigne, posColonne), affichage);

                        }
                    }
                } else {
                	 System.out.println("La lettre doit suivre la première lettre ");
                }
            }
        } else {
        	 placerEtRetirerLettre(lettre, plateau, new Coordonee(posLigne, posColonne), affichage);
             Coordonee coordonnees = new Coordonee(posLigne, posColonne);
             return coordonnees;
        }
		return null;
    }

    public Coordonee jouerMot(Plateau plateau) {
        Coordonee coordonnees = null;
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
        
        if (position == 1) {
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
        	if (position == 2) {
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
        return coordonnees;
    }

    public Chevalet getChevalet() {
        return chevalet;
    }

    public void remplirChevalet(Sac sac) {
        this.chevalet.remplirChevalet(sac);
    }
}
