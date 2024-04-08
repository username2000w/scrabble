package scrabble.model;

public class Plateau {
	private Case[][] plateau = new Case[15][15]; 
	
	public Plateau() {
		for (int i = 0; i < 15; i++) {
		    for (int j = 0; j < 15; j++) {
		        plateau[i][j] = new Case();
		    }
		}
	}
}
