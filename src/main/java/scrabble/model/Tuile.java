package scrabble.model;

public class Tuile {
  private final LettreAlphabet lettre;
  
  public Tuile(LettreAlphabet lettre) {
    this.lettre = lettre;
  }

  public LettreAlphabet getLettre() {
    return this.lettre;
  }
}
