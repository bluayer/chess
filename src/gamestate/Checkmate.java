package gamestate;

import board.ChessBoard;
import board.SearchPieceByPos;
import board.Tile;
import chess.ChessGui;
import gamestate.GameController;
import piece.Bishop;
import piece.GamePiece;
import piece.GamePiece.Color;
import piece.GamePiece.PieceType;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.PieceWay;
import piece.Position;
import piece.Queen;
import piece.Rook;


/**
 * 
 * @author Yeoilgoo
 *
 * @since 2018-06-02
 */
public class Checkmate {

  private Tile[][] nowTile; 
  King myKing;
  
  King[] king;
  Queen[] queen;
  Knight[][] knight;
  Bishop[][] bishop;
  Rook[][] rook;
  Pawn[][] pawn;
  Position[] aw; // availableWay
  
  public Checkmate(King k) {
    this.myKing = k;
    this.nowTile = ChessGui.b.getcBoard();
    this.king = ChessGui.b.king;
    this.queen = ChessGui.b.queen;
    this.knight = ChessGui.b.knight;
    this.bishop = ChessGui.b.bishop;
    this.rook = ChessGui.b.rook;
    this.pawn = ChessGui.b.pawn;
  }
  
  public boolean isCheckmate(Position kingPos, Tile[][] tile) {
    Check c = new Check(this.myKing, nowTile);
    Tile[][] bufferTile = tile;
    Tile t;
    Color teamColor = myKing.getColor();
    int team = ChessBoard.cvtTeam(GameController.colorToTeam(teamColor));
    Position temp = new Position(0, 0);
    PieceWay kingWay = new PieceWay(kingPos);
    Position nowPos = new Position(0, 0);
    GamePiece piece;
    
    if(!c.isCheck() && kingWay.waysKingPosCheck(teamColor) == null)
      return false;
    
    else {
      for(int i = 0; i < 14; i++) {
        for(int j = 0; j < 14; j++) {
          if(bufferTile[i][j].getActive()) {
            if(bufferTile[i][j].isOnPiece()) {
              nowPos.setX(i); nowPos.setY(j);
              piece = SearchPieceByPos.searchPiece(nowPos, ChessGui.b);
              if(piece.getColor() == teamColor) {
                if(bufferTile[i][j].getOccupyPiece() == PieceType.QUEEN) {
                  aw = queen[team].getCanMoves();
                
                  for(int k = 0; k < aw.length; k++) {
                    t = new Tile(false, PieceType.NOPE);
                    bufferTile[aw[k].getX()][aw[k].getY()] = bufferTile[i][j];
                    bufferTile[i][j] = t;
                    c = new Check(this.myKing, bufferTile);
                  
                    if(!c.isCheck()) {
                      bufferTile = tile;
                      return false;
                      }
                    }
                  }
                }
      
                else if(bufferTile[i][j].getOccupyPiece() == PieceType.BISHOP) {
                  for(int k = 0; k < bishop[team].length; k++) {
                    aw = bishop[team][k].getCanMoves();
                
                    for(int l = 0; l < aw.length; l++) {
                      t = new Tile(false, PieceType.NOPE);
                      bufferTile[aw[l].getX()][aw[l].getY()] = bufferTile[i][j];
                      bufferTile[i][j] = t;
                      c = new Check(this.myKing, bufferTile);
                      
                      if(!c.isCheck()) {
                        bufferTile = tile;
                        return false;
                      }
                    }
                  }
                }
              
                else if(bufferTile[i][j].getOccupyPiece() == PieceType.KNIGHT) {
                  for(int k = 0; k < knight[team].length; k++) {
                    aw = knight[team][k].getCanMoves();
                
                    for(int l = 0; l < aw.length; l++) {
                      t = new Tile(false, PieceType.NOPE);
                      bufferTile[aw[l].getX()][aw[l].getY()] = bufferTile[i][j];
                      bufferTile[i][j] = t;
                      c = new Check(this.myKing, bufferTile);
                      
                      if(!c.isCheck()) {
                        bufferTile = tile;
                        return false;
                      }
                    }
                  }
                }
              
                else if(bufferTile[i][j].getOccupyPiece() == PieceType.ROOK) {
                  for(int k = 0; k < rook[team].length; k++) {
                    aw = rook[team][k].getCanMoves();
                
                    for(int l = 0; l < aw.length; l++) {
                      t = new Tile(false, PieceType.NOPE);
                      bufferTile[aw[l].getX()][aw[l].getY()] = bufferTile[i][j];
                      bufferTile[i][j] = t;
                      c = new Check(this.myKing, bufferTile);
                      
                      if(!c.isCheck()) {
                        bufferTile = tile;
                        return false;
                      }
                    }
                  }
                }
              
                else if(bufferTile[i][j].getOccupyPiece() == PieceType.PAWN) {
                  for(int k = 0; k < pawn[team].length; k++) {
                    aw = pawn[team][k].getCanMoves();
                
                    for(int l = 0; l < aw.length; l++) {
                      t = new Tile(false, PieceType.NOPE);
                      bufferTile[aw[l].getX()][aw[l].getY()] = bufferTile[i][j];
                      bufferTile[i][j] = t;
                      c = new Check(this.myKing, bufferTile);
                      
                      if(!c.isCheck()) {
                        bufferTile = tile;
                        return false;
                      }
                    }
                  }
                }
              
                else {
                  System.out.println("Checkmate:Error");
                  return false;
                }
              }
            }
          }
        }
      
      bufferTile = tile;
      System.out.println("Checkmate");
      return true;
    }
  }
}
