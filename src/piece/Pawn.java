package piece;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * A class to make Pawn
 * 
 * @see GamePiece
 * @see BufferedImage
 * @see ArrayList
 * @author SongJeongWoo
 * @since 2018-05-27
 */

public class Pawn extends GamePiece{
    Pawn(BufferedImage img, Color color, Position position) {
      super(img, color, PieceType.PAWN, position);
  }
  /**
   * returns current Pawn can move position
   * 
   * @return ArrayList<Position> PawnWay
   */
  @Override
  public ArrayList<Position> getCanMoves() {
    PosManage posM = new PosManage(getPosition());
    ArrayList<Position> PawnWay = posM.waysPawnPos(color);
    return PawnWay;
  }
}
