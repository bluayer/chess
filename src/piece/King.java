package piece;

import java.awt.image.BufferedImage;

/**
 * A class to make King
 * 
 * @see GamePiece
 * @see BufferedImage
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
   * @return Position[] KingWay
   */
  @Override
  public Position[] getCanMoves() {
    PieceWay way = new PieceWay(getPosition());
    Position[] KingWay = way.waysKingPosCheck(color);
    return KingWay;
  }

  /**
   * returns current King can move position for King
   * In this method, it add the position when it finds the same team piece in ways.
   * 
   * @return Position[] KingWay
   */
  
  @Override
  public Position[] getCanMovesForKing() {
    PieceWay way = new PieceWay(getPosition());
    Position[] KingWay = way.waysKingPosForKing(color);
    return KingWay;
  }
}
