package piece;

import java.awt.image.BufferedImage;

/**
 * A class to make Bishop
 * 
 * @see GamePiece
 * @see BufferedImage
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
   * @return Position[] BishopWay
   */
  @Override
  public Position[] getCanMoves() {
    PieceWay way = new PieceWay(getPosition());
    Position[] BishopWay = way.waysBishopPos(color);
    return BishopWay;
  }

  
  /**
   * returns current Bishop can move position for King
   * In this method, it add the position when it finds the same team piece in ways.
   * 
   * @return Position[] BishopWay
   */
  @Override
  public Position[] getCanMovesForKing() {
    PieceWay way = new PieceWay(getPosition());
    Position[] BishopWay = way.waysBishopPosForKing(color);
    return BishopWay;
  }
}
