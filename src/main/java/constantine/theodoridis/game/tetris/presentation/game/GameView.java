package constantine.theodoridis.game.tetris.presentation.game;

import constantine.theodoridis.game.tetris.resources.TranslatableString;
import constantine.theodoridis.game.tetris.resources.UntranslatableLabel;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javafx.util.Pair;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class GameView implements GameContract.View, Runnable {

  private static final int SIZE = 25;
  private GameContract.Presenter presenter;
  private final JFrame window;
  private JPanel mainPanel;
  private JPanel sidePanel;
  private JPanel boardPanel;
  private JButton newGameButton;
  private Color[][] board;
  private Pair<Point, Color>[] currentPiece;
  private Pair<Point, Color>[] nextPiece;
  private String score;

  public GameView() {
    window = new JFrame(TranslatableString.APPLICATION_TITLE);
    setUpMainPanel();
    setUpBoardPanel();
    setUpSidePanel();
    setUpNewGameButton();
    addComponents();
    window.setFocusable(true);
    window.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
      }

      @Override
      public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
          case KeyEvent.VK_LEFT: {
            presenter.onLeftArrowPress();
            boardPanel.repaint();
            break;
          }
          case KeyEvent.VK_RIGHT: {
            presenter.onRightArrowPress();
            boardPanel.repaint();
            break;
          }
          case KeyEvent.VK_DOWN: {
            presenter.onDownArrowPress();
            boardPanel.repaint();
            break;
          }
          case KeyEvent.VK_UP: {
            presenter.onUpArrowPress();
            boardPanel.repaint();
            break;
          }
          case KeyEvent.VK_SPACE: {
            presenter.onPause();
            break;
          }
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
      }
    });
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.pack();
    window.setResizable(false);
    window.setLocationRelativeTo(null);
    window.setVisible(true);
  }

  @Override
  public void displayBoard(Color[][] board) {
    if (this.board == null) {
      boardPanel.setBounds(SIZE, SIZE, board[0].length * SIZE, board.length * SIZE);
    }
    this.board = board;
  }

  @Override
  public void displayCurrentPiece(Pair<Point, Color>[] currentPiece) {
    this.currentPiece = currentPiece;
  }

  @Override
  public void displayNextPiece(Pair<Point, Color>[] nextPiece) {
    this.nextPiece = nextPiece;
  }

  @Override
  public void displayScore(String score) {
    this.score = score;
  }

  @Override
  public void setPresenter(GameContract.Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void start() {
    presenter.onStart();
    window.requestFocusInWindow();
    new Thread(this).start();
  }

  @Override
  public void run() {
    while (true) {
      boardPanel.repaint();
      try {
        presenter.onSleep();
      } catch (InterruptedException e) {
      }
      presenter.onNextFrame();
      boardPanel.repaint();
      sidePanel.repaint();
    }
  }

  private void setUpMainPanel() {
    mainPanel = new JPanel();
    mainPanel.setName(UntranslatableLabel.MAIN_PANEL);
    mainPanel.setPreferredSize(new Dimension(450, 575));
    mainPanel.setLayout(null);
  }

  private void setUpBoardPanel() {
    boardPanel = new JPanel() {
      @Override
      public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        drawCurrentPiece(g);
        drawBoard(g);
      }
    };
    boardPanel.setName(UntranslatableLabel.BOARD_PANEL);
    boardPanel.setPreferredSize(new Dimension(250, 500));
    boardPanel.setBackground(new Color(253, 242, 231));
    boardPanel.setBorder(BorderFactory.createLineBorder(Color.black));
  }

  private void drawCurrentPiece(Graphics g) {
    for (Pair<Point, Color> block : currentPiece) {
      final Point coordinates = block.getKey();
      drawBlock(g, coordinates.x, coordinates.y, block.getValue());
    }
  }

  private void drawBoard(Graphics g) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] != Color.WHITE) {
          drawBlock(g, j, i, board[i][j]);
        }
      }
    }
  }

  private void drawBlock(Graphics g, int x, int y, Color color) {
    g.setColor(Color.black);
    g.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);
    g.setColor(color);
    g.fillRect(x * SIZE + 1, y * SIZE + 1, SIZE - 2, SIZE - 2);
  }

  private void setUpSidePanel() {
    final Font text = new Font("Times New Roman", 1, 20);
    sidePanel = new JPanel() {
      @Override
      public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.setFont(text);
        g.drawString("Next", 35, 25);
        for (Pair<Point, Color> block : nextPiece) {
          final Point coordinates = block.getKey();
          drawBlock(g, coordinates.x - 3, coordinates.y + 3, block.getValue());
        }
        g.setColor(Color.black);
        g.drawString("Score", 35, 150);
        g.drawString(score, 35, 175);
      }
    };
    sidePanel.setPreferredSize(new Dimension(SIZE * 5, SIZE * 4));
    sidePanel.setBounds(300, 25, 125, 500);
    sidePanel.setOpaque(false);
    sidePanel.setLayout(null);
  }

  private void setUpNewGameButton() {
    newGameButton = new JButton("New Game");
    newGameButton.setBounds(25, 225, 100, 30);
    newGameButton.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        presenter.onStart();
        window.requestFocusInWindow();
      }

      @Override
      public void mousePressed(MouseEvent e) {
      }

      @Override
      public void mouseReleased(MouseEvent e) {
      }

      @Override
      public void mouseEntered(MouseEvent e) {
      }

      @Override
      public void mouseExited(MouseEvent e) {
      }
    });
  }

  private void addComponents() {
    sidePanel.add(newGameButton);
    mainPanel.add(boardPanel);
    mainPanel.add(sidePanel);
    window.add(mainPanel);
  }
}
