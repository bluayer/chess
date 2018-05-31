package piece;

import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import board.ChessBoard;
import board.SearchPieceByPos;
import board.Tile;
import chess.ChessGui;
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
  private ChessBoard board = ChessGui.b;

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
    ArrayList <Position> RookPos = new ArrayList<Position>();
    if (color == GamePiece.Color.WHITE || color == GamePiece.Color.BLACK) {
      Direction[] WRook = Direction.WRookD();
      RookPos = mpos.findPos(WRook[0], color);
      for (int i = 1; i < WRook.length; i++) {
        RookPos.addAll(mpos.findPos(WRook[i], color));
      }
      
      if (RookPos.size() != 0) {
        for(int k=0; k< RookPos.size(); k++) {
          if(RookPos.get(k).getX() == 0 && RookPos.get(k).getY()==0) {
            RookPos.remove(k);
          }
        }
      }

      Position[] Wresult = RookPos.toArray(new Position[RookPos.size()]);
      return Wresult;
    } 
    else {
      Direction[] RRook = Direction.RRookD();
      RookPos = mpos.findPos(RRook[0], color);
      for (int i = 1; i < RRook.length; i++) {
        RookPos.addAll(mpos.findPos(RRook[i], color));
      }
      
      if (RookPos.size() != 0) {
        for(int k=0; k< RookPos.size(); k++) {
          if(RookPos.get(k).getX() == 0 && RookPos.get(k).getY()==0) {
            RookPos.remove(k);
          }
        }
      }
      
      Position[] Rresult = RookPos.toArray(new Position[RookPos.size()]);
      return Rresult;
    }
    

  }

  public Position[] waysKnightPos(Color color) {
    ArrayList<Position> KnightPos = new ArrayList<Position>();
    if (color == GamePiece.Color.WHITE || color == GamePiece.Color.BLACK) {
      Direction[] WKnight = Direction.WKnightD();
      for (int i = 0; i < WKnight.length; i++) {

        Position oneMovedPos = mpos.moveTo(WKnight[i]);

        if (oneMovedPos.isValid()) {
          int tileX = oneMovedPos.getX();
          int tileY = oneMovedPos.getY();
          Tile tile = board.getcBoard()[tileX][tileY];
          if (tile.isOnPiece() == false) {
            KnightPos.add(oneMovedPos);
          }
          else {
            if(SearchPieceByPos.searchPiece(oneMovedPos, ChessGui.b).getColor() == Color.RED
                || SearchPieceByPos.searchPiece(oneMovedPos, ChessGui.b).getColor() == Color.GREEN) {
              KnightPos.add(oneMovedPos);
            }
          }
        }
      }
      Position[] Wresult = KnightPos.toArray(new Position[KnightPos.size()]);
      return Wresult;
      
    } else {
      Direction[] RKnight = Direction.RKnightD();
      for (int i = 0; i < RKnight.length; i++) {
        Position oneMovedPos = mpos.moveTo(RKnight[i]);
        if (oneMovedPos.isValid()) {
          int tileX = oneMovedPos.getX();
          int tileY = oneMovedPos.getY();
          Tile tile = board.getcBoard()[tileX][tileY];
          if (tile.isOnPiece() == false) {
            KnightPos.add(oneMovedPos);
          }
          else {
            if(SearchPieceByPos.searchPiece(oneMovedPos, ChessGui.b).getColor() == Color.BLACK
                || SearchPieceByPos.searchPiece(oneMovedPos, ChessGui.b).getColor() == Color.WHITE) {
              KnightPos.add(oneMovedPos);
            }
          }
        }
      }
      Position[] Rresult = KnightPos.toArray(new Position[KnightPos.size()]);
      return Rresult;
    }
  }

  public Position[] waysBishopPos(Color color) {
    ArrayList <Position> BishopPos = new ArrayList<Position>();
    if (color == GamePiece.Color.WHITE || color == GamePiece.Color.BLACK) {
      Direction[] WBishop = Direction.WBishopD();
      BishopPos = mpos.findPos(WBishop[0], color);
      for (int i = 1; i < WBishop.length; i++) {
        BishopPos.addAll(mpos.findPos(WBishop[i], color));
      }
      if (BishopPos.size() != 0) {
        for(int k=0; k< BishopPos.size(); k++) {
          if(BishopPos.get(k).getX() == 0 && BishopPos.get(k).getY()==0) {
            BishopPos.remove(k);
          }
        }
      }
      
      Position[] Wresult = BishopPos.toArray(new Position[BishopPos.size()]);
      return Wresult;
    } else {
      Direction[] RBishop = Direction.RBishopD();
      BishopPos = mpos.findPos(RBishop[0], color);
      for (int i = 1; i < RBishop.length; i++) {
        BishopPos.addAll(mpos.findPos(RBishop[i], color));
      }
      
      if (BishopPos.size() != 0) {
        for(int k=0; k< BishopPos.size(); k++) {
          if(BishopPos.get(k).getX() == 0 && BishopPos.get(k).getY()==0) {
            BishopPos.remove(k);
          }
        }
      }
      
      Position[] Rresult = BishopPos.toArray(new Position[BishopPos.size()]);
      return Rresult;
    }
  }


  public Position[] waysQueenPos(Color color) {
    ArrayList <Position> QueenPos = new ArrayList<Position>();
    if (color == GamePiece.Color.WHITE || color == GamePiece.Color.BLACK) {
      Direction[] WQueen = Direction.WAllD();
      QueenPos = mpos.findPos(WQueen[0], color);
      for (int i = 1; i < WQueen.length; i++) {
        QueenPos.addAll(mpos.findPos(WQueen[i], color));
      }
      
      if (QueenPos.size() != 0) {
        for(int k=0; k< QueenPos.size(); k++) {
          if(QueenPos.get(k).getX() == 0 && QueenPos.get(k).getY()==0) {
            QueenPos.remove(k);
          }
        }
      }
      
      Position[] Wresult = QueenPos.toArray(new Position[QueenPos.size()]);
      return Wresult;
    } else {
      Direction[] RQueen = Direction.RAllD();
      QueenPos = mpos.findPos(RQueen[0], color);
      for (int i = 0; i < RQueen.length; i++) {
        QueenPos.addAll(mpos.findPos(RQueen[i], color));
      }
      
      if (QueenPos.size() != 0) {
        for(int k=0; k< QueenPos.size(); k++) {
          if(QueenPos.get(k).getX() == 0 && QueenPos.get(k).getY()==0) {
            QueenPos.remove(k);
          }
        }

      }
      
      HashSet<Position> dubData = new HashSet<Position>(QueenPos);
      ArrayList<Position> QueenPosDub = new ArrayList<Position>(dubData);
      
      
      Position[] Rresult = QueenPosDub.toArray(new Position[QueenPosDub.size()]);
      return Rresult;
    }
  }

  public Position[] waysKingPos(Color color) {
    ArrayList<Position> KingPos = new ArrayList<Position>();
    if (color == GamePiece.Color.WHITE || color == GamePiece.Color.BLACK) {
      Direction[] WKing = Direction.WAllD();
      for (int i = 0; i < WKing.length; i++) {
        Position oneMovedPos = mpos.moveTo(WKing[i]);
        if (oneMovedPos.isValid()) {
          int tileX = oneMovedPos.getX();
          int tileY = oneMovedPos.getY();
          Tile tile = board.getcBoard()[tileX][tileY];
          if (tile.isOnPiece() == false) {
            KingPos.add(oneMovedPos);
          }
          else {
            if(SearchPieceByPos.searchPiece(oneMovedPos, ChessGui.b).getColor() == Color.RED
                || SearchPieceByPos.searchPiece(oneMovedPos, ChessGui.b).getColor() == Color.GREEN) {
              KingPos.add(oneMovedPos);
            }
          }
        }
        
      }
      
      Position[] Wresult = KingPos.toArray(new Position[KingPos.size()]);
      return Wresult;
      
    } 
    else {
      Direction[] RKing = Direction.RAllD();
      for (int i = 0; i < RKing.length; i++) {
        Position oneMovedPos = mpos.moveTo(RKing[i]);
        if (oneMovedPos.isValid()) {
          int tileX = oneMovedPos.getX();
          int tileY = oneMovedPos.getY();
          Tile tile = board.getcBoard()[tileX][tileY];
          if (tile.isOnPiece() == false) {
            KingPos.add(oneMovedPos);
          }
          else {
            if(SearchPieceByPos.searchPiece(oneMovedPos, ChessGui.b).getColor() == Color.BLACK
                || SearchPieceByPos.searchPiece(oneMovedPos, ChessGui.b).getColor() == Color.WHITE) {
              KingPos.add(oneMovedPos);
            }
          }
        }
      }
      
      Position[] Rresult = KingPos.toArray(new Position[KingPos.size()]);
      return Rresult;
    }
  }

 

  public Position[] waysPawnPos(Color color) {
    ArrayList<Position> PawnPos = new ArrayList<Position>();
    
    int x = mpos.getX();
    int y = mpos.getY();
    
    if (color == GamePiece.Color.WHITE) {
      int WPawnInitialCol = 12;
      Position oneMovedPos = mpos;
      if (oneMovedPos.isValid()) {
        int WtileX = oneMovedPos.getX()-1;
        int WtileY = oneMovedPos.getY();
        if(oneMovedPos.moveTo(Direction.WN).isValid()) {
          Tile Wtile = board.getcBoard()[WtileX][WtileY];
  
          if (Wtile.isOnPiece() == false) {
            oneMovedPos = oneMovedPos.moveTo(Direction.WN);
            PawnPos.add(oneMovedPos); // Basic move
          }
          
          if (mpos.getX() == WPawnInitialCol) {
            if(Wtile.isOnPiece() == false ) {
              WtileX -= 1;
              Wtile = board.getcBoard()[WtileX][WtileY];
              if(Wtile.isOnPiece() == false ) {
                if (oneMovedPos.moveTo(Direction.WN).isValid()) {
                  oneMovedPos = oneMovedPos.moveTo(Direction.WN);
                  PawnPos.add(oneMovedPos); // When Pawn in starting line
                }
              }
            }
          }
          
          oneMovedPos = mpos;
         
          if (mpos.moveTo(Direction.WNE).isValid()) {
            Tile WdiagonalTile1 = board.getcBoard()[oneMovedPos.getX()-1][oneMovedPos.getY()+1]; // WNE Tile
            if(WdiagonalTile1.isOnPiece() == true) {
              if (SearchPieceByPos.searchPiece(oneMovedPos.moveTo(Direction.WNE), board).getColor() == Color.RED 
                  || SearchPieceByPos.searchPiece(oneMovedPos.moveTo(Direction.WNE), board).getColor() == Color.GREEN ) {
                PawnPos.add(oneMovedPos.moveTo(Direction.WNE));
              } 
            }
          }
          
          if (mpos.moveTo(Direction.WNW).isValid()) {
            Tile WdiagonalTile2 = board.getcBoard()[oneMovedPos.getX()-1][oneMovedPos.getY()-1]; // WNW Tile
            if(WdiagonalTile2.isOnPiece() == true) {
              if (SearchPieceByPos.searchPiece(oneMovedPos.moveTo(Direction.WNW), board).getColor() == Color.RED 
                  || SearchPieceByPos.searchPiece(oneMovedPos.moveTo(Direction.WNW), board).getColor() == Color.GREEN) {
                PawnPos.add(oneMovedPos.moveTo(Direction.WNW));
              }
            }
          }
        }
      }
      Position[] Wresult = PawnPos.toArray(new Position[PawnPos.size()]);
      return Wresult;
    }

    else if (color == GamePiece.Color.BLACK) {
      int BPawnInitialCol = 1;
      Position oneMovedPos2 = mpos;
      if (oneMovedPos2.isValid()) {
        int BtileX = oneMovedPos2.getX()+1;
        int BtileY = oneMovedPos2.getY();
        if (oneMovedPos2.moveTo(Direction.WS).isValid()) {
          Tile Btile = board.getcBoard()[BtileX][BtileY];
          if (Btile.isOnPiece() == false) {
            oneMovedPos2 = oneMovedPos2.moveTo(Direction.WS);
            PawnPos.add(oneMovedPos2);
          }
          if (mpos.getX() == BPawnInitialCol) {
            if(Btile.isOnPiece() == false ) {
              BtileX += 1;
              Btile = board.getcBoard()[BtileX][BtileY];
              if(Btile.isOnPiece() == false ) {
                if (oneMovedPos2.moveTo(Direction.WS).isValid()) {
                  oneMovedPos2 = oneMovedPos2.moveTo(Direction.WS);
                  PawnPos.add(oneMovedPos2); // When Pawn in starting line
                }
              }
            }
          }
        
          oneMovedPos2 = mpos;

          if (mpos.moveTo(Direction.WSE).isValid()) {
            Tile BdiagonalTile1 = board.getcBoard()[oneMovedPos2.getX()+1][oneMovedPos2.getY()+1]; // WSE Tile
            if(BdiagonalTile1.isOnPiece() == true) {
              if (SearchPieceByPos.searchPiece(oneMovedPos2.moveTo(Direction.WSE), board).getColor() == Color.RED 
                  || SearchPieceByPos.searchPiece(oneMovedPos2.moveTo(Direction.WSE), board).getColor() == Color.GREEN) {
                PawnPos.add(oneMovedPos2.moveTo(Direction.WSE));
              } 
            }
          }
            
          if (mpos.moveTo(Direction.WSW).isValid()) {
            Tile BdiagonalTile2 = board.getcBoard()[oneMovedPos2.getX()+1][oneMovedPos2.getY()-1]; // WSW Tile
            if(BdiagonalTile2.isOnPiece() == true) {
              if (SearchPieceByPos.searchPiece(oneMovedPos2.moveTo(Direction.WSW), board).getColor() == Color.RED 
                  || SearchPieceByPos.searchPiece(oneMovedPos2.moveTo(Direction.WSW), board).getColor() == Color.GREEN) {            
                PawnPos.add(oneMovedPos2.moveTo(Direction.WSW));
              }
            }
          }
        }
      }

      Position[] Bresult = PawnPos.toArray(new Position[PawnPos.size()]);
      return Bresult;
    }
      
    else if (color == GamePiece.Color.RED) {
      int RPawnInitialCol = 1;
      Position oneMovedPos3 = mpos;
      if (oneMovedPos3.isValid()) {
        int RtileX = oneMovedPos3.getX();
        int RtileY = oneMovedPos3.getY()+1;
        if(oneMovedPos3.moveTo(Direction.RN).isValid()) {
          Tile Rtile = board.getcBoard()[RtileX][RtileY];
          if (Rtile.isOnPiece() == false) {
            oneMovedPos3 = oneMovedPos3.moveTo(Direction.RN);
            PawnPos.add(oneMovedPos3);
          }
          if (mpos.getY() == RPawnInitialCol) {
            if(Rtile.isOnPiece() == false ) {
              RtileY += 1;
              Rtile = board.getcBoard()[RtileX][RtileY];
              if(Rtile.isOnPiece() == false ) {
                if (oneMovedPos3.moveTo(Direction.RN).isValid()) {
                  oneMovedPos3 = oneMovedPos3.moveTo(Direction.RN);
                  PawnPos.add(oneMovedPos3); // When Pawn in starting line
                }
              }
            }
          }
        
          oneMovedPos3 = mpos;
      
          if (mpos.moveTo(Direction.RNE).isValid()) {
            Tile RdiagonalTile1 = board.getcBoard()[oneMovedPos3.getX()+1][oneMovedPos3.getY()+1];  // RNE Tile
            if(RdiagonalTile1.isOnPiece() == true) {
            if (SearchPieceByPos.searchPiece(mpos.moveTo(Direction.RNE), board).getColor() == Color.WHITE 
                || SearchPieceByPos.searchPiece(mpos.moveTo(Direction.RNE), board).getColor() == Color.BLACK) {         
                PawnPos.add(oneMovedPos3.moveTo(Direction.RNE));
              } 
            }
          }
              
          if (mpos.moveTo(Direction.RNW).isValid()) {
            Tile RdiagonalTile2 = board.getcBoard()[oneMovedPos3.getX()-1][oneMovedPos3.getY()+1]; // RNW Tile
            if(RdiagonalTile2.isOnPiece() == true) {
              if (SearchPieceByPos.searchPiece(mpos.moveTo(Direction.RNW), board).getColor() == Color.WHITE 
                  || SearchPieceByPos.searchPiece(mpos.moveTo(Direction.RNW), board).getColor() == Color.BLACK) {        
                PawnPos.add(oneMovedPos3.moveTo(Direction.RNW));
              }
            }
          }
        }
      }
        
      Position[] Rresult = PawnPos.toArray(new Position[PawnPos.size()]);
      return Rresult;
    }
      
    else {
      int GPawnInitialCol = 12;
      Position oneMovedPos4 = mpos;
      if (oneMovedPos4.isValid()) {
        int GtileX = oneMovedPos4.getX();
        int GtileY = oneMovedPos4.getY()-1;
        if(oneMovedPos4.moveTo(Direction.RS).isValid()) {
          Tile Gtile = board.getcBoard()[GtileX][GtileY];
          if (Gtile.isOnPiece() == false) {
            oneMovedPos4 = oneMovedPos4.moveTo(Direction.RS);
            PawnPos.add(oneMovedPos4);
          }
          if (mpos.getY() == GPawnInitialCol) {
            if(Gtile.isOnPiece() == false ) {
              GtileY -= 1;
              Gtile = board.getcBoard()[GtileX][GtileY];
              if(Gtile.isOnPiece() == false ) {
                if (oneMovedPos4.moveTo(Direction.RS).isValid()) {
                  oneMovedPos4 = oneMovedPos4.moveTo(Direction.RS);
                  PawnPos.add(oneMovedPos4); // When Pawn in starting line
                }
              }
            }
          }
        
          oneMovedPos4 = mpos;

          if (mpos.moveTo(Direction.RSE).isValid()) {
            Tile GdiagonalTile1 = board.getcBoard()[oneMovedPos4.getX()+1][oneMovedPos4.getY()-1]; // RSE Tile
            if(GdiagonalTile1.isOnPiece() == true) {
              if (SearchPieceByPos.searchPiece(mpos.moveTo(Direction.RSE), board).getColor() == Color.WHITE 
                  || SearchPieceByPos.searchPiece(mpos.moveTo(Direction.RSE), board).getColor() == Color.BLACK) {
                PawnPos.add(oneMovedPos4.moveTo(Direction.RSE));
              } 
            }
          }
            
          if (mpos.moveTo(Direction.RSW).isValid()) {
            Tile GdiagonalTile2 = board.getcBoard()[oneMovedPos4.getX()-1][oneMovedPos4.getY()-1]; // RSW Tile
            if(GdiagonalTile2.isOnPiece() == true) {
              if (SearchPieceByPos.searchPiece(mpos.moveTo(Direction.RSW), board).getColor() == Color.WHITE 
                  || SearchPieceByPos.searchPiece(mpos.moveTo(Direction.RSW), board).getColor() == Color.BLACK) {
                PawnPos.add(oneMovedPos4.moveTo(Direction.RSW));
              }
            }
          }
        }
      }

      Position[] Gresult = PawnPos.toArray(new Position[PawnPos.size()]);
      return Gresult;
    }
  }

  public boolean isCheck(Color color) {
    
    Color oppositeColor1 = null, oppositeColor2 = null;
    
    switch(color) {
    case BLACK:
      oppositeColor1 = Color.RED;
      oppositeColor2 = Color.GREEN;
      break;
    case WHITE:
      oppositeColor1 = Color.RED;
      oppositeColor2 = Color.GREEN;
      break;
    case RED:
      oppositeColor1 = Color.BLACK;
      oppositeColor2 = Color.WHITE;
      break;
    case GREEN:
      oppositeColor1 = Color.RED;
      oppositeColor2 = Color.WHITE;
      break;
    default:
    }
    
    ArrayList<Position> kingPos = new ArrayList<Position>(Arrays.asList(waysKingPos(color)));
    ArrayList<Position> oppositeAll = new ArrayList<Position>();
    Position nowPos = new Position(0 , 0);
    for(int i = 0; i < 14; i++) {
      for(int j = 0; j < 14; j++) {
        nowPos.setX(i);
        nowPos.setY(j);
        
        if(board.getcBoard()[i][j].getActive() == true) {
          if(board.getcBoard()[i][j].isOnPiece() == true) {
            if (SearchPieceByPos.searchPiece(nowPos, board).getPieceType() != PieceType.KING) {
              GamePiece piece = SearchPieceByPos.searchPiece(nowPos, board);
              if (piece.getColor() == oppositeColor1 || piece.getColor() == oppositeColor2) {
                if(piece.getCanMoves() != null) {
                  if(piece.getPieceType() == PieceType.PAWN) { // pawn diagonal exception
                    ArrayList<Position> PawnPos = new ArrayList<Position>(Arrays.asList(waysPawnPos(piece.getColor())));
                    Position diagonalMove1 = piece.getPosition();
                    Position diagonalMove2 = piece.getPosition();
                    if (piece.isWhite()) {
                      diagonalMove1 = diagonalMove1.moveTo(Direction.WNE);
                      diagonalMove2 = diagonalMove2.moveTo(Direction.WNW);
                    } 
                    else if (piece.isBlack()) {
                      diagonalMove1 = diagonalMove1.moveTo(Direction.WSE);
                      diagonalMove2 = diagonalMove2.moveTo(Direction.WSW);
                    }
                    else if(piece.isRed()) {
                      diagonalMove1 = diagonalMove1.moveTo(Direction.RNE);
                      diagonalMove2 = diagonalMove2.moveTo(Direction.RNW);
                    }
                    else {
                      diagonalMove1 = diagonalMove1.moveTo(Direction.RSE);
                      diagonalMove2 = diagonalMove2.moveTo(Direction.RSW);
                    }
                    
                    PawnPos.add(diagonalMove1);
                    PawnPos.add(diagonalMove2);

                    for(int k=0; k < PawnPos.size(); k++) {
                      for (int l=0; l<kingPos.size(); l++) {
                        if (PawnPos.get(k).getX() == kingPos.get(l).getX() &&
                            PawnPos.get(k).getY() == kingPos.get(l).getY()) {
                          return true;
                        }
                      }
                    }
                  }
                  for(int k=0; k < piece.getCanMoves().length; k++) {
                    for (int l=0; l<kingPos.size(); l++) {
                      if (piece.getCanMoves()[k].getX() == kingPos.get(l).getX() &&
                          piece.getCanMoves()[k].getY() == kingPos.get(l).getY()) {
                        return true;
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }

    return false;
  }
  
  public Position[] waysKingPosCheck(Color color) {
   
    Color oppositeColor1 = null, oppositeColor2 = null;
    
    switch(color) {
    case BLACK:
      oppositeColor1 = Color.RED;
      oppositeColor2 = Color.GREEN;
      break;
    case WHITE:
      oppositeColor1 = Color.RED;
      oppositeColor2 = Color.GREEN;
      break;
    case RED:
      oppositeColor1 = Color.BLACK;
      oppositeColor2 = Color.WHITE;
      break;
    case GREEN:
      oppositeColor1 = Color.RED;
      oppositeColor2 = Color.WHITE;
      break;
    default:
    }
    
    ArrayList<Position> kingPos = new ArrayList<Position>(Arrays.asList(waysKingPos(color)));
    ArrayList<Position> oppositeAll = new ArrayList<Position>();
    Position nowPos = new Position(0 , 0);
    for(int i = 0; i < 14; i++) {
      for(int j = 0; j < 14; j++) {
        nowPos.setX(i);
        nowPos.setY(j);
        
        if(board.getcBoard()[i][j].getActive() == true) {
          if(board.getcBoard()[i][j].isOnPiece() == true) {
            if (SearchPieceByPos.searchPiece(nowPos, board).getPieceType() != PieceType.KING) {
              GamePiece piece = SearchPieceByPos.searchPiece(nowPos, board);
              if (piece.getColor() == oppositeColor1 || piece.getColor() == oppositeColor2) {
                if(piece.getCanMoves() != null) {
                    if(piece.getPieceType() == PieceType.PAWN) { // pawn diagonal exception
                      ArrayList<Position> PawnPos = new ArrayList<Position>();
                      Position diagonalMove1 = piece.getPosition();
                      Position diagonalMove2 = piece.getPosition();
                      
                      if (piece.isWhite()) {
                        diagonalMove1 = diagonalMove1.moveTo(Direction.WNE);
                        diagonalMove2 = diagonalMove2.moveTo(Direction.WNW);
                      } 
                      else if (piece.isBlack()) {
                        diagonalMove1 = diagonalMove1.moveTo(Direction.WSE);
                        diagonalMove2 = diagonalMove2.moveTo(Direction.WSW);
                      }
                      else if(piece.isRed()) {
                        diagonalMove1 = diagonalMove1.moveTo(Direction.RNE);
                        diagonalMove2 = diagonalMove2.moveTo(Direction.RNW);
                      }
                      else if(piece.isGreen()){
                        diagonalMove1 = diagonalMove1.moveTo(Direction.RSE);
                        diagonalMove2 = diagonalMove2.moveTo(Direction.RSW);
                      }
                      
                      PawnPos.add(diagonalMove1);
                      PawnPos.add(diagonalMove2);
                      for(int k=0; k < PawnPos.size(); k++) {
                        for (int l=0; l<kingPos.size(); l++) {
                          if (PawnPos.get(k).getX() == kingPos.get(l).getX() &&
                              PawnPos.get(k).getY() == kingPos.get(l).getY()) {
                            kingPos.remove(l);
                          }
                        }
                      }
                    }
                    else {
                    for(int f=0; f < piece.getCanMoves().length; f++) {
                      for (int e=0; e<kingPos.size(); e++) {
                        if (piece.getCanMoves()[f].getX() == kingPos.get(e).getX() &&
                            piece.getCanMoves()[f].getY() == kingPos.get(e).getY()) {
                          kingPos.remove(e);
                        }
                      }
                    }
                  }
                }  
              }
            }
          }
        }
      }
    }

    Position[] result = kingPos.toArray(new Position[kingPos.size()]);
    return result;
  }
}
