package piece;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * A class to make Knight
 * 
 * @see GamePiece
 * @see BufferedImage
 * @see ArrayList
 * @author SongJeongWoo
 * @since 2018-05-26
 */

public class Knight extends GamePiece {
  Knight(BufferedImage img, Color color, Position position) {
    super(img, color, PieceType.KNIGHT, position);
  }
  /**
   * returns current Knight can move position
   * 
   * @return ArrayList<Position> KnightWay
   */
  @Override
  public ArrayList<Position> getCanMoves() {
    PosManage posM = new PosManage(getPosition());
    ArrayList<Position> KnightWay = posM.waysKnightPos(color);
    return KnightWay;
  }
}
