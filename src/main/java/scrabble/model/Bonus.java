package scrabble.model;

public enum Bonus {
	MOT_TRIPLE(" "),
	MOT_DOUBLE(" "),
	LETTRE_DOUBLE(" "),
	LETTRE_TRIPLE(" "),
	ETOILE("★ ");
	
	private final String symbole;
	
	Bonus(String symbole) {
		this.symbole = symbole;
	}
	
	@Override
	public String toString() {
		return symbole;
	}
}
