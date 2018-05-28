package piece;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * A class to make Queen
 * 
 * @see GamePiece
 * @see BufferedImage
 * @see ArrayList
 * @author SongJeongWoo
 * @since 2018-05-26
 */

public class Queen extends GamePiece{
  Queen(BufferedImage img, Color color, Position position) {
    super(img, color, PieceType.QUEEN, position);
  }
  /**
   * returns current Queen can move position
   * 
   * @return ArrayList<Position> QueenWay
   */
  @Override
  public ArrayList<Position> getCanMoves() {
    PosManage posM = new PosManage(getPosition());
    ArrayList<Position> QueenWay = posM.waysQueenPos(color);
    return QueenWay;
  }
}
