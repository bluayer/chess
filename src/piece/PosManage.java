package piece;

import java.util.ArrayList;
import java.util.List;

import piece.GamePiece.*;
import piece.Position.Direction;

public class PosManage {
  private Position mpos;
  
  public PosManage(Position mpos) {
    this.mpos = mpos;
  }
  
  public List<Position> waysRookPos(Color color) {
    ArrayList<Position> RookPos = new ArrayList<Position>();
    if (color == GamePiece.Color.WHITE || color == GamePiece.Color.BLACK) {
      Direction[] WRook = Direction.WRookD();
      for (int i=0; i< WRook.length; i++) {
        RookPos.addAll(mpos.findPos(WRook[i]));
      }
      return RookPos;
    }
    else {
      Direction[] RRook = Direction.RRookD();
      for (int i=0; i< RRook.length; i++) {
        RookPos.addAll(mpos.findPos(RRook[i]));
      }
      return RookPos;
    }
      
  }
  
  public List<Position> waysKnightPos(Color color) {
    ArrayList<Position> KnightPos = new ArrayList<Position>();
    if (color == GamePiece.Color.WHITE || color == GamePiece.Color.BLACK) {
      Direction[] WKnight = Direction.WKnightD();
      for (int i=0; i< WKnight.length; i++) {
        KnightPos.addAll(mpos.findPos(WKnight[i]));
      }
      return KnightPos;
    }
    else {
      Direction[] RKnight = Direction.RKnightD();
      for (int i=0; i< RKnight.length; i++) {
        KnightPos.addAll(mpos.findPos(RKnight[i]));
      }
      return KnightPos;
    }
  }
  
  public List<Position> waysBishopPos(Color color) {
    ArrayList<Position> BishopPos = new ArrayList<Position>();
    if (color == GamePiece.Color.WHITE || color == GamePiece.Color.BLACK) {
      Direction[] WBishop = Direction.WBishopD();
      for (int i=0; i< WBishop.length; i++) {
        BishopPos.addAll(mpos.findPos(WBishop[i]));
      }
      return BishopPos;
    }
    else {
      Direction[] RBishop = Direction.RBishopD();
      for (int i=0; i< RBishop.length; i++) {
        BishopPos.addAll(mpos.findPos(RBishop[i]));
      }
      return BishopPos;
    }
      
  }
  
  public List<Position> waysQueenPos(Color color) {
    ArrayList<Position> QueenPos = new ArrayList<Position>();
    if (color == GamePiece.Color.WHITE || color == GamePiece.Color.BLACK) {
      Direction[] WQueen = Direction.WAllD();
      for (int i=0; i< WQueen.length; i++) {
        QueenPos.addAll(mpos.findPos(WQueen[i]));
      }
      return QueenPos;
    }
    else {
      Direction[] RQueen = Direction.RAllD();
      for (int i=0; i< RQueen.length; i++) {
        QueenPos.addAll(mpos.findPos(RQueen[i]));
      }
      return QueenPos;
    }  
  }
  
  public List<Position> waysKingPos(Color color) {
    List<Position> KingPos = new ArrayList<Position>();
    if (color == GamePiece.Color.WHITE || color == GamePiece.Color.BLACK) {
      Direction[] WKing = Direction.WAllD();
      for (int i=0; i < WKing.length; i++) {
        Position oneMovedPos = mpos.moveTo(WKing[i]);
        if(oneMovedPos.isValid()) {
          KingPos.add(oneMovedPos);
        }
      }
      return KingPos;
    }
    else {
      Direction[] RKing = Direction.RAllD();
      for (int i=0; i< RKing.length; i++) {
        Position oneMovedPos = mpos.moveTo(RKing[i]);
        if(oneMovedPos.isValid()) {
          KingPos.add(oneMovedPos);
        }
      }
      return KingPos;
    }
  }
  

  
}
