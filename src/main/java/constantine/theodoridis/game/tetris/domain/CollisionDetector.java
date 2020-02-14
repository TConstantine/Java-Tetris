package constantine.theodoridis.game.tetris.domain;

public class CollisionDetector {

  public boolean canPieceMoveLeft(final Piece piece, final Board board) {
    for (Block block : piece.getPiece()) {
      final int x = block.getX();
      final int y = block.getY();
      if (!board.isWithinLeftBoundary(x) || !board.isEmptyAt(x - 1, y)) {
        return false;
      }
    }
    return true;
  }

  public boolean canPieceMoveRight(final Piece piece, final Board board) {
    for (Block block : piece.getPiece()) {
      final int x = block.getX();
      final int y = block.getY();
      if (!board.isWithinRightBoundary(x) || !board.isEmptyAt(x + 1, y)) {
        return false;
      }
    }
    return true;
  }

  public boolean canPieceMoveDown(final Piece piece, final Board board) {
    for (Block block : piece.getPiece()) {
      final int x = block.getX();
      final int y = block.getY();
      if (!board.isWithinBottomBoundary(y) || !board.isEmptyAt(x, y + 1)) {
        return false;
      }
    }
    return true;
  }

  public boolean canPieceRotate(final Piece piece, final Board board) {
    for (Block block : piece.getPiece()) {
      final int x = block.getX();
      final int y = block.getY();
      if (!board.isAtBottomBoundary(y) || !board.isWithinHorizontalBoundaries(x) || !board.isEmptyAt(x, y)) {
        return false;
      }
    }
    return true;
  }
}
