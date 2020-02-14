package constantine.theodoridis.game.tetris;

import constantine.theodoridis.game.tetris.domain.Board;
import constantine.theodoridis.game.tetris.domain.CollisionDetector;
import constantine.theodoridis.game.tetris.domain.PieceGenerator;
import constantine.theodoridis.game.tetris.domain.Score;
import constantine.theodoridis.game.tetris.presentation.game.GameContract;
import constantine.theodoridis.game.tetris.presentation.game.GameModel;
import constantine.theodoridis.game.tetris.presentation.game.GamePresenter;
import constantine.theodoridis.game.tetris.presentation.game.GameView;
import java.util.Random;
import javax.swing.SwingUtilities;

public class Main {

  public static void main(String[] arguments) {
    SwingUtilities.invokeLater(() -> {
      final GameContract.View view = new GameView();
      final Board board = new Board(20, 10);
      final PieceGenerator pieceGenerator = new PieceGenerator(new Random());
      final Score score = new Score();
      final CollisionDetector collisionDetector = new CollisionDetector();
      final GameContract.Model model = new GameModel(board, pieceGenerator, score, collisionDetector);
      final GameContract.Presenter presenter = new GamePresenter(view, model);
      view.setPresenter(presenter);
      view.start();
    });
  }
}
