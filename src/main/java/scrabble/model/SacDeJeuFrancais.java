package scrabble.model;

import java.util.ArrayList;

public class SacDeJeuFrancais {
	 
	private ArrayList<LettreAlphabetFrancais> lettres = new ArrayList<>();  
	
	private void faireTuilespour(int nombreDeTuile, LettreAlphabetFrancais lettre) {
		for (int i = 0; i < nombreDeTuile; i++) {
		  this.lettres.add(lettre);
		}
	}
	
	public SacDeJeuFrancais() {
	
		this.faireTuilespour(15, LettreAlphabetFrancais.E);
	    this.faireTuilespour(8, LettreAlphabetFrancais.I);
	    this.faireTuilespour(6, LettreAlphabetFrancais.N);
	    this.faireTuilespour(6, LettreAlphabetFrancais.O);
	    this.faireTuilespour(6, LettreAlphabetFrancais.R);
	    this.faireTuilespour(6, LettreAlphabetFrancais.S);
	    this.faireTuilespour(6, LettreAlphabetFrancais.T);
	    this.faireTuilespour(6, LettreAlphabetFrancais.U);
	    this.faireTuilespour(5, LettreAlphabetFrancais.L);
	    this.faireTuilespour(3, LettreAlphabetFrancais.D);
	    this.faireTuilespour(2, LettreAlphabetFrancais.G);
	    this.faireTuilespour(3, LettreAlphabetFrancais.M);
	    this.faireTuilespour(2, LettreAlphabetFrancais.B);
	    this.faireTuilespour(2, LettreAlphabetFrancais.C);
	    this.faireTuilespour(2, LettreAlphabetFrancais.P);
	    this.faireTuilespour(2, LettreAlphabetFrancais.F);
	    this.faireTuilespour(2, LettreAlphabetFrancais.H);
	    this.faireTuilespour(2, LettreAlphabetFrancais.V);
	    this.faireTuilespour(2, LettreAlphabetFrancais.JOKER);
	    this.faireTuilespour(1, LettreAlphabetFrancais.J);
	    this.faireTuilespour(1, LettreAlphabetFrancais.Q);
	    this.faireTuilespour(1, LettreAlphabetFrancais.K);
	    this.faireTuilespour(1, LettreAlphabetFrancais.W);
	    this.faireTuilespour(1, LettreAlphabetFrancais.X);
	    this.faireTuilespour(1, LettreAlphabetFrancais.Y);
	    this.faireTuilespour(1, LettreAlphabetFrancais.Z);
	}
	
	public ArrayList<LettreAlphabetFrancais> getLettres() {
        return this.lettres;
    }

}
