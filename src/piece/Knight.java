package piece;

import java.awt.image.BufferedImage;


/**
 * A class to make Knight
 * 
 * @see GamePiece
 * @see BufferedImage
 * @author SongJeongWoo
 * @since 2018-05-26
 */

public class Knight extends GamePiece {
  Knight(BufferedImage img, Color color, Position position, boolean alive) {
    super(img, color, PieceType.KNIGHT, position, alive);
  }
  /**
   * returns current Knight can move position
   * 
   * @return Position[] KnightWay
   */
  @Override
  public Position[] getCanMoves() {
    PieceWay way = new PieceWay(getPosition());
    Position[] KnightWay = way.waysKnightPos(color);
    return KnightWay;
  }
  
  /**
   * returns current Knight can move position for King
   * In this method, it add the position when it finds the same team piece in ways.
   * 
   * @return Position[] KnightWay
   */
  
  @Override
  public Position[] getCanMovesForKing() {
    PieceWay way = new PieceWay(getPosition());
    Position[] KnightWay = way.waysKnightPosForKing(color);
    return KnightWay;
  }
}
