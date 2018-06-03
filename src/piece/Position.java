package piece;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import board.ChessBoard;
import board.SearchPieceByPos;
import board.Tile;
import chess.ChessGui;
import piece.GamePiece.Color;

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

  public Position() {
    
  }
  
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

  public void setX(int x) {
    this.mx = x;
  }

  public void setY(int y) {
    this.my = y;
  }

  /**
   * Judge If the piece is out of the board or not returns boolean
   * 
   * @return boolean
   */

  public boolean isValid() {
    if (mx >= 3 && mx <= 10) {
      if (my >= 0 && my <= 13) {
        return true;
      }
    }

    if (my >= 3 && my <= 10) {
      if (mx >= 0 && mx <= 13) {
        return true;
      }
    }

    return false;
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
    WN(-1, 0), WS(1, 0), WE(0, 1), WW(0, -1),

    WNE(-1, 1), WNW(-1, -1), WSE(1, 1), WSW(1, -1),

    WNNE(-2, 1), WNNW(-2, -1), WSSE(2, 1), WSSW(2, -1), WEEN(-1, 2), WEES(1, 2), WWWN(-1, -2), WWWS(1, -2), // Standard
                                                                                                            // of White
                                                                                                            // and Black
    RN(0, 1), RS(0, -1), RE(1, 0), RW(-1, 0),

    RNE(1, 1), RNW(-1, 1), RSE(1, -1), RSW(-1, -1),

    RNNE(1, 2), RNNW(-1, 2), RSSE(1, -2), RSSW(-1, -2), REEN(2, 1), REES(2, -1), RWWN(-2, 1), RWWS(-2, -1); // Standard
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

  ArrayList<Position> findPos(Direction direction, Color color) {
    ArrayList<Position> posList = new ArrayList<Position>();
    Position nowPos = moveTo(direction);
    int nowPosX = nowPos.getX();
    int nowPosY = nowPos.getY();
    
    ChessBoard board = ChessGui.b;
    
    if (nowPos.isValid()) {
      
      Tile tile = board.getcBoard()[nowPosX][nowPosY];
      while (nowPos.isValid()) {
        if(tile.isOnPiece() == false) {
          posList.add(nowPos);
          nowPos = nowPos.moveTo(direction);
          if (nowPos.isValid()) {
            tile = ChessGui.b.getcBoard()[nowPos.getX()][nowPos.getY()];
          } else {
            break;
          }
        } 
        else if(tile.isOnPiece() == true) {
          if (color == Color.BLACK || color == Color.WHITE) {
            if(SearchPieceByPos.searchPiece(nowPos, board).getColor() == Color.RED
                || SearchPieceByPos.searchPiece(nowPos, board).getColor() == Color.GREEN) {
              posList.add(nowPos);
              break;
            }
            else {
              break;
            }
          }  
          else if (color == Color.RED || color == Color.GREEN){
            if(SearchPieceByPos.searchPiece(nowPos, board).getColor() == Color.BLACK 
                || SearchPieceByPos.searchPiece(nowPos, board).getColor() == Color.WHITE) {
              posList.add(nowPos);
              break;
            }
            else {
              break;
            }
          } 
          else { 
          System.out.println("color == null");
          }
        }
        else {
          System.out.println("isPieceOn == null");
        }
      }
    }
    /* 
    for(int i=0; i<posList.size(); i++) {
      System.out.println(posList.get(i).getX());
      System.out.println(posList.get(i).getY());
      System.out.println("Next");
    }
    */
    
    return posList;
  }
  
}
