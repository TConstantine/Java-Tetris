package constantine.theodoridis.game.tetris.domain;

import java.awt.Color;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class BoardTest {

  private Board board;

  @Before
  public void setUp() {
    board = new Board(20, 10);
  }

  @Test
  public void givenBoardWithTwentyRows_whenGettingRowCount_thenReturnTwenty() {
    final int rows = board.getRows();

    assertThat(rows).isEqualTo(20);
  }

  @Test
  public void givenBoardWithTenColumns_whenGettingColumnCount_thenReturnTen() {
    final int columns = board.getColumns();

    assertThat(columns).isEqualTo(10);
  }

  @Test
  @Parameters({
    "5, 15, true",
    "-1, 15, false",
    "25, 15, false",
    "5, -1, false",
    "5, 25, false"})
  public void givenEmptyBoard_whenCheckingIfPositionIsEmpty_thenReturnResult(int x, int y, boolean result) {
    final boolean isEmpty = board.isEmptyAt(x, y);

    assertThat(isEmpty).isEqualTo(result);
  }

  @Test
  public void givenNonEmptyBoard_whenCheckingIfPositionIsEmpty_thenReturnFalse() {
    board.addPiece(new PieceI());

    final boolean isEmpty = board.isEmptyAt(5, 0);

    assertThat(isEmpty).isEqualTo(false);
  }

  @Test
  public void givenEmptyBoard_whenCheckingIfPieceIsAtValidPosition_thenReturnTrue() {
    final boolean isAtValidPosition = board.isAtValidPosition(new PieceI());

    assertThat(isAtValidPosition).isEqualTo(true);
  }

  @Test
  public void givenNonEmptyBoard_whenCheckingIfPieceIsAtValidPosition_thenReturnFalse() {
    final Piece piece = new PieceI();
    board.addPiece(piece);

    final boolean isAtValidPosition = board.isAtValidPosition(piece);

    assertThat(isAtValidPosition).isEqualTo(false);
  }

  @Test
  public void givenEmptyBoard_whenClearingLines_thenReturnZero() {
    final int clearedLines = board.clearLines();

    assertThat(clearedLines).isEqualTo(0);
  }

  @Test
  public void givenBoardWithFullLine_whenClearingLines_thenReturnClearedLineCount() {
    fillBoard();

    final int clearedLines = board.clearLines();

    assertThat(clearedLines).isEqualTo(1);
  }
  
  @Test
  public void givenEmptyBoard_whenGrayingOutBoard_thenAllBlocksAreGray() {
    board.grayOut();

    final Color[][] colors = board.getColors();
    
    for (Color[] row : colors) {
      for (Color column : row) {
        assertThat(column).isEqualTo(Color.GRAY);
      }
    }
  }

  private void fillBoard() {
    final Piece leftPiece = new PieceI();
    movePieceLeft(leftPiece, 3);
    movePieceDown(leftPiece, 1);
    board.addPiece(leftPiece);
    final Piece rightPiece = new PieceI();
    movePieceRight(rightPiece, 3);
    movePieceDown(rightPiece, 1);
    board.addPiece(rightPiece);
    final Piece middlePiece = new PieceO();
    movePieceDown(middlePiece, 2);
    board.addPiece(middlePiece);
    final Piece extraPiece = new PieceI();
    board.addPiece(extraPiece);
  }

  private void movePieceLeft(Piece piece, int times) {
    for (int i = 0; i < times; i++) {
      piece.movePieceLeft();
    }
  }

  private void movePieceRight(Piece piece, int times) {
    for (int i = 0; i < times; i++) {
      piece.movePieceRight();
    }
  }

  private void movePieceDown(Piece piece, int times) {
    for (int i = 0; i < times; i++) {
      piece.movePieceDown();
    }
  }
}
