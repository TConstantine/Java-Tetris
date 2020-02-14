package constantine.theodoridis.game.tetris.presentation.game;

import java.awt.Color;
import java.awt.Point;
import javafx.util.Pair;

public interface GameContract {

  interface Model {

    public Color[][] getBoard();
    
    public Pair<Point, Color>[] getCurrentPiece();

    public Pair<Point, Color>[] getNextPiece();

    public String getScore();
    
    public int getSpeed();
    
    public void movePieceDown();
    
    public void movePieceLeft();
    
    public void movePieceRight();
    
    public void pauseGame();
    
    public void playNextFrame();
    
    public void rotatePiece();

    public void startNewGame();
  }

  interface View {

    public void displayBoard(Color[][] board);
    
    public void displayCurrentPiece(Pair<Point, Color>[] currentPiece);
    
    public void displayNextPiece(Pair<Point, Color>[] nextPiece);
    
    public void displayScore(String score);

    public void setPresenter(GameContract.Presenter presenter);
    
    public void start();
  }

  interface Presenter {

    public void onDownArrowPress();

    public void onLeftArrowPress();

    public void onNextFrame();

    public void onPause();

    public void onRightArrowPress();

    public void onSleep() throws InterruptedException;

    public void onStart();

    public void onUpArrowPress();
  }
}
