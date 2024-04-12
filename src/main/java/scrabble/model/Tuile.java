package scrabble.model;

public class Tuile {
  private final LettreAlphabet lettre;
  
  public Tuile(LettreAlphabet lettre) {
    this.lettre = lettre;
  }

  public LettreAlphabet getLettre() {
    return this.lettre;
  }
  
  public String description() {
	return this.lettre.toString() + + this.lettre.getPoints();
  }
}