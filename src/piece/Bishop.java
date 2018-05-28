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
  Bishop(BufferedImage img, Color color, Position position) {
    super(img, color, PieceType.BISHOP, position);
  }

  /**
   * returns current Bishop can move position
   * 
   * @return ArrayList<Position> BishopWay
   */
  @Override
  public ArrayList<Position> getCanMoves() {
    PosManage posM = new PosManage(getPosition());
    ArrayList<Position> BishopWay = posM.waysBishopPos(color);
    return BishopWay;
  }
}
