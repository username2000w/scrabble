package scrabble.model;

public class Case {
	private Bonus bonus;
	private Tuile tuile;
	
	
	public Case() {
		this.bonus = null; //TODO Ajouter le bonus à la v2
		this.tuile = null;
	}


	public Boolean estVide() {
		if (this.getTuile() == null) {
			return true;
		}
		return false;
	}

	

	public Tuile getTuile() {
		return this.tuile;
	}


	public void setTuile(Tuile tuile) {
		this.tuile = tuile;
	}


	
	public Bonus getBonus() {
		return bonus;
	}


	public void setBonus(Bonus bonus) {
		this.bonus = bonus;
	}
}

