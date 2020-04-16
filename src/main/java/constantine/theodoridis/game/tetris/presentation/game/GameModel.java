package constantine.theodoridis.game.tetris.presentation.game;

import constantine.theodoridis.game.tetris.domain.Block;
import constantine.theodoridis.game.tetris.domain.Piece;
import constantine.theodoridis.game.tetris.domain.Board;
import constantine.theodoridis.game.tetris.domain.CollisionDetector;
import constantine.theodoridis.game.tetris.domain.PieceGenerator;
import constantine.theodoridis.game.tetris.domain.Score;
import java.awt.Color;
import java.awt.Point;
import javafx.util.Pair;

public class GameModel implements GameContract.Model {

  private final Board board;
  private final PieceGenerator pieceGenerator;
  private final Score score;
  private final CollisionDetector collisionDetector;
  private Piece currentPiece;
  private Piece nextPiece;
  private boolean isOver;
  private boolean isPaused;
  private int speed;

  public GameModel(Board board, PieceGenerator pieceGenerator, Score score, CollisionDetector collisionDetector) {
    this.board = board;
    this.pieceGenerator = pieceGenerator;
    this.score = score;
    this.collisionDetector = collisionDetector;
  }

  @Override
  public Color[][] getBoard() {
    return board.getColors();
  }

  @Override
  public Pair<Point, Color>[] getCurrentPiece() {
    return createPairs(currentPiece);
  }

  @Override
  public Pair<Point, Color>[] getNextPiece() {
    return createPairs(nextPiece);
  }

  @Override
  public String getScore() {
    return Integer.toString(score.getScore());
  }

  @Override
  public int getSpeed() {
    return speed;
  }

  @Override
  public void movePieceDown() {
    if (!isPaused && collisionDetector.canPieceMoveDown(currentPiece, board)) {
      currentPiece.movePieceDown();
    }
  }

  @Override
  public void movePieceLeft() {
    if (!isPaused && collisionDetector.canPieceMoveLeft(currentPiece, board)) {
      currentPiece.movePieceLeft();
    }
  }

  @Override
  public void movePieceRight() {
    if (!isPaused && collisionDetector.canPieceMoveRight(currentPiece, board)) {
      currentPiece.movePieceRight();
    }
  }

  @Override
  public void pauseGame() {
    isPaused = !isPaused;
  }

  @Override
  public void playNextFrame() {
    if (!isPaused) {
      if (collisionDetector.canPieceMoveDown(currentPiece, board)) {
        currentPiece.movePieceDown();
      } else if (board.isAtValidPosition(currentPiece)) {
        isPaused = true;
        board.addPiece(currentPiece);
        score.addScore(5);
        score.calculateScore(board.clearLines());
        updateSpeed();
        currentPiece = nextPiece;
        nextPiece = pieceGenerator.generate();
        isPaused = false;
      } else {
        isPaused = true;
        isOver = true;
        board.grayOut();
      }
    }
  }

  @Override
  public void rotatePiece() {
    if (!isPaused) {
      rotatePiece(currentPiece);
    }
  }

  @Override
  public void startNewGame() {
    board.clear();
    currentPiece = pieceGenerator.generate();
    nextPiece = pieceGenerator.generate();
    score.reset();
    isOver = false;
    isPaused = false;
    speed = 1000;
  }

  private void updateSpeed() {
    if (score.isMultiple() && speed > 20) {
      speed -= 20;
    }
  }

  private Pair<Point, Color>[] createPairs(Piece piece) {
    final Pair<Point, Color>[] pairs = new Pair[4];
    for (int i = 0; i < 4; i++) {
      final Block block = piece.getPiece()[i];
      final Point coordinates = new Point(block.getX(), block.getY());
      pairs[i] = new Pair(coordinates, block.getColor());
    }
    return pairs;
  }

  private void rotatePiece(Piece piece) {
    final int rotation = piece.getRotation();
    piece.rotate();
    if (!collisionDetector.canPieceRotate(piece, board)) {
      while (piece.getRotation() != rotation) {
        piece.rotate();
      }
    }
  }
}
