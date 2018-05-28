package piece;

import java.util.ArrayList;

import piece.GamePiece.*;
import piece.Position.Direction;

public class PosManage {
  private Position mpos;
  
  public PosManage(Position mpos) {
    this.mpos = mpos;
  }
  
  public ArrayList<Position> waysRookPos(Color color) {
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
  
  public ArrayList<Position> waysKnightPos(Color color) {
    ArrayList<Position> KnightPos = new ArrayList<Position>();
    if (color == GamePiece.Color.WHITE || color == GamePiece.Color.BLACK) {
      Direction[] WKnight = Direction.WKnightD();
      for (int i=0; i< WKnight.length; i++) {
        Position oneMovedPos = mpos.moveTo(WKnight[i]);
        if(oneMovedPos.isValid()) {
          KnightPos.add(oneMovedPos);
        }
      }
      return KnightPos;
    }
    else {
      Direction[] RKnight = Direction.RKnightD();
      for (int i=0; i< RKnight.length; i++) {
        Position oneMovedPos = mpos.moveTo(RKnight[i]);
        if(oneMovedPos.isValid()) {
          KnightPos.add(oneMovedPos);
        }
      }
      return KnightPos;
    }
  }
  
  public ArrayList<Position> waysBishopPos(Color color) {
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
  
  public ArrayList<Position> waysQueenPos(Color color) {
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
  
  public ArrayList<Position> waysKingPos(Color color) {
    ArrayList<Position> KingPos = new ArrayList<Position>();
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
  
  public ArrayList<Position> waysPawnPos(Color color) {
    ArrayList<Position> PawnPos = new ArrayList<Position>();
    if (color == GamePiece.Color.WHITE) {
      int WPawnInitialCol = 12;
      Position oneMovedPos = mpos.moveTo(Direction.WN);
      if (oneMovedPos.isValid()) {
        PawnPos.add(oneMovedPos);
      }
      if (mpos.getY() == WPawnInitialCol) {
        PawnPos.add(oneMovedPos.moveTo(Direction.WN));
      }
      if (mpos.moveTo(Direction.WNE).isValid()) {
        PawnPos.add(oneMovedPos);
      }
      if (mpos.moveTo(Direction.WNW).isValid()) {
        PawnPos.add(oneMovedPos);
      }
      
      return PawnPos;
    }
    
    else if (color == GamePiece.Color.BLACK){
      int BPawnInitialCol = 1;
      Position oneMovedPos = mpos.moveTo(Direction.WS);
      if (oneMovedPos.isValid()) {
        PawnPos.add(oneMovedPos);
      }
      if (mpos.getY() == BPawnInitialCol) {
        PawnPos.add(oneMovedPos.moveTo(Direction.WS));
      }
      if (mpos.moveTo(Direction.WSE).isValid()) {
        PawnPos.add(oneMovedPos);
      }
      if (mpos.moveTo(Direction.WSW).isValid()) {
        PawnPos.add(oneMovedPos);
      }
      
      return PawnPos;
    }
    
    else if (color == GamePiece.Color.RED) {
      int RPawnInitialRow = 1;
      Position oneMovedPos = mpos.moveTo(Direction.RN);
      if (oneMovedPos.isValid()) {
        PawnPos.add(oneMovedPos);
      }
      if (mpos.getY() == RPawnInitialRow) {
        PawnPos.add(oneMovedPos.moveTo(Direction.RN));
      }
      if (mpos.moveTo(Direction.RNE).isValid()) {
        PawnPos.add(oneMovedPos);
      }
      if (mpos.moveTo(Direction.RNW).isValid()) {
        PawnPos.add(oneMovedPos);
      }
      
      return PawnPos;
    }
      
    else {
      int GPawnInitialRow = 12;
      Position oneMovedPos = mpos.moveTo(Direction.RS);
      if (oneMovedPos.isValid()) {
        PawnPos.add(oneMovedPos);
      }
      if (mpos.getY() == GPawnInitialRow) {
        PawnPos.add(oneMovedPos.moveTo(Direction.RS));
      }
      if (mpos.moveTo(Direction.RSE).isValid()) {
        PawnPos.add(oneMovedPos);
      }
      if (mpos.moveTo(Direction.RSW).isValid()) {
        PawnPos.add(oneMovedPos);
      }
      
      return PawnPos;
    }
  }
  
  
  

  
}
