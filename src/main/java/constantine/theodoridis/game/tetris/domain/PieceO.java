package constantine.theodoridis.game.tetris.domain;

import java.awt.Point;
import java.awt.Color;

public class PieceO extends Piece {

  public PieceO() {
    this.piece = new Block[Piece.getNumberOfBlocks()];
    this.color = Color.yellow;
    this.piece[0] = new Block(new Point(4, 0), this.color);
    this.piece[1] = new Block(new Point(5, 0), this.color);
    this.piece[2] = new Block(new Point(4, -1), this.color);
    this.piece[3] = new Block(new Point(5, -1), this.color);
    this.rotation = 0;
  }

  @Override
  public void rotate() {
  }
}
