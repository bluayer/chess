package piece;

import java.awt.image.BufferedImage;

/**
 * A class to make Queen
 * 
 * @see GamePiece
 * @see BufferedImage
 * @author SongJeongWoo
 * @since 2018-05-26
 */

public class Queen extends GamePiece{
  Queen(BufferedImage img, Color color, Position position, boolean alive) {
    super(img, color, PieceType.QUEEN, position, alive);
  }
  /**
   * returns current Queen can move position
   * 
   * @return Position[] QueenWay
   */
  @Override
  public Position[] getCanMoves() {
    PieceWay way = new PieceWay(getPosition());
    Position[] QueenWay = way.waysQueenPos(color);
    return QueenWay;
  }
  
  /**
   * returns current Queen can move position for King
   * In this method, it add the position when it finds the same team piece in ways.
   * 
   * @return Position[] QueenWay
   */
  
  @Override
  public Position[] getCanMovesForKing() {
    PieceWay way = new PieceWay(getPosition());
    Position[] QueenWay = way.waysQueenPosForKing(color);
    return QueenWay;
  }
}
