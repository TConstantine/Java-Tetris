package constantine.theodoridis.game.tetris.domain;

import java.awt.Color;
import java.awt.Point;

public class Board {

  private final int rows;
  private final int columns;
  private final Block[][] blocks;

  public Board(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
    blocks = new Block[rows][columns];
  }

  public boolean isEmptyAt(final int x, final int y) {
    return isWithinHorizontalBoundaries(x) && isWithinVerticalBoundaries(y) && !hasBlockAt(x, y);
  }

  public boolean isAtValidPosition(Piece piece) {
    final Block[] pieceBlocks = piece.getPiece();
    for (int i = 0; i < piece.getLength(); ++i) {
      final int x = pieceBlocks[i].getX();
      final int y = pieceBlocks[i].getY();
      if (!isEmptyAt(x, y)) {
        return false;
      }
    }
    return true;
  }
  
  public int getRows() {
    return rows;
  }

  public int getColumns() {
    return columns;
  }

  public Color[][] getColors() {
    final Color[][] colors = new Color[rows][columns];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        colors[i][j] = hasBlockAt(j, i) ? blocks[i][j].getColor() : Color.WHITE;
      }
    }
    return colors;
  }
  
  public void grayOut() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        blocks[i][j] = new Block(new Point(i, j), Color.GRAY);
      }
    }
  }

  public void clear() {
    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < columns; ++j) {
        blocks[i][j] = null;
      }
    }
  }

  public void addPiece(final Piece f) {
    for (int i = 0; i < 4; ++i) {
      blocks[f.getPiece()[i].getY()][f.getPiece()[i].getX()] = f.getPiece()[i];
    }
  }

  public int clearLines() {
    int clearedLinesCount = 0;
    final int[] linesToClear = new int[4];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns && hasBlockAt(j, i); j++) {
        if (j == columns - 1) {
          linesToClear[clearedLinesCount++] = i;
        }
      }
    }
    if (clearedLinesCount > 0) {
      moveLinesDown(linesToClear);
    }
    return clearedLinesCount;
  }

  private void moveLinesDown(int[] lines) {
    for (int line : lines) {
      while (line > 0) {
        clearLine(line);
        --line;
      }
      clearTopRow();
    }
  }

  private void clearLine(int lineNumber) {
    for (int k = 0; k < columns; k++) {
      blocks[lineNumber][k] = blocks[lineNumber - 1][k];
      if (hasBlockAt(k, lineNumber)) {
        blocks[lineNumber][k].setPosition(0, 1);
      }
    }
  }

  private void clearTopRow() {
    for (int i = 0; i < columns; i++) {
      blocks[0][i] = null;
    }
  }

  public boolean isWithinHorizontalBoundaries(int x) {
    return x >= 0 && x < columns;
  }

  private boolean isWithinVerticalBoundaries(int y) {
    return y >= 0 && y < rows;
  }

  private boolean hasBlockAt(int x, int y) {
    return blocks[y][x] != null;
  }

  public boolean isWithinLeftBoundary(int x) {
    return x > 0;
  }

  public boolean isWithinRightBoundary(int x) {
    return x < columns - 1;
  }

  public boolean isWithinBottomBoundary(int y) {
    return y < rows - 1;
  }

  public boolean isAtBottomBoundary(int y) {
    return y < rows;
  }
}
