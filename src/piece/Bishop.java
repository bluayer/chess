package piece;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * A class to make Bishop
 * 
 * @see GamePiece
 * @see BufferedImage
 * @see ArrayList
 * @author SongJeongWoo
 * @since 2018-05-26
 */

public class Bishop extends GamePiece{
  Bishop(BufferedImage img, Color color, Position position, boolean alive) {
    super(img, color, PieceType.BISHOP, position, alive);
  }

  /**
   * returns current Bishop can move position
   * 
   * @return ArrayList<Position> BishopWay
   */
  @Override
  public Position[] getCanMoves() {
    PieceWay way = new PieceWay(getPosition());
    Position[] BishopWay = way.waysBishopPos(color);
    return BishopWay;
  }
}
