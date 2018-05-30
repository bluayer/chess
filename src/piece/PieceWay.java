package piece;

import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import ChangminYi.ChessBoard;
import ChangminYi.SearchPieceByPos;
import ChangminYi.Tile;
import chess.ChessGui;
import piece.GamePiece.Color;
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
    Position[] RookPos = new Position[30];
    for(int i=0; i<RookPos.length; i++) {
      RookPos[i] = new Position(0, 0);
    }
    if (color == GamePiece.Color.WHITE || color == GamePiece.Color.BLACK) {
      Direction[] WRook = Direction.WRookD();
      RookPos = mpos.findPos(WRook[0], color);
      for (int i = 0; i < WRook.length; i++) {
        RookPos = concat(RookPos, mpos.findPos(WRook[i], color));
      }
      return RookPos;
    } else {
      Direction[] RRook = Direction.RRookD();
      RookPos = mpos.findPos(RRook[0], color);
      for (int i = 0; i < RRook.length; i++) {
        RookPos = concat(RookPos, mpos.findPos(RRook[i], color));
      }
      return RookPos;
    }

  }

  public Position[] waysKnightPos(Color color) {
    Position[] KnightPos = new Position[8];
    for(int i=0; i<KnightPos.length; i++) {
      KnightPos[i] = new Position(0, 0);
    }
    if (color == GamePiece.Color.WHITE || color == GamePiece.Color.BLACK) {
      Direction[] WKnight = Direction.WKnightD();
      for (int i = 0; i < WKnight.length; i++) {
        Position oneMovedPos = mpos.moveTo(WKnight[i]);
        if (oneMovedPos.isValid()) {
          int tileX = oneMovedPos.getX();
          int tileY = oneMovedPos.getY();
          Tile tile = board.cBoard[tileX][tileY];
          if (tile.isOnPiece() == false) {
            KnightPos[i] = oneMovedPos;
          }
          else {
            if(SearchPieceByPos.searchPiece(oneMovedPos, ChessGui.b).getColor() == Color.RED
                || SearchPieceByPos.searchPiece(oneMovedPos, ChessGui.b).getColor() == Color.GREEN) {
              KnightPos[i] = oneMovedPos;
            }
          }
        }
      }
      return KnightPos;
    } else {
      Direction[] RKnight = Direction.RKnightD();
      for (int i = 0; i < RKnight.length; i++) {
        Position oneMovedPos = mpos.moveTo(RKnight[i]);
        if (oneMovedPos.isValid()) {
          int tileX = oneMovedPos.getX();
          int tileY = oneMovedPos.getY();
          Tile tile = board.cBoard[tileX][tileY];
          if (tile.isOnPiece() == false) {
            KnightPos[i] = oneMovedPos;
          }
          else {
            if(SearchPieceByPos.searchPiece(oneMovedPos, ChessGui.b).getColor() == Color.BLACK
                || SearchPieceByPos.searchPiece(oneMovedPos, ChessGui.b).getColor() == Color.WHITE) {
              KnightPos[i] = oneMovedPos;
            }
          }
        }
      }
      return KnightPos;
    }
  }

  public Position[] waysBishopPos(Color color) {
    Position[] BishopPos = new Position[22];
    for(int i=0; i<BishopPos.length; i++) {
      BishopPos[i] = new Position(0, 0);
    }
    if (color == GamePiece.Color.WHITE || color == GamePiece.Color.BLACK) {
      Direction[] WBishop = Direction.WBishopD();
      BishopPos = mpos.findPos(WBishop[0], color);
      for (int i = 0; i < WBishop.length; i++) {
        BishopPos = concat(BishopPos, mpos.findPos(WBishop[i], color));
      }
      return BishopPos;
    } else {
      Direction[] RBishop = Direction.RBishopD();
      BishopPos = mpos.findPos(RBishop[0], color);
      for (int i = 0; i < RBishop.length; i++) {
        BishopPos = concat(BishopPos, mpos.findPos(RBishop[i], color));
      }
      return BishopPos;
    }

  }

  public Position[] waysQueenPos(Color color) {
    Position[] QueenPos = new Position[50];
    for(int i=0; i<QueenPos.length; i++) {
      QueenPos[i] = new Position(0, 0);
    }
    if (color == GamePiece.Color.WHITE || color == GamePiece.Color.BLACK) {
      Direction[] WQueen = Direction.WAllD();
      QueenPos = mpos.findPos(WQueen[0], color);
      for (int i = 0; i < WQueen.length; i++) {
        QueenPos = concat(QueenPos, mpos.findPos(WQueen[i], color));
      }
      return QueenPos;
    } else {
      Direction[] RQueen = Direction.RAllD();
      QueenPos = mpos.findPos(RQueen[0], color);
      for (int i = 0; i < RQueen.length; i++) {
        QueenPos = concat(QueenPos, mpos.findPos(RQueen[i], color));
      }
      return QueenPos;
    }
  }

  public Position[] waysKingPos(Color color) {
    Position KingPos[] = new Position[8];
    for(int i=0; i<KingPos.length; i++) {
      KingPos[i] = new Position(0, 0);
    }
    if (color == GamePiece.Color.WHITE || color == GamePiece.Color.BLACK) {
      Direction[] WKing = Direction.WAllD();
      for (int i = 0; i < WKing.length; i++) {
        Position oneMovedPos = mpos.moveTo(WKing[i]);
        if (oneMovedPos.isValid()) {
          int tileX = oneMovedPos.getX();
          int tileY = oneMovedPos.getY();
          Tile tile = board.cBoard[tileX][tileY];
          if (tile.isOnPiece() == false) {
            KingPos[i] = oneMovedPos;
          }
          else {
            if(SearchPieceByPos.searchPiece(oneMovedPos, ChessGui.b).getColor() == Color.RED
                || SearchPieceByPos.searchPiece(oneMovedPos, ChessGui.b).getColor() == Color.GREEN) {
              KingPos[i] = oneMovedPos;
            }
          }
        }
      }
      return KingPos;
    } else {
      Direction[] RKing = Direction.RAllD();
      for (int i = 0; i < RKing.length; i++) {
        Position oneMovedPos = mpos.moveTo(RKing[i]);
        if (oneMovedPos.isValid()) {
          int tileX = oneMovedPos.getX();
          int tileY = oneMovedPos.getY();
          Tile tile = board.cBoard[tileX][tileY];
          if (tile.isOnPiece() == false) {
            KingPos[i] = oneMovedPos;
          }
          else {
            if(SearchPieceByPos.searchPiece(oneMovedPos, ChessGui.b).getColor() == Color.BLACK
                || SearchPieceByPos.searchPiece(oneMovedPos, ChessGui.b).getColor() == Color.WHITE) {
              KingPos[i] = oneMovedPos;
            }
          }
        }
      }
      
      return KingPos;
    }
  }

 

  public Position[] waysPawnPos(Color color) {
    Position[] PawnPos = new Position[4];
    for(int i=0; i<PawnPos.length; i++) {
      PawnPos[i] = new Position(0, 0);
    }
    int x = mpos.getX();
    int y = mpos.getY();
    
    if (color == GamePiece.Color.WHITE) {
      int WPawnInitialCol = 12;
      Position oneMovedPos = mpos;
      if (oneMovedPos.isValid()) {
        int WtileX = oneMovedPos.getX()+1;
        int WtileY = oneMovedPos.getY();
        Tile Wtile = board.cBoard[WtileX][WtileY];
        if (Wtile.isOnPiece() == false) {
          PawnPos[0] = oneMovedPos.moveTo(Direction.WN); // Basic move
        }
        if (mpos.getX() == WPawnInitialCol) {
          if(Wtile.isOnPiece() == false ) {
            WtileX += 1;
            if(Wtile.isOnPiece() == false ) {
              PawnPos[1] = oneMovedPos.moveTo(Direction.WN); // When Pawn in starting line
            }
          }
        }
      }

      if (oneMovedPos.isValid() && oneMovedPos == mpos) {
        int WdiagonalTileX1 = oneMovedPos.getX()-1; // WNE
        int WdiagonalTileY1 = oneMovedPos.getY()+1; // WNE
        Position dOneMovedPos1 = new Position(x-1, y+1); // WNE Position
        Position dOneMovedPos2 = new Position(x-1, y-1); // WNW Position
        int WdiagonalTileX2 = oneMovedPos.getX()-1; // WNW
        int WdiagonalTileY2 = oneMovedPos.getY()-1; // WNW
        
        Tile WdiagonalTile1 = board.cBoard[WdiagonalTileX1][WdiagonalTileY1]; // WNE Tile
        Tile WdiagonalTile2 = board.cBoard[WdiagonalTileX2][WdiagonalTileY2]; // WNW Tile
        
        
        if (mpos.moveTo(Direction.WNE).isValid()) {
          if (SearchPieceByPos.searchPiece(dOneMovedPos1, board).getColor() == Color.RED 
              || SearchPieceByPos.searchPiece(dOneMovedPos1, board).getColor() == Color.GREEN) {
            if(WdiagonalTile1.isOnPiece() == true) {
                PawnPos[2] = oneMovedPos.moveTo(Direction.WNE);
            } 
          }
        }
        
        if (mpos.moveTo(Direction.WNW).isValid()) {
          if (SearchPieceByPos.searchPiece(dOneMovedPos2, board).getColor() == Color.RED 
              || SearchPieceByPos.searchPiece(dOneMovedPos2, board).getColor() == Color.GREEN) {
            if(WdiagonalTile2.isOnPiece() == true) {
              PawnPos[3] = oneMovedPos.moveTo(Direction.WNW);
            }
          }
        }
      }
      return PawnPos;
    }

    else if (color == GamePiece.Color.BLACK) {
      int BPawnInitialCol = 1;
      Position oneMovedPos2 = mpos.moveTo(Direction.WS);
      if (oneMovedPos2.isValid()) {
        int BtileX = oneMovedPos2.getX()-1;
        int BtileY = oneMovedPos2.getY();
        Tile Btile = board.cBoard[BtileX][BtileY];
        if (Btile.isOnPiece() == false) {
          PawnPos[0] = oneMovedPos2.moveTo(Direction.WS); // Basic move
        }
        if (mpos.getX() == BPawnInitialCol) {
          if(Btile.isOnPiece() == false ) {
            BtileX -= 1;
            if(Btile.isOnPiece() == false ) {
              PawnPos[1] = oneMovedPos2.moveTo(Direction.WS); // When Pawn in starting line
            }
          }
        }
      }

      if (oneMovedPos2.isValid() && oneMovedPos2 == mpos) {
        int BdiagonalTileX1 = oneMovedPos2.getX()+1; // WSE
        int BdiagonalTileY1 = oneMovedPos2.getY()+1; // WSE
        Position dOneMovedPos3 = new Position(x+1, y+1); // WSE Position
        Position dOneMovedPos4 = new Position(x+1, y-1); // WSW Position
        int BdiagonalTileX2 = oneMovedPos2.getX()+1; // WSW
        int BdiagonalTileY2 = oneMovedPos2.getY()-1; // WSW
        
        Tile BdiagonalTile1 = board.cBoard[BdiagonalTileX1][BdiagonalTileY1]; // WSE Tile
        Tile BdiagonalTile2 = board.cBoard[BdiagonalTileX2][BdiagonalTileY2]; // WSW Tile        
        
        if (mpos.moveTo(Direction.WSE).isValid()) {
          if (SearchPieceByPos.searchPiece(dOneMovedPos3, board).getColor() == Color.RED 
              || SearchPieceByPos.searchPiece(dOneMovedPos3, board).getColor() == Color.GREEN) {
            if(BdiagonalTile1.isOnPiece() == true) {
                PawnPos[2] = oneMovedPos2.moveTo(Direction.WSE);
            } 
          }
        }
        
        if (mpos.moveTo(Direction.WSW).isValid()) {
          if (SearchPieceByPos.searchPiece(dOneMovedPos4, board).getColor() == Color.RED 
              || SearchPieceByPos.searchPiece(dOneMovedPos4, board).getColor() == Color.GREEN) {
            if(BdiagonalTile2.isOnPiece() == true) {
              PawnPos[3] = oneMovedPos2.moveTo(Direction.WSW);
            }
          }
        }
      }

      return PawnPos;
    }
      
    else if (color == GamePiece.Color.RED) {
      int RPawnInitialCol = 1;
      Position oneMovedPos3 = mpos.moveTo(Direction.RN);
      if (oneMovedPos3.isValid()) {
        int RtileX = oneMovedPos3.getX();
        int RtileY = oneMovedPos3.getY()+1;
        Tile Rtile = board.cBoard[RtileX][RtileY];
        if (Rtile.isOnPiece() == false) {
          PawnPos[0] = oneMovedPos3.moveTo(Direction.RN); // Basic move
        }
        if (mpos.getX() == RPawnInitialCol) {
          if(Rtile.isOnPiece() == false ) {
            RtileY += 1;
            if(Rtile.isOnPiece() == false ) {
              PawnPos[1] = oneMovedPos3.moveTo(Direction.RN); // When Pawn in starting line
            }
          }
        }
      }

      if (oneMovedPos3.isValid() && oneMovedPos3 == mpos) {
        int RdiagonalTileX1 = oneMovedPos3.getX()+1; // RNE
        int RdiagonalTileY1 = oneMovedPos3.getY()+1; // RNE
        Position dOneMovedPos5 = new Position(x+1, y+1); // RNE Position
        Position dOneMovedPos6 = new Position(x-1, y+1); // RNW Position
        int RdiagonalTileX2 = oneMovedPos3.getX()-1; // RNW
        int RdiagonalTileY2 = oneMovedPos3.getY()+1; // RNW
          
        Tile RdiagonalTile1 = board.cBoard[RdiagonalTileX1][RdiagonalTileY1]; // RNE Tile
        Tile RdiagonalTile2 = board.cBoard[RdiagonalTileX2][RdiagonalTileY2]; // RNW Tile        
          
        if (mpos.moveTo(Direction.RNE).isValid()) {
          if (SearchPieceByPos.searchPiece(dOneMovedPos5, board).getColor() == Color.WHITE 
              || SearchPieceByPos.searchPiece(dOneMovedPos5, board).getColor() == Color.BLACK) {
            if(RdiagonalTile1.isOnPiece() == true) {
                PawnPos[2] = oneMovedPos3.moveTo(Direction.RNE);
            } 
          }
        }
          
        if (mpos.moveTo(Direction.RNW).isValid()) {
          if (SearchPieceByPos.searchPiece(dOneMovedPos6, board).getColor() == Color.WHITE 
              || SearchPieceByPos.searchPiece(dOneMovedPos6, board).getColor() == Color.BLACK) {
            if(RdiagonalTile2.isOnPiece() == true) {
              PawnPos[3] = oneMovedPos3.moveTo(Direction.RNW);
            }
          }
        }
      }

      return PawnPos;
    }
      
    else {
      int GPawnInitialCol = 12;
      Position oneMovedPos4 = mpos.moveTo(Direction.RS);
      if (oneMovedPos4.isValid()) {
        int GtileX = oneMovedPos4.getX();
        int GtileY = oneMovedPos4.getY()-1;
        Tile Gtile = board.cBoard[GtileX][GtileY];
        if (Gtile.isOnPiece() == false) {
          PawnPos[0] = oneMovedPos4.moveTo(Direction.RS); // Basic move
        }
        if (mpos.getX() == GPawnInitialCol) {
          if(Gtile.isOnPiece() == false ) {
            GtileY -= 1;
            if(Gtile.isOnPiece() == false ) {
              PawnPos[1] = oneMovedPos4.moveTo(Direction.RS); // When Pawn in starting line
            }
          }
        }
      }

      if (oneMovedPos4.isValid() && oneMovedPos4 == mpos) {
        int GdiagonalTileX1 = oneMovedPos4.getX()+1; // RSE
        int GdiagonalTileY1 = oneMovedPos4.getY()-1; // RSE
        Position dOneMovedPos7 = new Position(x+1, y-1); // RSE Position
        Position dOneMovedPos8 = new Position(x-1, y-1); // RSW Position
        int GdiagonalTileX2 = oneMovedPos4.getX()-1; // RSW
        int GdiagonalTileY2 = oneMovedPos4.getY()-1; // RSW
          
        Tile GdiagonalTile1 = board.cBoard[GdiagonalTileX1][GdiagonalTileY1]; // RSE Tile
        Tile GdiagonalTile2 = board.cBoard[GdiagonalTileX2][GdiagonalTileY2]; // RSW Tile        
          
        if (mpos.moveTo(Direction.RSE).isValid()) {
          if (SearchPieceByPos.searchPiece(dOneMovedPos7, board).getColor() == Color.WHITE 
              || SearchPieceByPos.searchPiece(dOneMovedPos7, board).getColor() == Color.BLACK) {
            if(GdiagonalTile1.isOnPiece() == true) {
                PawnPos[2] = oneMovedPos4.moveTo(Direction.RSE);
            } 
          }
        }
          
        if (mpos.moveTo(Direction.RSW).isValid()) {
          if (SearchPieceByPos.searchPiece(dOneMovedPos8, board).getColor() == Color.WHITE 
              || SearchPieceByPos.searchPiece(dOneMovedPos8, board).getColor() == Color.BLACK) {
            if(GdiagonalTile2.isOnPiece() == true) {
              PawnPos[3] = oneMovedPos4.moveTo(Direction.RSW);
            }
          }
        }
      }
      return PawnPos;
    }
  }

  public boolean isCheck(Color color, Position tilePosition, ChessBoard b) {
    Color[] oppositeColor = new Color[2];
    int sameMoveTile = 0; // If the tile that king can move on is same as parameter tile, it will be 1

    Position[] kingWays = waysKingPos(color);

    for (int i = 0; i < kingWays.length; i++) {
      if (kingWays[i] == tilePosition) {
        sameMoveTile = 1;
      }
    }

    if (sameMoveTile == 0) {
      return false; // If the tile location is that King can't go, return false
    }

    if (color == Color.WHITE || color == GamePiece.Color.BLACK) {
      oppositeColor[0] = Color.RED;
      oppositeColor[1] = Color.GREEN;
    } else {
      oppositeColor[0] = Color.WHITE;
      oppositeColor[1] = Color.BLACK;
    }

    Position pos = new Position(0, 0);
    GamePiece piece;

    ArrayList<Position> allPos = new ArrayList<Position>();
    for (int j = 0; j < 14; j++) {
      for (int k = 0; k < 14; k++) {
        if (pos.isValid()) {
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
    Tile aroundTile = ChessBoard.cBoard[tilePosX][tilePosY];

    if (aroundTile.isOnPiece() == false) {
      for (int i = 0; i < result.length; i++) {
        if (result[i] == tilePosition) {
          return true;
        }
      }
    }
    return false;
  }
  
  public Position[] waysKingPosCheck(Color color) {
    Position KingPos[] = new Position[8];
    int count = 0;
    Position zero = new Position(0, 0);
    KingPos = waysKingPos(color);
    for (int i=0; i<KingPos.length; i++) {
      if(isCheck(color, KingPos[i], board) == true) {
        for(int j = i; j<KingPos.length -1; j++) {
          KingPos[j] = KingPos[j+1];
          count++;
        }
      }
    }
    if(count != 0) {
      for(int k=KingPos.length-count; k<KingPos.length; k++) {
        KingPos[k] = zero; // if Check position is valid, index in array is check that will be zero
      }
    }
    
    return KingPos;
  }

}
