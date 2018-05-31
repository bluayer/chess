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

public class Nope extends GamePiece { 
  Nope(Position position, boolean alive) {
    super(PieceType.NOPE, position, alive);
  }
  /**
   * returns current Knight can move position
   * 
   * @return ArrayList<Position> KnightWay
   */
  @Override
  public Position[] getCanMoves() {
    return null;
  }
}