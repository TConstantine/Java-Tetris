package constantine.theodoridis.game.tetris.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;

public class CollisionDetectorTest {

  private Piece piece;
  private Board board;
  private CollisionDetector collisionDetector;

  @Before
  public void setUp() {
    piece = new PieceI();
    board = new Board(20, 10);
    collisionDetector = new CollisionDetector();
  }

  @Test
  public void givenEmptyBoard_whenCheckingIfPieceCanMoveLeft_thenReturnTrue() {
    final boolean canMoveLeft = collisionDetector.canPieceMoveLeft(piece, board);

    assertThat(canMoveLeft).isEqualTo(true);
  }
  
  @Test
  public void givenNonEmptyBoard_whenCheckingIfPieceCanMoveLeft_thenReturnFalse() {
    board.addPiece(piece);

    final boolean canMoveLeft = collisionDetector.canPieceMoveLeft(piece, board);

    assertThat(canMoveLeft).isEqualTo(false);
  }
  
  @Test
  public void givenPieceOnLeftBoundary_whenCheckingIfPieceCanMoveLeft_thenReturnFalse() {
    piece.movePieceLeft();
    piece.movePieceLeft();
    piece.movePieceLeft();
    board.addPiece(piece);

    final boolean canMoveLeft = collisionDetector.canPieceMoveLeft(piece, board);

    assertThat(canMoveLeft).isEqualTo(false);
  }
  
  @Test
  public void givenEmptyBoard_whenCheckingIfPieceCanMoveRight_thenReturnTrue() {
    final boolean canMoveRight = collisionDetector.canPieceMoveRight(piece, board);

    assertThat(canMoveRight).isEqualTo(true);
  }
  
  @Test
  public void givenNonEmptyBoard_whenCheckingIfPieceCanMoveRight_thenReturnFalse() {
    piece.movePieceRight();
    piece.movePieceRight();
    piece.movePieceRight();
    board.addPiece(piece);
    final Piece secondPiece = new PieceI();

    final boolean canMoveRight = collisionDetector.canPieceMoveRight(secondPiece, board);

    assertThat(canMoveRight).isEqualTo(false);
  }
  
  @Test
  public void givenPieceOnRightBoundary_whenCheckingIfPieceCanMoveRight_thenReturnFalse() {
    piece.movePieceRight();
    piece.movePieceRight();
    piece.movePieceRight();
    board.addPiece(piece);

    final boolean canMoveRight = collisionDetector.canPieceMoveRight(piece, board);

    assertThat(canMoveRight).isEqualTo(false);
  }
  
  @Test
  public void givenEmptyBoard_whenCheckingIfPieceCanMoveDown_thenReturnTrue() {
    final boolean canMoveDown = collisionDetector.canPieceMoveDown(piece, board);

    assertThat(canMoveDown).isEqualTo(true);
  }

  @Test
  public void givenNonEmptyBoard_whenCheckingIfPieceCanMoveDown_thenReturnFalse() {
    piece.movePieceDown();
    board.addPiece(piece);
    final Piece secondPiece = new PieceI();

    final boolean canMoveDown = collisionDetector.canPieceMoveDown(secondPiece, board);

    assertThat(canMoveDown).isEqualTo(false);
  }

  @Test
  public void givenPieceOnBottomBoundary_whenCheckingIfPieceCanMoveDown_thenReturnFalse() {
    for (int i = 0; i < board.getRows(); i++) {
      piece.movePieceDown();
    }

    final boolean canMoveDown = collisionDetector.canPieceMoveDown(piece, board);

    assertThat(canMoveDown).isEqualTo(false);
  }
  
  @Test
  public void givenEmptyBoard_whenCheckingIfPieceCanRotate_thenReturnTrue() {
    final boolean canRotate = collisionDetector.canPieceRotate(piece, board);

    assertThat(canRotate).isEqualTo(true);
  }

  @Test
  public void givenPieceOnBottomBoundary_whenCheckingIfPieceCanRotate_thenReturnFalse() {
    for (int i = 0; i < board.getRows(); i++) {
      piece.movePieceDown();
    }

    final boolean canRotate = collisionDetector.canPieceRotate(piece, board);

    assertThat(canRotate).isEqualTo(false);
  }

  @Test
  public void givenPieceOnLeftBoundary_whenCheckingIfPieceCanRotate_thenReturnFalse() {
    piece.movePieceDown();
    piece.setRotation();
    piece.movePieceLeft();
    piece.movePieceLeft();
    piece.movePieceLeft();
    piece.movePieceLeft();
    piece.movePieceLeft();
    piece.movePieceLeft();

    final boolean canRotate = collisionDetector.canPieceRotate(piece, board);

    assertThat(canRotate).isEqualTo(false);
  }

  @Test
  public void givenPieceOnRightBoundary_whenCheckingIfPieceCanRotate_thenReturnFalse() {
    piece.movePieceDown();
    piece.setRotation();
    piece.movePieceRight();
    piece.movePieceRight();
    piece.movePieceRight();
    piece.movePieceRight();
    piece.movePieceRight();
    piece.movePieceRight();

    final boolean canRotate = collisionDetector.canPieceRotate(piece, board);

    assertThat(canRotate).isEqualTo(false);
  }
  
  @Test
  public void givenNonEmptyBoard_whenCheckingIfPieceCanRotate_thenReturnFalse() {
    piece.movePieceDown();
    piece.movePieceDown();
    piece.movePieceDown();
    board.addPiece(piece);
    final Piece secondPiece = new PieceI();
    secondPiece.movePieceDown();
    secondPiece.movePieceDown();
    secondPiece.setRotation();

    final boolean canRotate = collisionDetector.canPieceRotate(secondPiece, board);

    assertThat(canRotate).isEqualTo(false);
  }
}
