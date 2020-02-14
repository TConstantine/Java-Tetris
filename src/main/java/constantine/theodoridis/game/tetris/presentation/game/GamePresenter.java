package constantine.theodoridis.game.tetris.presentation.game;

public class GamePresenter implements GameContract.Presenter {

  private final GameContract.View view;
  private final GameContract.Model model;

  public GamePresenter(GameContract.View view, GameContract.Model model) {
    this.view = view;
    this.model = model;
  }

  @Override
  public void onDownArrowPress() {
    model.movePieceDown();
    view.displayCurrentPiece(model.getCurrentPiece());
  }

  @Override
  public void onLeftArrowPress() {
    model.movePieceLeft();
    view.displayCurrentPiece(model.getCurrentPiece());
  }

  @Override
  public void onNextFrame() {
    model.playNextFrame();
    view.displayBoard(model.getBoard());
    view.displayCurrentPiece(model.getCurrentPiece());
    view.displayNextPiece(model.getNextPiece());
    view.displayScore(model.getScore());
  }

  @Override
  public void onPause() {
    model.pauseGame();
  }

  @Override
  public void onRightArrowPress() {
    model.movePieceRight();
    view.displayCurrentPiece(model.getCurrentPiece());
  }

  @Override
  public void onSleep() throws InterruptedException {
    Thread.sleep(model.getSpeed());
  }

  @Override
  public void onStart() {
    model.startNewGame();
    view.displayBoard(model.getBoard());
    view.displayCurrentPiece(model.getCurrentPiece());
    view.displayNextPiece(model.getNextPiece());
    view.displayScore(model.getScore());
  }

  @Override
  public void onUpArrowPress() {
    model.rotatePiece();
    view.displayCurrentPiece(model.getCurrentPiece());
  }
}
