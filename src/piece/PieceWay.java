package piece;

import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import ChangminYi.ChessBoard;
import ChangminYi.SearchPieceByPos;
import ChangminYi.Status.TEAM;
import ChangminYi.Tile;
import piece.GamePiece.Color;
import piece.GamePiece.PieceType;
import piece.Position.Direction;

/**
 * A class for Piece position(location) manage. Actually, it's about how to find
 * the all ways and locations(positions) that piece can moves So it get a
 * current position(location) and using it. Also it needs color of piece cause
 * direction is different according to color.
 * 
 * @see GamePiece
 * @see BufferedImage
 * @see ArrayList
 * @see Position#Direction
 * @author SongJeongWoo
 * @since 2018-05-27
 */

public class PieceWay {
  private Position mpos;

  public PieceWay(Position mpos) {
    this.mpos = mpos;
  }

  public <T> T[] concat(T[] a, T[] b) {
    int aLen = a.length;
    int bLen = b.length;

    @SuppressWarnings("unchecked")
    T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
    System.arraycopy(a, 0, c, 0, aLen);
    System.arraycopy(b, 0, c, aLen, bLen);

    return c;
  }

  public Position[] waysRookPos(Color color) {
    Position[] RookPos = new Position[30];
    if (color == GamePiece.Color.WHITE || color == GamePiece.Color.BLACK) {
      Direction[] WRook = Direction.WRookD();
      RookPos = mpos.findPos(WRook[0]);
      for (int i = 0; i < WRook.length; i++) {
        RookPos = concat(RookPos, mpos.findPos(WRook[i]));
      }
      return RookPos;
    } else {
      Direction[] RRook = Direction.RRookD();
      RookPos = mpos.findPos(RRook[0]);
      for (int i = 0; i < RRook.length; i++) {
        RookPos = concat(RookPos, mpos.findPos(RRook[i]));
      }
      return RookPos;
    }

  }

  public Position[] waysKnightPos(Color color) {
    Position[] KnightPos = new Position[8];
    if (color == GamePiece.Color.WHITE || color == GamePiece.Color.BLACK) {
      Direction[] WKnight = Direction.WKnightD();
      for (int i = 0; i < WKnight.length; i++) {
        Position oneMovedPos = mpos.moveTo(WKnight[i]);
        if (oneMovedPos.isValid()) {
          KnightPos[i] = oneMovedPos;
        }
      }
      return KnightPos;
    } else {
      Direction[] RKnight = Direction.RKnightD();
      for (int i = 0; i < RKnight.length; i++) {
        Position oneMovedPos = mpos.moveTo(RKnight[i]);
        if (oneMovedPos.isValid()) {
          KnightPos[i] = oneMovedPos;
        }
      }
      return KnightPos;
    }
  }

  public Position[] waysBishopPos(Color color) {
    Position[] BishopPos = new Position[22];
    if (color == GamePiece.Color.WHITE || color == GamePiece.Color.BLACK) {
      Direction[] WBishop = Direction.WBishopD();
      BishopPos = mpos.findPos(WBishop[0]);
      for (int i = 0; i < WBishop.length; i++) {
        BishopPos = concat(BishopPos, mpos.findPos(WBishop[i]));
      }
      return BishopPos;
    } else {
      Direction[] RBishop = Direction.RBishopD();
      BishopPos = mpos.findPos(RBishop[0]);
      for (int i = 0; i < RBishop.length; i++) {
        BishopPos = concat(BishopPos, mpos.findPos(RBishop[i]));
      }
      return BishopPos;
    }

  }

  public Position[] waysQueenPos(Color color) {
    Position[] QueenPos = new Position[50];
    if (color == GamePiece.Color.WHITE || color == GamePiece.Color.BLACK) {
      Direction[] WQueen = Direction.WAllD();
      QueenPos = mpos.findPos(WQueen[0]);
      for (int i = 0; i < WQueen.length; i++) {
        QueenPos = concat(QueenPos, mpos.findPos(WQueen[i]));
      }
      return QueenPos;
    } else {
      Direction[] RQueen = Direction.RAllD();
      QueenPos = mpos.findPos(RQueen[0]);
      for (int i = 0; i < RQueen.length; i++) {
        QueenPos = concat(QueenPos, mpos.findPos(RQueen[i]));
      }
      return QueenPos;
    }
  }

  public Position[] waysKingPos(Color color) {
    Position KingPos[] = new Position[8];
    if (color == GamePiece.Color.WHITE || color == GamePiece.Color.BLACK) {
      Direction[] WKing = Direction.WAllD();
      for (int i = 0; i < WKing.length; i++) {
        Position oneMovedPos = mpos.moveTo(WKing[i]);
        if (oneMovedPos.isValid()) {
          KingPos[i] = oneMovedPos;
        }
      }
      return KingPos;
    } else {
      Direction[] RKing = Direction.RAllD();
      for (int i = 0; i < RKing.length; i++) {
        Position oneMovedPos = mpos.moveTo(RKing[i]);
        if (oneMovedPos.isValid()) {
          KingPos[i] = oneMovedPos;
        }
      }
      return KingPos;
    }
  }

  public Position[] waysPawnPos(Color color) {
    Position[] PawnPos = new Position[4];
    if (color == GamePiece.Color.WHITE) {
      int WPawnInitialCol = 12;
      Position oneMovedPos = mpos.moveTo(Direction.WN);
      if (oneMovedPos.isValid()) {
        PawnPos[0] = oneMovedPos; // Basic move
      }
      if (mpos.getY() == WPawnInitialCol) {
        PawnPos[1] = oneMovedPos.moveTo(Direction.WN); // When Pawn in starting line
      }
      if (mpos.moveTo(Direction.WNE).isValid()) {
        PawnPos[2] = oneMovedPos.moveTo(Direction.WNE); // diagonal move when there is opposite team's piece
        // it need to add condition opposite teams' piece - 5/28 Jeongwoo
      }
      if (mpos.moveTo(Direction.WNW).isValid()) {
        PawnPos[3] = oneMovedPos.moveTo(Direction.WNW);
      }

      return PawnPos;
    }

    else if (color == GamePiece.Color.BLACK) {
      int BPawnInitialCol = 1;
      Position oneMovedPos = mpos.moveTo(Direction.WS);
      if (oneMovedPos.isValid()) {
        PawnPos[0] = oneMovedPos; // Basic move
      }
      if (mpos.getY() == BPawnInitialCol) {
        PawnPos[1] = oneMovedPos.moveTo(Direction.WS); // When Pawn in starting line
      }
      if (mpos.moveTo(Direction.WSE).isValid()) {
        PawnPos[2] = oneMovedPos.moveTo(Direction.WSE); // diagonal move when there is opposite team's piece
        // it need to add condition opposite teams' piece - 5/28 Jeongwoo
      }
      if (mpos.moveTo(Direction.WSW).isValid()) {
        PawnPos[3] = oneMovedPos.moveTo(Direction.WSW);
      }
      return PawnPos;
    }

    else if (color == GamePiece.Color.RED) {
      int RPawnInitialRow = 1;
      Position oneMovedPos = mpos.moveTo(Direction.RN);
      if (oneMovedPos.isValid()) {
        PawnPos[0] = oneMovedPos; // Basic move
      }
      if (mpos.getY() == RPawnInitialRow) {
        PawnPos[1] = oneMovedPos.moveTo(Direction.RN); // When Pawn in starting line
      }
      if (mpos.moveTo(Direction.RNE).isValid()) {
        PawnPos[2] = oneMovedPos.moveTo(Direction.RNE); // diagonal move when there is opposite team's piece
        // it need to add condition opposite teams' piece - 5/28 Jeongwoo
      }
      if (mpos.moveTo(Direction.RNW).isValid()) {
        PawnPos[3] = oneMovedPos.moveTo(Direction.RNW);
      }

      return PawnPos;
    }

    else {
      int GPawnInitialRow = 12;
      Position oneMovedPos = mpos.moveTo(Direction.RS);
      if (oneMovedPos.isValid()) {
        PawnPos[0] = oneMovedPos; // Basic move
      }
      if (mpos.getY() == GPawnInitialRow) {
        PawnPos[1] = oneMovedPos.moveTo(Direction.RS); // When Pawn in starting line
      }
      if (mpos.moveTo(Direction.RSE).isValid()) {
        PawnPos[2] = oneMovedPos.moveTo(Direction.RSE); // diagonal move when there is opposite team's piece
        // it need to add condition opposite teams' piece - 5/28 Jeongwoo
      }
      if (mpos.moveTo(Direction.RSW).isValid()) {
        PawnPos[3] = oneMovedPos.moveTo(Direction.RSW);
      }

      return PawnPos;
    }
  }

  protected boolean isCheck(King king, Position tilePosition, ChessBoard b) {
    Color[] oppositeColor = new Color[2];
    int sameMoveTile = 0; // If the tile that king can move on is same as parameter tile, it will be 1

    Position[] kingWays = waysKingPos(king.color);
    
    for(int i=0; i<kingWays.length; i++) {
      if (kingWays[i] == tilePosition) {
        sameMoveTile = 1;
      }
    }
    
    if (sameMoveTile == 0) {
      return false; // If the tile location is that King can't go, return false
    }

    if (king.color == Color.WHITE || king.color == GamePiece.Color.BLACK) { 
      oppositeColor[0] = Color.RED;
      oppositeColor[1] = Color.GREEN;
    }
    else {
      oppositeColor[0] = Color.WHITE;
      oppositeColor[1] = Color.BLACK;
    }
    
    Position pos = new Position(0,0);
    GamePiece piece;
    
    ArrayList<Position> allPos = new ArrayList<Position>();
    for (int j=0; j<14; j++) {
      for (int k=0; k<14; k++) {
        if(pos.isValid()) {
          pos.setX(j);
          pos.setY(k);
          piece = SearchPieceByPos.searchPiece(pos, b);
          if (piece != null) {
            if (piece.getColor() == oppositeColor[0] || piece.getColor() == oppositeColor[1]) {      
              allPos.addAll(Arrays.asList(piece.getCanMoves()));
            }
          }
        }
      }
    }
    
    HashSet<Position> hashPos = new HashSet<Position>(allPos);
    ArrayList<Position> allPosResult = new ArrayList<Position>(hashPos);
    Position[] result = allPosResult.toArray(new Position[allPosResult.size()]);
    
    int tilePosX = tilePosition.getX();
    int tilePosY = tilePosition.getY();
    Tile aroundTile= ChessBoard.cBoard[tilePosX][tilePosY];
    
    if (aroundTile.isOnPiece() == false) {
      for(int i=0; i<result.length; i++) {
        if (result[i] == tilePosition) {
          return true;
        }
      }
    }
    return false;
  }

}
