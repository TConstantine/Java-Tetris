package constantine.theodoridis.game.tetris.domain;

import java.util.Random;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

@RunWith(JUnitParamsRunner.class)
public class PieceGeneratorTest {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  private Random mockNumberGenerator;

  private PieceGenerator pieceGenerator;

  @Before
  public void setUp() {
    pieceGenerator = new PieceGenerator(mockNumberGenerator);
  }

  @Test
  @Parameters(method = "getRandomNumbersAndClasses")
  public void givenValidRandomNumber_whenGeneratingPiece_thenReturnNewPiece(int randomNumber, Class pieceClass) {
    when(mockNumberGenerator.nextInt(7)).thenReturn(randomNumber);

    final Piece piece = pieceGenerator.generate();

    assertThat(piece).isInstanceOf(pieceClass);
  }

  @Test
  public void givenInvalidRandomNumber_whenGeneratingPiece_thenReturnNull() {
    when(mockNumberGenerator.nextInt(7)).thenReturn(-1);

    final Piece piece = pieceGenerator.generate();

    assertThat(piece).isNull();
  }
  
  private Object[][] getRandomNumbersAndClasses() {
    return new Object[][] {
      {0, PieceI.class},
      {1, PieceO.class},
      {2, PieceT.class},
      {3, PieceS.class},
      {4, PieceZ.class},
      {5, PieceJ.class},
      {6, PieceL.class}
    };
  }
}
