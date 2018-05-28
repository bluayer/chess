package piece;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * A class to make Rook
 * 
 * @see GamePiece
 * @see BufferedImage
 * @see ArrayList
 * @author SongJeongWoo
 * @since 2018-05-26
 */

public class Rook extends GamePiece{
  Rook(BufferedImage img, Color color, Position position) {
    super(img, color, PieceType.ROOK, position);
  }
  
  /**
   * returns current Rook can move position
   * 
   * @return ArrayList<Position> RookWay
   */
  
  @Override
  public ArrayList<Position> getCanMoves() {
    PosManage posM = new PosManage(getPosition());
    ArrayList<Position> RookWay = posM.waysRookPos(color);
    return RookWay;
  }
}