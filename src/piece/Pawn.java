package piece;

import java.awt.image.BufferedImage;

/**
 * A class to make Pawn
 * 
 * @see GamePiece
 * @see BufferedImage
 * @author SongJeongWoo
 * @since 2018-05-27
 */

public class Pawn extends GamePiece{
    Pawn(BufferedImage img, Color color, Position position, boolean alive) {
      super(img, color, PieceType.PAWN, position, alive);
  }
    
  /**
   * returns current Pawn can move position
   * 
   * @return Position[] PawnWay
   */
    
  @Override
  public Position[] getCanMoves() {
    PieceWay way = new PieceWay(getPosition());
    Position[] PawnWay = way.waysPawnPos(color);
    return PawnWay;
  }
  @Override
  public Position[] getCanMovesForKing() {
    return null;
  }
}
