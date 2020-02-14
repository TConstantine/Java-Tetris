package constantine.theodoridis.game.tetris.domain;

public class Score {

  private static final int FIFTY_POINTS = 50;
  private static final int ONE_CLEARED_LINE = 1;
  private static final int TWO_CLEARED_LINES = 2;
  private static final int THREE_CLEARED_LINES = 3;
  private static final int FOUR_CLEARED_LINES = 4;
  private int scoreValue;

  public Score() {
    scoreValue = 0;
  }

  public void addScore(final int score) {
    scoreValue += score;
  }

  public void calculateScore(int clearedLines) {
    switch (clearedLines) {
      case ONE_CLEARED_LINE:
        addScore(10);
        break;
      case TWO_CLEARED_LINES:
        addScore(25);
        break;
      case THREE_CLEARED_LINES:
        addScore(45);
        break;
      case FOUR_CLEARED_LINES:
        addScore(80);
        break;
      default:
        break;
    }
  }

  public int getScore() {
    return scoreValue;
  }

  public boolean isMultiple() {
    return scoreValue % FIFTY_POINTS == 0;
  }

  public void reset() {
    scoreValue = 0;
  }
}
