package constantine.theodoridis.game.tetris.domain;

import constantine.theodoridis.game.tetris.domain.Block;
import java.awt.Point;
import java.awt.Color;

public class PieceZ extends Piece {

  public PieceZ() {
    this.piece = new Block[Piece.getNumberOfBlocks()];
    this.color = Color.cyan;
    this.piece[0] = new Block(new Point(5, 0), this.color);
    this.piece[1] = new Block(new Point(6, 0), this.color);
    this.piece[2] = new Block(new Point(5, -1), this.color);
    this.piece[3] = new Block(new Point(4, -1), this.color);
    this.rotation = 0;
  }

  @Override
  public void rotate() {
    switch (this.rotation) {
      case 0: {
        this.piece[1].setPosition(-1, 1);
        this.piece[2].setPosition(1, 1);
        this.piece[3].setPosition(2, 0);
        this.rotation = 1;
        break;
      }
      case 1: {
        this.piece[1].setPosition(1, -1);
        this.piece[2].setPosition(-1, -1);
        this.piece[3].setPosition(-2, 0);
        this.rotation = 0;
        break;
      }
    }
  }
}
