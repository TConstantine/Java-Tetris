package constantine.theodoridis.game.tetris.domain;

import java.util.Random;

public class PieceGenerator {

  private final Random numberGenerator;

  public PieceGenerator(Random numberGenerator) {
    this.numberGenerator = numberGenerator;
  }

  public Piece generate() {
    final int number = numberGenerator.nextInt(7) + 1;
    switch (number) {
      case 1:
        return new PieceI();
      case 2:
        return new PieceO();
      case 3:
        return new PieceT();
      case 4:
        return new PieceS();
      case 5:
        return new PieceZ();
      case 6:
        return new PieceJ();
      case 7:
        return new PieceL();
      default:
        return null;
    }
  }
}
