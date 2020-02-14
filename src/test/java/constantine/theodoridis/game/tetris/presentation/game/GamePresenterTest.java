package constantine.theodoridis.game.tetris.presentation.game;

import java.awt.Color;
import java.awt.Point;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class GamePresenterTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  private GameContract.View mockView;

  @Mock
  private GameContract.Model mockModel;

  private GameContract.Presenter presenter;

  @Before
  public void setUp() {
    presenter = new GamePresenter(mockView, mockModel);
  }

  @Test
  public void whenOnStartIsTriggered_thenStartNewGameAndDisplayIt() {
    final Color[][] board = new Color[20][10];
    when(mockModel.getBoard()).thenReturn(board);
    final Pair<Point, Color>[] currentPiece = new Pair[4];
    when(mockModel.getCurrentPiece()).thenReturn(currentPiece);
    when(mockModel.getNextPiece()).thenReturn(currentPiece);
    final String score = "0";
    when(mockModel.getScore()).thenReturn(score);

    presenter.onStart();

    InOrder inOrder = inOrder(mockModel, mockView);
    inOrder.verify(mockModel).startNewGame();
    inOrder.verify(mockView).displayBoard(board);
    inOrder.verify(mockView).displayCurrentPiece(currentPiece);
    inOrder.verify(mockView).displayNextPiece(currentPiece);
    inOrder.verify(mockView).displayScore(score);
  }

  @Test
  public void whenOnNextFrameIsTriggered_thenPlayNextFrameAndDisplayIt() {
    final Color[][] board = new Color[20][10];
    when(mockModel.getBoard()).thenReturn(board);
    final Pair<Point, Color>[] currentPiece = new Pair[4];
    when(mockModel.getCurrentPiece()).thenReturn(currentPiece);
    when(mockModel.getNextPiece()).thenReturn(currentPiece);
    final String score = "0";
    when(mockModel.getScore()).thenReturn(score);

    presenter.onNextFrame();

    InOrder inOrder = inOrder(mockModel, mockView);
    inOrder.verify(mockModel).playNextFrame();
    inOrder.verify(mockView).displayBoard(board);
    inOrder.verify(mockView).displayCurrentPiece(currentPiece);
    inOrder.verify(mockView).displayNextPiece(currentPiece);
    inOrder.verify(mockView).displayScore(score);
  }

  @Test
  public void whenOnDownArrowPressIsTriggered_thenMovePieceDownAndDisplayIt() {
    final Pair<Point, Color>[] currentPiece = new Pair[4];
    when(mockModel.getCurrentPiece()).thenReturn(currentPiece);

    presenter.onDownArrowPress();

    InOrder inOrder = inOrder(mockModel, mockView);
    inOrder.verify(mockModel).movePieceDown();
    inOrder.verify(mockView).displayCurrentPiece(currentPiece);
  }

  @Test
  public void whenOnLeftArrowPressIsTriggered_thenMovePieceLeftAndDisplayIt() {
    final Pair<Point, Color>[] currentPiece = new Pair[4];
    when(mockModel.getCurrentPiece()).thenReturn(currentPiece);

    presenter.onLeftArrowPress();

    InOrder inOrder = inOrder(mockModel, mockView);
    inOrder.verify(mockModel).movePieceLeft();
    inOrder.verify(mockView).displayCurrentPiece(currentPiece);
  }

  @Test
  public void whenOnRightArrowPressIsTriggered_thenMovePieceRightAndDisplayIt() {
    final Pair<Point, Color>[] currentPiece = new Pair[4];
    when(mockModel.getCurrentPiece()).thenReturn(currentPiece);

    presenter.onRightArrowPress();

    InOrder inOrder = inOrder(mockModel, mockView);
    inOrder.verify(mockModel).movePieceRight();
    inOrder.verify(mockView).displayCurrentPiece(currentPiece);
  }

  @Test
  public void whenOnUpArrowPressIsTriggered_thenRotateAndDisplayPiece() {
    final Pair<Point, Color>[] currentPiece = new Pair[4];
    when(mockModel.getCurrentPiece()).thenReturn(currentPiece);

    presenter.onUpArrowPress();

    InOrder inOrder = inOrder(mockModel, mockView);
    inOrder.verify(mockModel).rotatePiece();
    inOrder.verify(mockView).displayCurrentPiece(currentPiece);
  }

  @Test
  public void whenOnSleepIsTriggered_thenGetSpeed() throws InterruptedException {
    presenter.onSleep();

    verify(mockModel).getSpeed();
  }

  @Test
  public void whenOnPauseIsTriggered_thenPauseGame() {
    presenter.onPause();

    verify(mockModel).pauseGame();
  }
}
