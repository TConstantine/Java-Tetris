package constantine.theodoridis.game.tetris.domain;

import constantine.theodoridis.game.tetris.domain.Block;
import java.awt.Color;

public abstract class Piece {

  protected Block[] piece;
  protected Color color;
  protected int rotation;

  public static int getNumberOfBlocks() {
    return 4;
  }

  public Block[] getPiece() {
    return this.piece;
  }

  public int getLength() {
    return this.piece.length;
  }

  public int getRotation() {
    return this.rotation;
  }

  public void movePieceDown() {
    for (int i = 0; i < this.piece.length; ++i) {
      this.piece[i].moveSpriteDown();
    }
  }

  public void movePieceLeft() {
    for (int i = 0; i < this.piece.length; ++i) {
      this.piece[i].moveSpriteLeft();
    }
  }

  public void movePieceRight() {
    for (int i = 0; i < this.piece.length; ++i) {
      this.piece[i].moveSpriteRight();
    }
  }

  public abstract void setRotation();
}
