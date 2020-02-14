package constantine.theodoridis.game.tetris.domain;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ScoreTest {

  private Score score;

  @Before
  public void setUp() {
    score = new Score();
  }

  @Test
  public void givenAddingScoreToPreviousScore_whenGettingScore_thenReturnAddedScore() {
    score.addScore(10);
    score.addScore(15);
    
    final int scoreValue = score.getScore();

    assertThat(scoreValue).isEqualTo(25);
  }
  
  @Test
  public void givenZeroScore_whenGettingScore_thenReturnZero() {
    final int scoreValue = score.getScore();

    assertThat(scoreValue).isEqualTo(0);
  }

  @Test
  @Parameters({
    "0, 20",
    "1, 30",
    "2, 45",
    "3, 65",
    "4, 100"
  })
  public void givenNonZeroScore_whenCalculatingScore_thenReturnCalculatedScore(int clearedLines, int expectedScore) {
    score.addScore(20);
    
    score.calculateScore(clearedLines);
    
    final int scoreValue = score.getScore();

    assertThat(scoreValue).isEqualTo(expectedScore);
  }
  
  @Test
  public void givenNonZeroScore_whenResettingScore_thenScoreIsZero() {
    score.addScore(10);
    
    score.reset();
    
    assertThat(score.getScore()).isEqualTo(0);
  }
  
  @Test
  public void givenScoreIsMultiple_whenCheckingIfScoreIsMultiple_thenReturnTrue() {
    score.addScore(100);
    
    final boolean isMultiple = score.isMultiple();
    
    assertThat(isMultiple).isEqualTo(true);
  }
  
  @Test
  public void givenScoreIsNotMultiple_whenCheckingIfScoreIsMultiple_thenReturnFalse() {
    score.addScore(90);
    
    final boolean isMultiple = score.isMultiple();
    
    assertThat(isMultiple).isEqualTo(false);
  }
}
