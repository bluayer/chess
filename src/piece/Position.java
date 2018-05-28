package piece;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * A class for Piece position(location)
 * 
 * @see GamePiece
 * @see BufferedImage
 * @see ArrayList
 * @author SongJeongWoo
 * @since 2018-05-27
 */

public class Position {
  private int mx;
  private int my;

  public Position(int x, int y) {
    this.mx = x;
    this.my = y;
  }

  public int getX() {
    return this.mx;
  }

  public int getY() {
    return this.my;
  }

  /**
   * Judge If the piece is out of the board or not returns boolean
   * 
   * @return boolean
   */

  public boolean isValid() {
    if (mx < 3 || mx > 10) {
      if (my < 0 || my > 14) {
        return false;
      }
    }

    if (my < 3 || my > 10) {
      if (mx < 0 || mx > 14) {
        return false;
      }
    }

    return true;
  }

  /**
   * It's for Direction when piece move or expressing way that piece can move
   * 
   * In front of enum Direction name, W means it's for White and Black. Actually W
   * is standard of White(starting at bottom)
   * 
   * R means it's for Red and Green. R is standard of Red(starting at West)
   * 
   */

  public enum Direction {
    WN(0, 1), WS(0, -1), WE(1, 0), WW(-1, 0),

    WNE(1, 1), WNW(-1, 1), WSE(1, -1), WSW(-1, -1),

    WNNE(1, 2), WNNW(-1, 2), WSSE(1, -2), WSSW(-1, -2), WEEN(2, 1), WEES(2, -1), WWWN(-2, 1), WWWS(-2, -1), // Standard
                                                                                                            // of White
                                                                                                            // and Black

    RN(1, 0), RS(-1, 0), RE(0, 1), RW(0, -1),

    RNE(1, 1), RNW(1, -1), RSE(-1, 1), RSW(-1, -1),

    RNNE(2, 1), RNNW(2, -1), RSSE(-2, 1), RSSW(-2, -1), REEN(1, 2), REES(-1, 2), RWWN(1, -2), RWWS(-1, -2); // Standard
                                                                                                            // of Red
                                                                                                            // and Green

    private int mxD;
    private int myD;

    private Direction(int mxD, int myD) {
      this.mxD = mxD;
      this.myD = myD;
    }

    public int getXD() {
      return mxD;
    }

    public int getYD() {
      return myD;
    }

    public static Direction[] WRookD() {
      return new Direction[] { WN, WE, WS, WW };
    }

    public static Direction[] WBishopD() {
      return new Direction[] { WNE, WNW, WSE, WSW };
    }

    public static Direction[] WAllD() {
      return new Direction[] { WN, WE, WS, WW, WNE, WNW, WSE, WSW };
    }

    public static Direction[] WKnightD() {
      return new Direction[] { WNNE, WNNW, WSSE, WSSW, WEEN, WEES, WWWN, WWWS };
    }

    public static Direction[] RRookD() {
      return new Direction[] { RN, RE, RS, RW };
    }

    public static Direction[] RBishopD() {
      return new Direction[] { RNE, RNW, RSE, RSW };
    }

    public static Direction[] RAllD() {
      return new Direction[] { RN, RE, RS, RW, RNE, RNW, RSE, RSW };
    }

    public static Direction[] RKnightD() {
      return new Direction[] { RNNE, RNNW, RSSE, RSSW, REEN, REES, RWWN, RWWS };
    }
  }

  /**
   * One tile move with direction at current position
   * 
   * @param Direction
   *          direction
   * @return Position(this.mx + direction.getXD(), this.my + direction.getYD())
   */

  Position moveTo(Direction direction) {
    return new Position(this.mx + direction.getXD(), this.my + direction.getYD());
  }

  /**
   * It's for finding Position with direction in valid board. It will use in
   * PosManage for finding ways that piece can move.
   * 
   * 
   * @param Direction
   *          direction
   * @return ArrayList<Position> pos
   */

  Position[] findPos(Direction direction) {
    Position[] pos = new Position[196];
    Position nowPos = moveTo(direction);

    for (int i = 0; i < pos.length; i++) {
      while (nowPos.isValid()) {
        pos[i] = nowPos;
        nowPos = nowPos.moveTo(direction);
      }
    }
    return pos;
  }
}
