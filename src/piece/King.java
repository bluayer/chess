package piece;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * A class to make King
 * 
 * @see GamePiece
 * @see BufferedImage
 * @see ArrayList
 * @author SongJeongWoo
 * @since 2018-05-27
 */

public class King extends GamePiece{
    King(BufferedImage img, Color color, Position position, boolean alive) {
      super(img, color, PieceType.KING, position, alive);
  }
    
  /**
   * returns current King can move position
   * 
   * @return ArrayList<Position> KingWay
   */
  @Override
  public Position[] getCanMoves() {
    PieceWay way = new PieceWay(getPosition());
    Position[] KingWay = way.waysKingPosCheck(color);
    return KingWay;
  }
}
