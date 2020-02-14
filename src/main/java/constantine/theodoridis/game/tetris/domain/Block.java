package constantine.theodoridis.game.tetris.domain;

import java.awt.Color;
import java.awt.Point;

public class Block {

  protected Point upperLeftPoint;
  protected int xVelocity;
  protected int yVelocity;
  protected Color color;

  public Block(Point upperLeft, Color color) {
    this.upperLeftPoint = upperLeft;
    this.xVelocity = 1;
    this.yVelocity = 1;
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  public int getX() {
    return this.upperLeftPoint.x;
  }

  public int getY() {
    return this.upperLeftPoint.y;
  }

  public void moveSpriteDown() {
    upperLeftPoint.y += this.yVelocity;
  }

  public void moveSpriteLeft() {
    upperLeftPoint.x += -this.xVelocity;
  }

  public void moveSpriteRight() {
    upperLeftPoint.x += this.xVelocity;
  }

  public void setPosition(final int xVelocity, final int yVelocity) {
    upperLeftPoint.x += xVelocity;
    upperLeftPoint.y += yVelocity;
  }
}
