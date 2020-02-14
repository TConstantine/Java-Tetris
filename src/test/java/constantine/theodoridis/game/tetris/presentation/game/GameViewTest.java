package constantine.theodoridis.game.tetris.presentation.game;

import constantine.theodoridis.game.tetris.Main;
import constantine.theodoridis.game.tetris.resources.TranslatableString;
import constantine.theodoridis.game.tetris.resources.UntranslatableLabel;
import java.awt.Frame;
import org.assertj.swing.core.GenericTypeMatcher;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.application;
import org.junit.Test;

public class GameViewTest extends AssertJSwingJUnitTestCase {

  private FrameFixture application;

  @Override
  protected void onSetUp() {
    application(Main.class).start();
    application = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
      @Override
      protected boolean isMatching(Frame frame) {
        return TranslatableString.APPLICATION_TITLE.equals(frame.getTitle()) && frame.isShowing();
      }
    }).using(robot());
  }

  @Test
  public void givenApplicationIsLaunched_thenDisplayBoardPanel() {
    application.panel(UntranslatableLabel.MAIN_PANEL).panel(UntranslatableLabel.BOARD_PANEL).requireVisible();
  }
}
