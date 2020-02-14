package constantine.theodoridis.game.tetris.presentation.game;

import constantine.theodoridis.game.tetris.domain.Board;
import constantine.theodoridis.game.tetris.domain.CollisionDetector;
import constantine.theodoridis.game.tetris.domain.PieceGenerator;
import constantine.theodoridis.game.tetris.domain.Score;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
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
}
