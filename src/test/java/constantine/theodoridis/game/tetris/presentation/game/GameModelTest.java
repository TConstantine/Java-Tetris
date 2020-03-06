package constantine.theodoridis.game.tetris.presentation.game;

import constantine.theodoridis.game.tetris.domain.Board;
import constantine.theodoridis.game.tetris.domain.CollisionDetector;
import constantine.theodoridis.game.tetris.domain.Piece;
import constantine.theodoridis.game.tetris.domain.PieceGenerator;
import constantine.theodoridis.game.tetris.domain.PieceI;
import constantine.theodoridis.game.tetris.domain.Score;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InOrder;
import org.mockito.Mock;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class GameModelTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  private Board mockBoard;

  @Mock
  private PieceGenerator mockPieceGenerator;

  @Mock
  private Score mockScore;

  @Mock
  private CollisionDetector mockCollisionDetector;

  private GameContract.Model model;

  @Before
  public void setUp() {
    model = new GameModel(mockBoard, mockPieceGenerator, mockScore, mockCollisionDetector);
  }

  @Test
  public void whenStartingNewGame_thenResetGame() {
    model.startNewGame();

    InOrder inOrder = inOrder(mockBoard, mockPieceGenerator, mockScore);
    inOrder.verify(mockBoard).clear();
    inOrder.verify(mockPieceGenerator, times(2)).generate();
    inOrder.verify(mockScore).reset();
  }

  @Test
  public void givenGameIsPaused_whenPlayingNextFrame_thenNothingHappens() {
    model.pauseGame();

    model.playNextFrame();

    verifyNoInteractions(mockCollisionDetector);
    verifyNoInteractions(mockBoard);
    verifyNoInteractions(mockScore);
    verifyNoInteractions(mockPieceGenerator);
  }

  @Test
  public void givenCurrentPieceCanMoveDown_whenPlayingNextFrame_thenCurrentPieceMovesDown() {
    final Piece currentPiece = new PieceI();
    final int y = currentPiece.getPiece()[0].getY();
    when(mockPieceGenerator.generate()).thenReturn(currentPiece);
    when(mockCollisionDetector.canPieceMoveDown(any(), any())).thenReturn(true);
    model.startNewGame();

    model.playNextFrame();

    assertThat(model.getCurrentPiece()[0].getKey().y, is(y + 1));
  }

  @Test
  public void givenCurrentPieceCantMoveDownAndBoardIsNotFull_whenPlayingNextFrame_thenGameIsUpdated() {
    final Piece currentPiece = new PieceI();
    final int clearedLines = 0;
    when(mockPieceGenerator.generate()).thenReturn(currentPiece);
    when(mockCollisionDetector.canPieceMoveDown(any(), any())).thenReturn(false);
    when(mockBoard.isAtValidPosition(any())).thenReturn(true);
    when(mockScore.isMultiple()).thenReturn(false);
    model.startNewGame();

    model.playNextFrame();

    InOrder inOrder = inOrder(mockBoard, mockScore, mockPieceGenerator);
    inOrder.verify(mockBoard).addPiece(currentPiece);
    inOrder.verify(mockScore).addScore(5);
    inOrder.verify(mockScore).calculateScore(clearedLines);
    inOrder.verify(mockPieceGenerator).generate();
  }

  @Test
  public void givenCurrentPieceCantMoveDownAndBoardIsFull_whenPlayingNextFrame_thenGameIsOver() {
    when(mockCollisionDetector.canPieceMoveDown(any(), any())).thenReturn(false);
    when(mockBoard.isAtValidPosition(any())).thenReturn(false);
    model.startNewGame();

    model.playNextFrame();

    verify(mockBoard).grayOut();
  }
}
